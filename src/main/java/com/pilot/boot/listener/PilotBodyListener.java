package com.pilot.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ezuy
 * @date 21/3/7 11:41
 */
@Slf4j
@Component
public class PilotBodyListener extends AnalysisEventListener<PilotBody> {

    @Autowired
    private PilotBodyService pilotBodyService;
    @Autowired
    private PilotService pilotService;

    private static List<PilotBody> pilotBodies = new ArrayList<>();

    public static List<PilotBody> getPilotBodies() {
        return pilotBodies;
    }

    @Override
    public void invoke(PilotBody pilotBody, AnalysisContext analysisContext) {

        log.info("开始解析数据---------");
        //1.initialize
        //2.check pilotBody exist
        Long pilotId = pilotBody.getPilotId();
        boolean flag1 = pilotService.checkPilotExistByPilotId(pilotId);
        boolean flag2 = pilotBodyService.checkPilotBodyExist(pilotId);

        Assert.isTrue(flag1, CommonResult.fail(100, "不存在编号为" + pilotId + "的飞行员"));
        Assert.notTrue(flag2, CommonResult.fail(100, "飞行员编号为" + pilotId + "的体型数据信息已存在"));


        //3.add to list
        pilotBodies.add(pilotBody);

        if (pilotBodies.size() == 5) {
            pilotBodyService.batchInsertPilotBody(pilotBodies);
            pilotBodies.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        if (pilotBodies.size()!=0) {
            pilotBodyService.batchInsertPilotBody(pilotBodies);
            pilotBodies.clear();
        }
        log.info("所有体型数据导入完成");
    }
}
