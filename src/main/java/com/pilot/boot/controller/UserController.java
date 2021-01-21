package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pilot.boot.entity.User;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.UserService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import com.pilot.boot.utils.ParamVerifyUtil;
import com.pilot.boot.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * user controller
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Slf4j
@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * login by username and password
     * @param nameAndPassword
     * @return
     */
    @PostMapping("/user/login")
    public CommonResult login(@RequestBody Map<String, String> nameAndPassword) {

        String name = nameAndPassword.get(ConstantUtil.name.toString());
        String password = nameAndPassword.get(ConstantUtil.password.toString());

        //1.check name and password format
        if (!ParamVerifyUtil.verifyUsername(name)) {
            throw new MyException("用户名格式不正确");
        }

        if (!ParamVerifyUtil.verifyPassword(password)) {
            throw new MyException("密码格式不正确");
        }


        //2.find user
        User user = userService.findUserByNameAndPassword(name, password);

        //3.check user
        if (user == null) {
            return new CommonResult(100, "用户不存在或密码错误");
        } else {

            //4.token
            String token = TokenUtil.signature(name);

            //5.save to map
            Map<String, Object> map = new LinkedHashMap<>(2);
            map.put("user", user);
            map.put("token", token);

            //log print
            log.info(token);

            //6.response to front
            return new CommonResult(200, "密码正确", map);
        }

    }

    /**
     * create
     * add user
     *
     * @param user
     * @return
     */
    @PostMapping("/user/add")
    public CommonResult addUser(@Valid @RequestBody User user) {

        int result = userService.addUser(user);

        return new CommonResult(200, "更新" + result + "条用户信息", user);
    }

    /**
     * add user by excel
     *
     * @param file
     * @return
     */
    @PostMapping("/user/add/excel")
    public CommonResult addUserByExcel(@RequestParam("file") CommonsMultipartFile file) {
        return new CommonResult(200, "用户存在");
    }

    /**
     * retrieve
     * select user by userId
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/get/{userId}")
    public CommonResult findUserByUserId(@PathVariable("userId") Long userId) {
        return new CommonResult(200, "查询成功", userService.findUserById(userId));
    }

    /**
     * select all user
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/user/list")
    public CommonResult findAllUser(@RequestParam(name = "current", defaultValue = "1") Long current, @RequestParam(name = "size", defaultValue = "5") Long size) {

        //1.Encapsulate the user page
        Page<User> userPage = new Page<>(current, size);
        //2.find all users with page
        IPage<User> userIPage = userService.findAllUser(userPage);
        //3.response to the front
        return new CommonResult(200, "查询成功", userIPage);
    }

    /**
     * update
     * update user info
     *
     * @param user
     * @return
     */
    @PutMapping("/user/update/info")
    public CommonResult updateUserById(@Valid @RequestBody User user) {

        //1.update operation
        int result = userService.updateUser(user);

        //2.response to front
        return new CommonResult(200, "更新" + result + "条记录", user);
    }

    /**
     * delete
     * delete user by userId
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/user/delete")
    public CommonResult deleteUserById(@RequestBody Map<String, Long> userId) {

        //1.delete operation
        int result = userService.deleteUserById(userId.get(ConstantUtil.userId.toString()));

        //2.response to front
        return new CommonResult(200, "删除" + result + "条记录");
    }

    @DeleteMapping("/user/batchDelete")
    public CommonResult deleteUserByUserIds(@RequestBody Map<String, Object> userId) {

        //1.save to list
        List<String> list = (List<String>) userId.get(ConstantUtil.userId.toString());

        //2.delete operation
        int result = userService.batchDeleteUser(list);

        //3.response to front
        return new CommonResult(200, "删除" + result + "条记录");
    }

    /**
     * check username exist
     *
     * @param nameMap
     * @return
     */
    @PostMapping("/user/check")
    public CommonResult checkUserExist(@RequestBody Map<String, String> nameMap) {

        String name = nameMap.get(ConstantUtil.name.toString());

        //1.check format
        if (!ParamVerifyUtil.verifyUsername(name)) {
            throw new MyException("用户名格式不正确");
        }

        //2.check name exist
        if (!userService.findUserByName(name)) {
            return new CommonResult(200, "用户名已存在!");
        }

        return new CommonResult(200, "用户名可用");

    }

    @PutMapping("/user/password/update")
    public CommonResult updatePasswordByUserId(@RequestBody Map<String, String> pwdInfo) {

        //password
        String password = pwdInfo.get(ConstantUtil.password.toString());
        //new password
        String pass = pwdInfo.get(ConstantUtil.pass.toString());
        //new rePassword
        String rePass = pwdInfo.get(ConstantUtil.rePass.toString());

        //1.check password
        // password | pass | rePass ==""
        if ("".equals(password.trim()) || "".equals(pass.trim()) || "".equals(rePass.trim())) {
            throw new NullPointerException("密码不能为空!");
        }

        //password format error
        if (!ParamVerifyUtil.verifyPassword(password)) {
            throw new MyException("原密码格式错误!");
        }
        //re password format error
        if (!ParamVerifyUtil.verifyPassword(pass) || !ParamVerifyUtil.verifyPassword(pwdInfo.get(ConstantUtil.rePass.toString()))) {
            throw new MyException("新密码格式错误!");
        }
        //pass != rePass
        if (!pass.equals(rePass)) {
            throw new MyException("两次密码输入不一致!");
        }

        //2.update operation
        userService.updateUserPassword(pwdInfo);

        //3.response to front
        return new CommonResult(200, "修改userId为" + pwdInfo.get(ConstantUtil.userId.toString()) + "的用户密码成功");

    }

}
