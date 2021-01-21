package com.pilot.boot.service.impl;

import com.pilot.boot.dao.DeptDao;
import com.pilot.boot.entity.Dept;
import com.pilot.boot.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptDao deptDao;


    /**
     * find all dept
     * @return depts
     */
    @Override
    public List<Dept> findAllDept() {
        return null;
    }
}
