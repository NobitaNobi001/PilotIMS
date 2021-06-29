package com.pilot.boot.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Dept;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:44
 */
public interface DeptService{

    /**
     * add a dept
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * check dept exist
     * @param deptName
     */
    boolean checkDeptExist(String deptName);

    /**
     * select deptId by deptName
     * @param deptName
     * @return
     */
    Long selectDeptIdByDeptName(String deptName);

    /**
     * find a dept by deptId
     * @param deptId
     * @return
     */
    Dept findDeptByDeptId(Long deptId);

    /**
     * check dept reference
     * @param deptId
     * @return
     */
    boolean checkDeptReference(Long deptId);


    /**
     * find all dept
     * @return
     */
    List<Dept> findAllDept();

    /**
     * find dept with page
     * @param deptPage
     * @return
     */
    IPage<Dept> findDeptWithPage(Page<Dept> deptPage);

    /**
     * update dept by deptId
     * @param dept
     * @return
     */
    int updateDeptByDeptId(Dept dept);

    /**
     * delete a dept
     * @param deptId
     * @return
     */
    int deleteDeptByDeptId(Long deptId);
}
