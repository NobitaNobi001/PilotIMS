package com.pilot.boot.service.impl;

import com.pilot.boot.dao.ScanDao;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.service.ScanService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:47
 */
@CacheConfig(cacheNames = "scan")
@Service
public class ScanServiceImpl implements ScanService {

    @Resource
    private ScanDao scanDao;

    /**
     * find all scan
     * @return scans
     */
    @Override
    public List<Scan> findAllScan() {
        return null;
    }
}
