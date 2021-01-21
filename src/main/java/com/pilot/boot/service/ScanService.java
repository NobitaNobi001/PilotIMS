package com.pilot.boot.service;

import com.pilot.boot.entity.Scan;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
public interface ScanService {

    /**
     * find all scan
     * @return scans
     */
    List<Scan> findAllScan();
}
