package com.pilot.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.User;
import com.pilot.boot.entity.excel.UserExcel;
import com.pilot.boot.exception.IdentifyException;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.listener.UserListener;
import com.pilot.boot.service.UserService;
import com.pilot.boot.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Slf4j
@Validated
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserListener userListener;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/user/login")
    public CommonResult login(@RequestBody Map<String, String> nameAndPassword) {

        String name = nameAndPassword.get(ConstantUtil.userName.toString());
        String password = nameAndPassword.get(ConstantUtil.password.toString());

        log.info(name);
        log.info(password);

        //1.check name and password format
        if (!ParamVerifyUtil.verifyUsername(name)) {
            return CommonResult.fail(100, "用户名格式不正确");
        }

        if (!ParamVerifyUtil.verifyPassword(password)) {
            return CommonResult.fail(100, "密码必须为8~16个字母和数字组合");
        }

        //2.find user
        User user = userService.findUserByNameAndPassword(name, password);

        //3.check user
        if (user == null) {
            return CommonResult.fail(100, "用户不存在或密码错误");
        } else {

            //4.token
            String token = TokenUtil.signature(user.getUserId());

            //5.save to map
            Map<String, Object> map = new LinkedHashMap<>(2);
            map.put("token", token);
            map.put("user", user);

            redisUtil.set(String.valueOf(user.getUserId()), token, 3L, TimeUnit.DAYS);
            //log print
            log.info(token);

            //6.response to front
            return CommonResult.success(map);
        }

    }

    @PostMapping("/user/add")
    public CommonResult addUser(@Valid @RequestBody User user) {

        //1.get result
        int result = userService.addUser(user);

        //2.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }
        return CommonResult.success(user);
    }

    @PostMapping("/user/add/excel")
    public CommonResult addUserByExcel(@RequestParam("file") MultipartFile file) {
        //1.check file == null
        if (file.getSize() == 0) {
            return CommonResult.fail(100, "请选择文件");
        }

        //2.get file suffix
        int begin = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(begin);

        //3.check file format
        if (!(".xls").equals(suffix)) {
            return CommonResult.fail(100, "请上传xls格式文件");
        }

        try {
            //4.read excel
            //4.1 work sheet
            ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getInputStream(), UserExcel.class, userListener);
            //4.2 work table
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();
            //4.3 read table
            sheetBuilder.headRowNumber(1).doRead();

        } catch (IOException e) {

            //5. clear remain data
            userListener.getUsers().clear();
            e.printStackTrace();
            return CommonResult.fail(100, "添加失败");
        }

        return CommonResult.success("添加成功");
    }

    @PostMapping("/user/check")
    public CommonResult checkUserExist(@RequestBody Map<String, String> nameMap) {

        String name = nameMap.get(ConstantUtil.userName.toString());

        //1.check format
        if (!ParamVerifyUtil.verifyUsername(name)) {
            return CommonResult.fail(100, "用户名格式不正确");
        }

        //2.check name exist
        if (userService.findUserByName(name)) {
            return CommonResult.fail(100, "用户名已存在!");
        }

        return CommonResult.success("用户名可用");

    }

    @GetMapping("/user/get/{userId}")
    public CommonResult findUserByUserId(@PathVariable("userId") Long userId) {

        //1.get query user
        User user = userService.findUserById(userId);

        //2.check
        if (user == null) {
            return new CommonResult(100, "查无此人");
        }
        return CommonResult.success(user);
    }

    @GetMapping("/user/list")
    public CommonResult findAllUser(@RequestParam(name = "userName", required = false) String userName,
                                    @RequestParam(name = "current", defaultValue = "1") Long current,
                                    @RequestParam(name = "size", defaultValue = "8") Long size,
                                    HttpServletRequest request) {

        //1.get userId
        Long userId = Long.valueOf(request.getHeader("userId"));
        //2.check
        if (userId == null) {
            throw new IdentifyException("登录信息已失效,请重新登录");
        }

        if (userName == null) {
            //1.Encapsulate the user page
            Page<User> userPage = new Page<>(current, size);
            //2.find all users with page
            IPage<User> userPages = userService.findAllUser(userId, userPage);
            //3.response to the front
            return CommonResult.success(userPages);
        } else {
            //1.Encapsulate the user page
            Page<User> userPage = new Page<>(current, size);
            //2.get result
            IPage<User> userPages = userService.findUsersByNamed(userPage, userName, userId);
            //3.response to front
            return CommonResult.success(userPages);
        }
    }

    @PutMapping("/user/update/info")
    public CommonResult updateUserById(@Valid @RequestBody User user) {
        //1.update operation
        int result = userService.updateUser(user);

        //2.response to front
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success("更新成功");
    }

    @PutMapping("/user/password/update")
    public CommonResult updatePasswordByUserId(@RequestBody Map<String, String> pwdInfo) {

        String password;
        String pass;
        String rePass;

        try {
            //password
            password = pwdInfo.get(ConstantUtil.password.toString());
            //new password
            pass = pwdInfo.get(ConstantUtil.pass.toString());
            //new rePassword
            rePass = pwdInfo.get(ConstantUtil.rePass.toString());
        } catch (Exception e) {
            return CommonResult.fail(100, "传入参数有误");
        }

        //1.check password
        // password | pass | rePass ==""
        if ("".equals(password.trim()) || "".equals(pass.trim()) || "".equals(rePass.trim())) {
            return CommonResult.fail(100, "密码不能为空!");
        }

        //password format error
        if (!ParamVerifyUtil.verifyPassword(password)) {
            return CommonResult.fail(100, "原密码格式错误!");
        }
        //re password format error
        if (!ParamVerifyUtil.verifyPassword(pass) || !ParamVerifyUtil.verifyPassword(pwdInfo.get(ConstantUtil.rePass.toString()))) {
            return CommonResult.fail(100, "新密码格式错误!");
        }
        //pass != rePass
        if (!pass.equals(rePass)) {
            return CommonResult.fail(100, "两次密码输入不一致!");
        }

        //2.update operation
        int result = userService.updateUserPassword(pwdInfo);

        //3.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success("更新成功");

    }

    @DeleteMapping("/user/delete")
    public CommonResult deleteUserById(@RequestBody Map<String, Long> userId) {

        //1.delete operation
        int result = userService.deleteUserById(userId.get(ConstantUtil.userId.toString()));

        //2.check result and response to front
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

    @DeleteMapping("/user/batchDelete")
    public CommonResult deleteUserByUserIds(@RequestBody Map<String, List<Long>> userIds) {

        //1.save to list
        List<Long> list = userIds.get(ConstantUtil.userId.toString());

        //2.delete operation
        int result = userService.batchDeleteUser(list);

        //3.check and response to front
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

}