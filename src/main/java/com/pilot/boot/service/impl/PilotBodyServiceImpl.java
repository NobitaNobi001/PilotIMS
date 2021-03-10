package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.service.PilotBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Service
public class PilotBodyServiceImpl implements PilotBodyService {

    @Resource
    private PilotBodyDao pilotBodyDao;

    @Override
    public int addPilotBodyByPilotId(PilotBody pilotBody) {
        return pilotBodyDao.insertPilotBody(pilotBody);
    }

    @Override
    public int batchInsertPilotBody(List<PilotBody> pilotBodies) {
        return pilotBodyDao.batchInsertPilotBody(pilotBodies);
    }

    @Override
    public IPage findAllPilotBody(Page<PilotBody> pilotBodyPage) {
        return pilotBodyDao.selectPilotBodyWithPage(pilotBodyPage);
    }

    @Override
    public PilotBody findPilotBodyByPilotId(Long pilotId) {

        //1.find operation
        PilotBody pilotBody = pilotBodyDao.findPilotBodyByPilotId(pilotId);
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
    public boolean checkPilotBodyExist(Long pilotId) {
        return pilotBodyDao.checkPilotBodyExist(pilotId) == 1;
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

    /**
     * TODO logic delete and delete
     * @param pilotId
     * @return
     */
    @Override
    public int deletePilotBodyByPilotId(Long pilotId) {
        return pilotBodyDao.updatePilotBodyWithLogicDelete(pilotId);
    }

    @Override
    public int batchDeletePilotBodyByPilotIds(List<Long> pilotId) {
        return pilotBodyDao.deleteBatchIds(pilotId);
    }
}
