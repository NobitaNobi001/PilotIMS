package com.pilot.boot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.listener.PilotBodyListener;
import com.pilot.boot.service.PilotBodyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Slf4j
@Service
public class PilotBodyServiceImpl implements PilotBodyService {

    @Resource
    private PilotBodyDao pilotBodyDao;

    @Autowired
    private PilotBodyListener pilotBodyListener;

    @Override
    public int addPilotBodyByPilotId(PilotBody pilotBody) {
        return pilotBodyDao.insertPilotBody(pilotBody);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importPilotBody(InputStream inputStream) {
        EasyExcel.read(inputStream, PilotBody.class, pilotBodyListener).sheet().headRowNumber(1).doRead();
        log.info("导入成功");
    }

    @Override
    public int batchInsertPilotBody(List<PilotBody> pilotBodies) {
        return pilotBodyDao.batchInsertPilotBody(pilotBodies);
    }

    @Override
    public IPage findAllPilotBody(Page<PilotBody> pilotBodyPage, Long deleted) {
        return pilotBodyDao.selectPilotBodyWithPage(pilotBodyPage, deleted);
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

        //1. foreach delete "" || null
        Iterator<String> item = listCondition.keySet().iterator();
        while (item.hasNext()) {
            if ("".equals(item.next())) {
                item.remove();
            }
        }

        if (listCondition.size() == 0) {
            return pilotBodyDao.selectPilotBodyWithPage(pilotBodyPage,0L);
        }

        return pilotBodyDao.selectPilotBodyWithCondition(pilotBodyPage, listCondition);
    }

    @Override
    public boolean checkPilotBodyExist(Long pilotId) {
        return pilotBodyDao.checkPilotBodyExist(pilotId) == 1;
    }

    @Override
    public int updatePilotBodyByPilotId(PilotBody pilotBody) {

        //1.update operation
        int result = pilotBodyDao.updatePilotBodyByPilotId(pilotBody);

        //2.check and response
        return result;
    }

    @Override
    public int updateDeletedPilotBody(Long pilotId) {

        //1.update operation
        int result = pilotBodyDao.updateDeletePilotBody(pilotId);

        //2.check and response
        return result;
    }

    /**
     * logic delete and physical delete
     * logic delete -> deleted = 0
     * physical delete -> deleted = 1
     *
     * @param pilotId
     * @param deleted
     * @return
     */
    @Override
    public int deletePilotBodyByPilotId(Long pilotId, Long deleted) {

        // check and response
        if (deleted == 0) {
            return pilotBodyDao.updatePilotBodyWithLogicDelete(pilotId);
        } else {
            return pilotBodyDao.deletePilotBodyByPilotId(pilotId);
        }
    }

    @Override
    public int batchDeletePilotBodyByPilotIds(List<Long> pilotId, Long deleted) {

        // check and operation
        if (deleted == 0) {
            return pilotBodyDao.updateBatchPilotBodyWithLogicDelete(pilotId);
        } else {
            return pilotBodyDao.deleteBatchPilotBodyByPilotId(pilotId);
        }
    }
}
