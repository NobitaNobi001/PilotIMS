package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotDao;
import com.pilot.boot.dao.ScanDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.exception.ServiceException;
import com.pilot.boot.service.ScanService;
import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:47
 */
@Slf4j
@Service
public class ScanServiceImpl implements ScanService {

    @Resource
    private ScanDao scanDao;
    @Resource
    private PilotDao pilotDao;

    private static final String SCAN_FILE_FORMAT = ".stl";

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public CommonResult addScan(Scan scan, MultipartFile file) {

        //1.check file size
        if (file.getSize() == 0) {
            return CommonResult.fail(100, "文件为空!");
        }

        //2.check file format
        //file origin name
        String fileOriginName = file.getOriginalFilename();
        //file format index
        int index = fileOriginName.lastIndexOf(".");
        //file format
        String endFormatName = fileOriginName.substring(index);

        if (!(SCAN_FILE_FORMAT).equals(endFormatName)) {
            return CommonResult.fail(100, "请上传.stl格式的文件");
        }

        //3.operation bind pilot
        Pilot pilot = pilotDao.findPilotWithDeptByPilotId(scan.getPilotId());

        //check
        if (pilot == null) {
            return CommonResult.fail(100, "预添加点云文件的飞行员信息不存在");
        }

        //4.rename file
        //date name and card ->rename
        String fileName = scan.getScanTime() + "-" + pilot.getPilotName() + "-" + pilot.getCard() + ".stl";

        //5.upload file
        //get absolutely path
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

        //jar package get target
        String upload = new File(path).getParentFile().getParentFile().getParentFile().toString();

        log.info(path);

        //save folder
        File folder = new File(upload + "/pilotScanInfo");
        log.info(folder.toString());


        //5.1 if folder not exist to create
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // window path
//        String filePath = folder.toString().substring(6) + "/" + fileName;
        // linux path
        String filePath = folder.toString().substring(5) + "/" + fileName;

        log.info(filePath);

        //5.2 bind file new info ->name & address
        scan.setDataFileName(fileName);
        scan.setFileStorageAddress(filePath);

        //6.insert operation
        int result = scanDao.insert(scan);

        if (result == 0) {
            return CommonResult.fail(100, "上传失败!");
        }

        //7.write file
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            outputStream.write(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.fail(100, "上传失败!");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //8.response
        return CommonResult.success("添加成功");
    }

    @Override
    public Scan findScanByPilotId(Long pilotId) {
        return scanDao.selectScanWithPilotByPilotId(pilotId);
    }

    @Override
    public List<Scan> findAllScan() {
        return scanDao.selectAllScan();
    }

    @Override
    public IPage<Scan> findScanWithPage(Page<Scan> scanPage) {
        return scanDao.selectAllScanWithPage(scanPage);
    }

    @Override
    public boolean checkScanExist(Long pilotId) {
        return scanDao.selectScanExist(pilotId) == 1;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public int updateScanByPilotId(Scan scan) {
        return scanDao.updateScanByPilotId(scan);
    }

    @Override
    public int deleteScanByPilotId(Long pilotId) {
        return scanDao.deleteById(pilotId);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public int batchDeleteScans(List<Long> pilotIds) {

        //1.get all scans by pilotId
        List<Scan> scans = scanDao.selectBatchPilotId(pilotIds);

        //2.records delete
        int result = scanDao.deleteBatchIds(pilotIds);

        //3.batch delete scanFile
        for (Scan scan : scans) {
            if (scan == null) {
                return 0;
            }
            File file = new File(scan.getFileStorageAddress());
            file.delete();
        }
        return result;
    }
}
