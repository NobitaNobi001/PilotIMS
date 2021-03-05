package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.ConstantUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    public int addPilotBodyByPilotId(PilotBody pilotBody) {
        return pilotBodyDao.insert(pilotBody);
    }

    @Override
    public IPage findAllPilotBody(Page<PilotBody> pilotBodyPage) {
        return pilotBodyDao.selectPage(pilotBodyPage, null);
    }

    @Override
    public PilotBody findPilotBodyByPilotId(Long pilotId) {

        //1.find operation
        PilotBody pilotBody = pilotBodyDao.selectById(pilotId);
        //2.check and response
        if (pilotBody == null) {
            return null;
        }
        return pilotBody;
    }

    @Override
    public IPage findPilotBodyWithCondition(Page<PilotBody> pilotBodyPage, Map<String, List<Long>> listCondition) {
        return pilotBodyDao.selectPilotBodyWithCondition(pilotBodyPage, listCondition);
    }

    @Override
    public boolean checkPilotBodyExist(Map<String, Long> pilotIdMap) {

        //1.get pilotId
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());
        //2.return to controller
        return pilotId.equals(pilotBodyDao.checkPilotBodyExist(pilotId));
    }

    @Override
    public int updatePilotBodyByPilotId(PilotBody pilotBody) {

        //1.update operation
        int result = pilotBodyDao.updateById(pilotBody);

        //2.check and response
        if (result == 0) {
            return result;
        }
        return result;
    }

    @Override
    public int deletePilotBodyByPilotId(Long pilotId) {
        return 0;
    }

    @Override
    public int batchDeletePilotBodyByPilotIds(List<Long> pilotId) {
        return pilotBodyDao.deleteBatchIds(pilotId);
    }
}
