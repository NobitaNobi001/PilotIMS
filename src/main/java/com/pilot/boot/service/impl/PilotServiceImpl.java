package com.pilot.boot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.dao.PilotDao;
import com.pilot.boot.dao.ScanDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.entity.excel.PilotExcel;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.exception.ServiceException;
import com.pilot.boot.listener.PilotListener;
import com.pilot.boot.service.PilotService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Slf4j
@Service
public class PilotServiceImpl implements PilotService {

    @Resource
    private PilotDao pilotDao;
    @Resource
    private PilotBodyDao pilotBodyDao;
    @Resource
    private ScanDao scanDao;

    @Autowired
    private PilotListener pilotListener;

    @Override
    public int addPilot(Pilot pilot) {
        return pilotDao.insert(pilot);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importPilot(InputStream inputStream) {
        EasyExcel.read(inputStream, PilotExcel.class, pilotListener).sheet().headRowNumber(1).doRead();
        log.info("导入成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddPilot(List<Pilot> pilots) {
        return pilotDao.batchInsertPilot(pilots);
    }

    @Override
    public boolean checkPilotExist(String pilotName, String card) {
        return pilotDao.findPilotByPilotNameAndCard(pilotName, card) == 1;
    }

    @Override
    public boolean checkPilotExistByPilotId(Long pilotId) {
        return pilotDao.checkPilotExist(pilotId) == 1;
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

        int result = 0;

        //1.get and delete scan record
        Scan scan = scanDao.selectScanWithPilotByPilotId(pilotId);

        if (scan != null) {
            result = scanDao.deleteById(pilotId);
        }

        PilotBody pilotBody = pilotBodyDao.findPilotBodyByPilotId(pilotId);
        //2.delete pilotBody
        if (pilotBody != null) {
            result = pilotBodyDao.updatePilotBodyWithLogicDelete(pilotId);
        }

        //3.delete pilot
        result = pilotDao.deleteById(pilotId);

        if (scan != null) {
            //4.delete file
            String address = scan.getFileStorageAddress();
            File file = new File(address);
            if (file.isFile()) {
                file.delete();
            } else {
                throw new ServiceException("点云文件不存在,操作失败");
            }
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDeletePilot(List<Long> pilotIds) {

        int result = 0;

        for (Long pilotId : pilotIds) {
            //1.get and delete scan record
            Scan scan = scanDao.selectScanWithPilotByPilotId(pilotId);

            result = scanDao.deleteById(pilotId);

            //2.delete pilotBody
            result = pilotBodyDao.updatePilotBodyWithLogicDelete(pilotId);

            //3.delete pilot
            result = pilotDao.deleteById(pilotId);

            //4.delete file
            Assert.notNull(scan, CommonResult.fail(100, "飞行员编号为" + pilotId + "点云文件信息不存在"));
            String address = scan.getFileStorageAddress();
            File file = new File(address);
            Assert.isTrue(file.isFile(), CommonResult.fail(100, "编号为" + pilotId + "的点云文件不存在"));
            file.delete();
        }
        return result;
    }
}
