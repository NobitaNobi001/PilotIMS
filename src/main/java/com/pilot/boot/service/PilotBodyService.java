package com.pilot.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.PilotBody;

import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
public interface PilotBodyService {


    /**
     * add pilotBody
     * @param pilotBody
     * @return
     */
    int addPilotBodyByPilotId(PilotBody pilotBody);

    /**
     * find all pilotBody
     * @param pilotBodyPage
     * @return
     */
    IPage findAllPilotBody(Page<PilotBody> pilotBodyPage);

    /**
     * find pilotBody by pilotId
     * @param pilotId
     * @return
     */
    PilotBody findPilotBodyByPilotId(Long pilotId);

    /**
     * find pilotBody with condition
     * @param pilotBodyPage
     * @param listCondition
     * @return
     */
    IPage findPilotBodyWithCondition(Page<PilotBody> pilotBodyPage,Map<String,List<Long>> listCondition);

    /**
     * check pilotBody exist
     * @param pilotIdMap
     * @return
     */
    boolean checkPilotBodyExist(Map<String,Long> pilotIdMap);

    /**
     * update pilotBody by pilotId
     * @param pilotBody
     * @return
     */
    int updatePilotBodyByPilotId(PilotBody pilotBody);

    /**
     * delete pilotBody by PilotId
     * @param pilotId
     * @return
     */
    int deletePilotBodyByPilotId(Long pilotId);

    /**
     * batch delete pilotId
     * @param pilotId
     * @return
     */
    int batchDeletePilotBodyByPilotIds(List<Long> pilotId);
}
