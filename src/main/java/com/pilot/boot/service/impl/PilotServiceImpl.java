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

    /**
     * add a pilot
     *
     * @param pilot
     * @return
     */
    @Override
    public int addPilot(Pilot pilot) {
        return pilotDao.insert(pilot);
    }

    /**
     * batch add pilot
     * @param pilots
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddPilot(List<Pilot> pilots) {
        return pilotDao.batchInsertPilot(pilots);
    }

    /**
     * find all pilot with page
     *
     * @return
     */
    @Override
    public IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage) {
        return pilotDao.selectPilotPage(pilotPage);
    }

    /**
     * select all pilot
     * @return
     */
    @Override
    public List<Pilot> findAllPilot() {
        return pilotDao.selectAllPilot();
    }

    /**
     * find pilot by pilotId
     *
     * @param pilotId pilotId
     * @return
     */
    @Override
    public Pilot findPilotById(Long pilotId) {
        return pilotDao.findPilotWithDeptByPilotId(pilotId);
    }

    /**
     * update pilot
     *
     * @param pilot
     * @return
     */
    @Override
    public int updatePilot(Pilot pilot) {
        return pilotDao.updateById(pilot);
    }

    /**
     * delete pilot by pilotId
     *
     * @param pilotId
     * @return
     */
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
