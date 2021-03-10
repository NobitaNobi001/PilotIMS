package com.pilot.boot.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pilot.boot.entity.PilotBody;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface PilotBodyDao extends BaseMapper<PilotBody> {


    /**
     * insert a pilotBody
     * @param pilotBody
     * @return
     */
    int insertPilotBody(PilotBody pilotBody);

    /**
     * batch insert pilotBody
     * @param pilotBodies
     * @return
     */
    int batchInsertPilotBody(@Param("list") List<PilotBody> pilotBodies);

    /**
     * find pilotBody by pilotId
     * @param pilotId
     * @return
     */
    PilotBody findPilotBodyByPilotId(@Param("pilotId") Long pilotId);

    /**
     * check pilotBody exist
     *
     * @param pilotId
     * @return
     */
    int checkPilotBodyExist(@Param("pilotId") Long pilotId);

    /**
     * select pilotBody with condition
     * @param pilotBodyIPage
     * @param listCondition
     * @return
     */
    IPage<PilotBody> selectPilotBodyWithCondition(IPage<PilotBody> pilotBodyIPage,@Param("listCondition") Map<String, List<Long>> listCondition);

    /**
     * select pilotBody with page
     * @param pilotBodyIPage
     * @return
     */
    IPage<PilotBody> selectPilotBodyWithPage(IPage<PilotBody> pilotBodyIPage);

    /**
     * update pilotBody by pilotId
     * @param pilotBody
     * @return
     */
    int updatePilotBodyByPilotId(PilotBody pilotBody);

    /**
     * logic delete
     * @param pilotId
     * @return
     */
    int updatePilotBodyWithLogicDelete(@Param("pilotId") Long pilotId);

    /**
     * batch logic delete
     * @param pilotIdMaps
     * @return
     */
    int updateBatchPilotBodyWithLogicDelete(@Param("list") List<Long> pilotIdMaps);

}
