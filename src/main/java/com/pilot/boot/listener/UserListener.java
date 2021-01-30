package com.pilot.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.User;
import com.pilot.boot.entity.excel.UserExcel;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.service.UserService;
import com.pilot.boot.utils.ParamVerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ezuy
 * @date 21/1/30 21:46
 */
@Slf4j
@Component
public class UserListener extends AnalysisEventListener<UserExcel> {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    private List<User> users = new ArrayList<>();

    private String userName;
    private String type;
    private String card;
    private Long deptId;
    private String phone;
    private String email;

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void invoke(UserExcel userExcel, AnalysisContext analysisContext) {
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

    public void addUser(User user, UserExcel userExcel) {

        //type
        user.setType(userExcel.getType() == "管理员" ? "0" : "1");

        //userName
        userName = userExcel.getUserName();
        if (!ParamVerifyUtil.verifyUsername(userName)) {
            throw new MyException("用户姓名为:" + userName + "的姓名错误!");
        }
        if (!userService.findUserByName(userName)) {
            throw new MyException("姓名为:" + userName + "的用户已存在");
        }
        user.setUserName(userName);

        //sex
        user.setSex(userExcel.getSex() == "男" ? 0 : 1);

        //card
        card = userExcel.getCard();
        if (!ParamVerifyUtil.verifyCard(card)) {
            throw new MyException("用户姓名为:" + user.getUserName() + "的身份证号码错误!");
        }
        user.setCard(card);

        //password
        user.setPassword("a" + card.substring(12));

        //deptId
        deptId = deptService.selectDeptIdByDeptName(userExcel.getDeptName());
        if (deptId == null) {
            throw new MyException("用户姓名为:" + user.getUserName() + "的部门错误!");
        }
        user.setDeptId(deptId);

        //position
        user.setPosition(userExcel.getPosition());
        //jobTitle
        user.setJobTitle(userExcel.getJobTitle());

        //phone
        phone = userExcel.getPhone();
        if (!ParamVerifyUtil.verifyPhone(phone)) {
            throw new MyException("用户姓名为:" + user.getUserName() + "的电话号码错误!");
        }
        user.setPhone(phone);

        //email
        email = userExcel.getEmail();
        if (!ParamVerifyUtil.verifyEmail(email)) {
            throw new MyException("用户姓名为:" + user.getUserName() + "的邮箱错误!");
        }
        user.setEmail(email);

        //remark
        user.setRemark(userExcel.getRemark());

        //add to list
        users.add(user);
    }
}
