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
     *
     * @param pilotBody
     * @return
     */
    int addPilotBodyByPilotId(PilotBody pilotBody);

    /**
     * batch insert pilotBody
     *
     * @param pilotBodies
     * @return
     */
    int batchInsertPilotBody(List<PilotBody> pilotBodies);

    /**
     * find all pilotBody
     *
     * @param pilotBodyPage
     * @param deleted
     * @return
     */
    IPage findAllPilotBody(Page<PilotBody> pilotBodyPage,Long deleted);

    /**
     * find pilotBody by pilotId
     *
     * @param pilotId
     * @return
     */
    PilotBody findPilotBodyByPilotId(Long pilotId);

    /**
     * find pilotBody with condition
     *
     * @param pilotBodyPage
     * @param listCondition
     * @return
     */
    IPage findPilotBodyWithCondition(Page<PilotBody> pilotBodyPage, Map<String, List<Long>> listCondition);

    /**
     * check pilotBody exist
     *
     * @param pilotId
     * @return
     */
    boolean checkPilotBodyExist(Long pilotId);

    /**
     * update pilotBody by pilotId
     *
     * @param pilotBody
     * @return
     */
    int updatePilotBodyByPilotId(PilotBody pilotBody);

    /**
     * delete pilotBody by PilotId
     *
     * @param pilotId
     * @param deleted logic delete flag
     * @return
     */
    int deletePilotBodyByPilotId(Long pilotId, Long deleted);

    /**
     * batch delete pilotId
     *
     * @param pilotId
     * @param deleted
     * @return
     */
    int batchDeletePilotBodyByPilotIds(List<Long> pilotId, Long deleted);
}
