package com.pilot.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pilot.boot.entity.Scan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface ScanDao extends BaseMapper<Scan> {

    /**
     * find scan with pilot
     * @param pilotId
     * @return
     */
    Scan selectScanWithPilotByPilotId(@Param("pilotId") Long pilotId);

    /**
     * batch select by pilotId
     * @param pilotId
     * @return
     */
    List<Scan> selectBatchPilotId(@Param("pilotIds") List<Long> pilotId);

    /**
     * get all scans
     * @return
     */
    List<Scan> selectAllScan();

    /**
     * get all scans wth page
     * @param var
     * @return
     */
    IPage<Scan> selectAllScanWithPage(IPage<Scan> var);

    /**
     * check scan record exist
     * @param pilotId
     * @return
     */
    Long selectScanExist(@Param("pilotId") Long pilotId);

    /**
     * update a scan by pilotId
     * @param scan
     * @return
     */
    int updateScanByPilotId(Scan scan);


}
