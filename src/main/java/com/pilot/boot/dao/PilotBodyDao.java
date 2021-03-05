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
     * check pilotBody exist
     *
     * @param pilotId
     * @return
     */
    Long checkPilotBodyExist(@Param("pilotId") Long pilotId);

    /**
     * select pilotBody with condition
     * @param pilotBodyIPage
     * @param listCondition
     * @return
     */
    IPage<PilotBody> selectPilotBodyWithCondition(IPage<PilotBody> pilotBodyIPage,@Param("listCondition") Map<String, List<Long>> listCondition);
}
