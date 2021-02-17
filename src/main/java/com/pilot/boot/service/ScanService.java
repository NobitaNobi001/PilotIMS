package com.pilot.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.utils.CommonResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
public interface ScanService {

    /**
     * add a scan
     *
     * @param scan
     * @param file
     * @return
     */
    CommonResult addScan(Scan scan, MultipartFile file);

    /**
     * find scan by pilotId
     *
     * @param pilotId
     * @return
     */
    Scan findScanByPilotId(Long pilotId);

    /**
     * find all scan
     *
     * @return scans
     */
    List<Scan> findAllScan();

    /**
     * find all scan with page
     *
     * @param scanPage
     * @return
     */
    IPage<Scan> findScanWithPage(Page<Scan> scanPage);

    /**
     * update scan by pilotId
     *
     * @param scan
     * @return
     */
    int updateScanByPilotId(Scan scan);

    /**
     * delete scan by pilotId
     *
     * @param pilotId
     * @return
     */
    int deleteScanByPilotId(Long pilotId);

    /**
     * batch delete scans
     *
     * @param pilotIds
     * @return
     */
    int batchDeleteScans(List<Long> pilotIds);
}
