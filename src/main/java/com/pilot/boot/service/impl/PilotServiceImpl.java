package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.service.PilotService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@CacheConfig(cacheNames = "pilot")
@Service
public class PilotServiceImpl implements PilotService {

    @Resource
    private PilotDao pilotDao;

    @Override
    public int addPilot(Pilot pilot) {
        return pilotDao.insert(pilot);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddPilot(List<Pilot> pilots) {
        return pilotDao.batchInsertPilot(pilots);
    }

    @Override
    public IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage,String pilotName) {
        return pilotDao.selectPilotPage(pilotPage,pilotName);
    }

    @Override
    public List<Pilot> findAllPilot() {
        return pilotDao.selectAllPilot();
    }

    @Override
    public Pilot findPilotById(Long pilotId) {
        return pilotDao.findPilotWithDeptByPilotId(pilotId);
    }

    @Override
    public int updatePilot(Pilot pilot) {
        return pilotDao.updateById(pilot);
    }

    @Override
    public int deletePilotByPilotId(Long pilotId) {
        return pilotDao.deleteById(pilotId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDeletePilot(List<Long> pilotIds) {
        return pilotDao.deleteBatchIds(pilotIds);
    }
}
