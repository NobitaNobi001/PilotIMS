package com.pilot.boot.service;

import com.pilot.boot.entity.PilotBody;

import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
public interface PilotBodyService {

    /**
     * find all pilotBody
     * @return pilotBodys
     */
    List<PilotBody> findAllPilotBody();

    /**
     * check pilotBody exist
     * @param pilotIdMap
     * @return
     */
    boolean checkPilotBodyExist(Map<String,Long> pilotIdMap);
}
