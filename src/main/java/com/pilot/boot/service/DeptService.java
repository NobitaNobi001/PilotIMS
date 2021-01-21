package com.pilot.boot.service;

import com.pilot.boot.entity.Dept;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:44
 */
public interface DeptService {

    /**
     * find all dept
     * @return depts
     */
    List<Dept> findAllDept();
}
