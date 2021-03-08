package com.pilot.boot.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.excel.PilotExcel;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.ParamVerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * listen pilot export
 *
 * @author ezuy
 * @date 21/1/27 20:43
 */
@Slf4j
@Component
public class PilotListener extends AnalysisEventListener<PilotExcel> {

    @Autowired
    private PilotService pilotService;

    @Autowired
    private DeptService deptService;

    private List<Pilot> pilots = new ArrayList<>();

    private String pilotName;
    private String card;
    private Long deptId;
    private String phone;
    private String email;

    public List<Pilot> getPilots() {
        return pilots;
    }

    @Override
    public void invoke(PilotExcel pilotExcel, AnalysisContext analysisContext) {
        log.info("开始解析数据");
        addPilot(new Pilot(), pilotExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        log.info("所有数据解析完成");
        //remain data
        //every 25 info to insert
        pilotService.batchAddPilot(pilots);
        pilots.clear();
    }

    public void addPilot(Pilot pilot, PilotExcel pilotExcel) {
        //pilotName
        pilotName = pilotExcel.getPilotName();
        if (!ParamVerifyUtil.verifyUsername(pilotName)) {
            throw new MyException("飞行员姓名为:" + pilotName + "的姓名错误!");
        }
        pilot.setPilotName(pilotName);

        //sex
        pilot.setSex("男".equals(pilotExcel.getSex()) ? 0 : 1);

        //card
        card = pilotExcel.getCard();
        if (!ParamVerifyUtil.verifyCard(card)) {
            throw new MyException("飞行员姓名为:" + pilot.getPilotName() + "的身份证号码错误!");
        }
        pilot.setCard(card);

        //deptId
        deptId = deptService.selectDeptIdByDeptName(pilotExcel.getDeptName());
        if (deptId == null) {
            throw new MyException("飞行员姓名为:" + pilot.getPilotName() + "的部门错误!");
        }
        pilot.setDeptId(deptId);

        //position
        pilot.setPosition(pilotExcel.getPosition());
        //jobTitle
        pilot.setJobTitle(pilotExcel.getJobTitle());

        //phone
        phone = pilotExcel.getPhone();
        if (!ParamVerifyUtil.verifyPhone(phone)) {
            throw new MyException("飞行员姓名为:" + pilot.getPilotName() + "的电话号码错误!");
        }
        pilot.setPhone(phone);

        //email
        email = pilotExcel.getEmail();
        if (!ParamVerifyUtil.verifyEmail(email)) {
            throw new MyException("飞行员姓名为:" + pilot.getPilotName() + "的邮箱错误!");
        }
        pilot.setEmail(email);

        //remark
        pilot.setRemark(pilotExcel.getRemark());

        //add to list
        pilots.add(pilot);
    }
}
