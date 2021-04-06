package com.pilot.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.PilotBodyService;
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

    private List<PilotBody> pilotBodies = new ArrayList<>();

    boolean flag = false;

    public List<PilotBody> getPilotBodies() {
        return pilotBodies;
    }

    @Override
    public void invoke(PilotBody pilotBody, AnalysisContext analysisContext) {

        log.info("开始解析数据---------");
        //1.initialize
        //2.check pilotBody exist
        Long pilotId = pilotBody.getPilotId();
        flag = pilotBodyService.checkPilotBodyExist(pilotId);
        if (flag) {
            flag = false;
            throw new MyException("飞行员id为" + pilotId + "的体型数据信息已存在");
        }

        //3.add to list
        pilotBodies.add(pilotBody);

        //4.insert operation
//        pilotBodyService.batchInsertPilotBody(pilotBodies);

        //5.clear
//        pilotBodies.clear();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        pilotBodyService.batchInsertPilotBody(pilotBodies);
        pilotBodies.clear();
    }
}
