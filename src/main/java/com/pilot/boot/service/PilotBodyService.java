package com.pilot.boot.service;

import com.pilot.boot.entity.PilotBody;

import java.util.List;

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
}
