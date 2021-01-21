package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.PilotDao;
import com.pilot.boot.entity.Pilot;
import com.pilot.boot.service.PilotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:46
 */
@Service
public class PilotServiceImpl implements PilotService {

    @Resource
    private PilotDao pilotDao;


    /**
     * find all pilot with page
     * @return
     */
    @Override
    public IPage<Pilot> findAllPilotWithPage(Page<Pilot> pilotPage) {
        return pilotDao.selectPage(pilotPage,null);
    }
}
