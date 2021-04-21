package com.pilot.boot.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.excel.PilotExcel;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
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

    private static List<Pilot> pilots = new ArrayList<>();

    public static List<Pilot> getPilots() {
        return pilots;
    }

    public static void setPilots(List<Pilot> pilots) {
        PilotListener.pilots = pilots;
    }

    @Override
    public void invoke(PilotExcel pilotExcel, AnalysisContext analysisContext) {
        log.info("开始解析数据");
        addPilot(new Pilot(), pilotExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        pilotService.batchAddPilot(pilots);
        pilots.clear();
        log.info("所有数据导入完成");
    }

    public void addPilot(Pilot pilot, PilotExcel pilotExcel) {

        //pilotName
        Assert.validName(pilotExcel.getPilotName(), CommonResult.fail(100, "飞行员姓名为:" + pilotExcel.getPilotName() + "的姓名不正确"));
        pilot.setPilotName(pilotExcel.getPilotName());

        //sex
        pilot.setSex("男".equals(pilotExcel.getSex()) ? 0 : 1);

        //card
        Assert.validCard(pilotExcel.getCard(), CommonResult.fail(100, "飞行员姓名为:" + pilotExcel.getPilotName() + "的身份证号码格式不正确"));
        pilot.setCard(pilotExcel.getCard());

        //deptId
        Long deptId = deptService.selectDeptIdByDeptName(pilotExcel.getDeptName());
        Assert.notNull(deptId, CommonResult.fail(100, "飞行员姓名为:" + pilotExcel.getPilotName() + "的部门不正确"));
        pilot.setDeptId(deptId);

        //position
        pilot.setPosition(pilotExcel.getPosition());
        //jobTitle
        pilot.setJobTitle(pilotExcel.getJobTitle());

        //phone
        Assert.validPhone(pilotExcel.getPhone(), CommonResult.fail(100, "飞行员姓名为:" + pilotExcel.getPilotName() + "的手机号码格式不正确"));
        pilot.setPhone(pilotExcel.getPhone());

        //email
        Assert.validEmail(pilotExcel.getEmail(), CommonResult.fail(100, "飞行员姓名为:" + pilotExcel.getPilotName() + "的邮箱格式不正确"));
        pilot.setEmail(pilotExcel.getEmail());

        //remark
        pilot.setRemark(pilotExcel.getRemark());

        //add to list
        pilots.add(pilot);

        if (pilots.size() >= 5) {
            pilotService.batchAddPilot(pilots);
            pilots.clear();
        }
    }
}
