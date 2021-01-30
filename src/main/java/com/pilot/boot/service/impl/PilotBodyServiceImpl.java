package com.pilot.boot.service.impl;

import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.ConstantUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@CacheConfig(cacheNames = "pilotBody")
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

    /**
     * check pilotBody exist
     * @param pilotIdMap
     * @return
     */
    @Override
    public boolean checkPilotBodyExist(Map<String, Long> pilotIdMap) {

        //1.get pilotId
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());

        //2.return to controller
        return pilotId.equals(pilotBodyDao.checkPilotBodyExist(pilotId));
    }
}
