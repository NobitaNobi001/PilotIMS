package com.pilot.boot.service.impl;

import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Service
public class PilotBodyServiceImpl implements PilotBodyService {

    @Resource
    private PilotBodyDao pilotBodyDao;

    /**
     * find all pilotBody
     * @return pilotBodys
     */
    @Override
    public List<PilotBody> findAllPilotBody() {
        return null;
    }
}
