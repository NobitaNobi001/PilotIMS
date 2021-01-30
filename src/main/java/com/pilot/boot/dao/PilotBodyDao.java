package com.pilot.boot.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pilot.boot.entity.PilotBody;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface PilotBodyDao extends BaseMapper<PilotBody> {

    /**
     * check pilotBody exist
     * @param pilotId
     * @return
     */
    Long checkPilotBodyExist(@Param("pilotId") Long pilotId);
}
