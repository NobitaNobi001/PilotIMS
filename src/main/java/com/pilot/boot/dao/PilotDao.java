package com.pilot.boot.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface PilotDao extends BaseMapper<Pilot> {

    /**
     * batch insert pilot
     * @param pilots
     * @return
     */
    int batchInsertPilot(@Param("list") List<Pilot> pilots);

    /**
     * find pilot with dept by pilotId
     * @param pilotId
     * @return
     */
    Pilot findPilotWithDeptByPilotId(@Param("pilotId") Long pilotId);

    /**
     * select pilot page
     * @param var
     * @param pilotName
     * @return
     */
    IPage<Pilot> selectPilotPage(Page<Pilot> var, @Param("pilotName") String pilotName);

    /**
     * select all pilot
     * @return
     */
    List<Pilot> selectAllPilot();

    /**
     * find pilot by name and card
     * @param pilotName
     * @param card
     * @return
     */
    int findPilotByPilotNameAndCard(@Param("pilotName") String pilotName,@Param("card") String card);

    /**
     * check pilot exist by pilotId
     * @param pilotId
     * @return
     */
    int checkPilotExist(@Param("pilotId") Long pilotId);
}
