package com.pilot.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pilot.boot.entity.Pilot;

import java.io.InputStream;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:44
 */
public interface PilotService{

    /**
     * add a pilot
     *
     * @param pilot
     * @return
     */
    int addPilot(Pilot pilot);

    /**
     * batch import pilot
     * @param inputStream
     */
    void importPilot(InputStream inputStream);

    /**
     * batch add pilot
     * @param pilots
     * @return
     */
    int batchAddPilot(List<Pilot> pilots);

    /**
     * find all pilot and page
     *
     * @param pilotPage
     * @param pilotName
     * @return
     */
    IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage,String pilotName);

    /**
     * find all pilot
     * @return
     */
    List<Pilot> findAllPilot();

    /**
     * find pilot with id
     *
     * @param pilotId pilotId
     * @return Pilot
     */
    Pilot findPilotById(Long pilotId);

    /**
     * update pilot
     *
     * @param pilot
     * @return
     */
    int updatePilot(Pilot pilot);

    /**
     * delete pilot by pilotId
     *
     * @param pilotId
     * @return
     */
    int deletePilotByPilotId(Long pilotId);

    /**
     * batch delete pilot
     *
     * @param pilotIds
     * @return
     */
    int batchDeletePilot(List<Long> pilotIds);


}
