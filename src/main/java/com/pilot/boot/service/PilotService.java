package com.pilot.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Pilot;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:44
 */
public interface PilotService {

    /**
     * find all pilot and page
     * @return pilots
     */
    IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage);
}
