package com.pilot.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pilot.boot.entity.PilotBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/3/7 11:41
 */
@Slf4j
@Component
public class PilotBodyListener extends AnalysisEventListener<PilotBody> {


    @Override
    public void invoke(PilotBody pilotBody, AnalysisContext analysisContext) {



    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
