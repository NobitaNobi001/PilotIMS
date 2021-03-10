package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.dao.PilotDao;
import com.pilot.boot.dao.ScanDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.exception.ServiceException;
import com.pilot.boot.service.PilotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Service
public class PilotServiceImpl implements PilotService {

    @Resource
    private PilotDao pilotDao;
    @Resource
    private PilotBodyDao pilotBodyDao;
    @Resource
    private ScanDao scanDao;

    @Override
    public int addPilot(Pilot pilot) {
        return pilotDao.insert(pilot);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public int batchAddPilot(List<Pilot> pilots) {
        return pilotDao.batchInsertPilot(pilots);
    }

    @Override
    public IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage, String pilotName) {
        return pilotDao.selectPilotPage(pilotPage, pilotName);
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

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public int deletePilotByPilotId(Long pilotId) {

        //1.get and delete scan record
        Scan scan = scanDao.selectScanWithPilotByPilotId(pilotId);
        int result = scanDao.deleteById(pilotId);

        //2.delete pilotBody
        result = pilotBodyDao.deleteById(pilotId);

        //3.delete pilot
        result = pilotDao.deleteById(pilotId);

        //4.delete file
        String address = scan.getFileStorageAddress();
        File file = new File(address);
        if (file.isFile()) {
            file.delete();
        } else {
            throw new ServiceException("点云文件不存在,操作失败");
        }
        return result;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public int batchDeletePilot(List<Long> pilotIds) {

        int result = 0;

        for (Long pilotId : pilotIds) {
            //1.get and delete scan record
            Scan scan = scanDao.selectScanWithPilotByPilotId(pilotId);

            result = scanDao.deleteById(pilotId);

            //2.delete pilotBody
            result = pilotBodyDao.deleteById(pilotId);

            //3.delete pilot
            result = pilotDao.deleteById(pilotId);

            //4.delete file
            String address = scan.getFileStorageAddress();
            File file = new File(address);
            if (file.isFile()) {
                file.delete();
            } else {
                throw new ServiceException("编号为" + pilotId + "的点云文件不存在");
            }
        }
        return result;
    }
}
