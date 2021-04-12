package com.pilot.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.User;
import com.pilot.boot.entity.excel.UserExcel;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.service.UserService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ezuy
 * @date 21/1/30 21:46
 */
@Slf4j
@Validated
@Component
public class UserListener extends AnalysisEventListener<UserExcel> {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void invoke(@Valid UserExcel userExcel, AnalysisContext analysisContext) {
        log.info("开始解析数据");
        addUser(new User(), userExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        log.info("所有数据解析完成");
        //remain data
        //batch insert
        userService.batchAddUser(users);
    }

    public void addUser(User user, @Valid UserExcel userExcel) {

        //type
        user.setType(userExcel.getType() == "管理员" ? "0" : "1");

        //userName
        Assert.validName(userExcel.getUserName(), CommonResult.fail(100, "用户姓名为:" + userExcel.getUserName() + "的格式错误"));
        Assert.isTrue(userService.findUserByName(userExcel.getUserName()), CommonResult.fail(100, "姓名为:" + userExcel.getUserName() + "的用户已存在"));
        user.setUserName(userExcel.getUserName());

        //sex
        user.setSex(userExcel.getSex() == "男" ? 0 : 1);

        //card
        Assert.validCard(userExcel.getCard(), CommonResult.fail(100, "用户姓名为:" + user.getUserName() + "的身份证号码错误"));
        user.setCard(userExcel.getCard());

        //password
        // a + card last seven
        user.setPassword("a" + userExcel.getCard().substring(12));

        //deptId
        Long deptId = deptService.selectDeptIdByDeptName(userExcel.getDeptName());
        user.setDeptId(deptId);

        //position
        user.setPosition(userExcel.getPosition());
        //jobTitle
        user.setJobTitle(userExcel.getJobTitle());

        //phone
        Assert.validPhone(userExcel.getPhone(), CommonResult.fail(100, "用户姓名为:" + user.getUserName() + "的手机号码错误!"));
        user.setPhone(userExcel.getPhone());

        //email
        Assert.validEmail(userExcel.getEmail(), CommonResult.fail(100, "用户姓名为:" + user.getUserName() + "的邮箱错误!"));
        user.setEmail(userExcel.getEmail());

        //remark
        user.setRemark(userExcel.getRemark());

        //add to list
        users.add(user);

        if (users.size() >= 5) {
            userService.batchAddUser(users);
            users.clear();
        }
    }
}
