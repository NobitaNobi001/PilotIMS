package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.DeptDao;
import com.pilot.boot.entity.Dept;
import com.pilot.boot.service.DeptService;
import org.springframework.cache.annotation.CacheConfig;
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
     * add a dept
     *
     * @param dept
     * @return
     */
    @Override
    public int addDept(Dept dept) {
        return deptDao.insert(dept);
    }

    /**
     * check dept exist
     *
     * @param deptName
     * @return
     */
    @Override
    public boolean checkDeptExist(String deptName) {
        return deptDao.selectDeptNameExist(deptName) == 0;
    }

    /**
     * select deptId by deptName
     *
     * @param deptName
     * @return
     */
    @Override
    public Long selectDeptIdByDeptName(String deptName) {
        return deptDao.selectDeptIdByDeptName(deptName);
    }

    /**
     * find dept by deptId
     * @param deptId
     * @return
     */
    @Override
    public Dept findDeptByDeptId(Long deptId) {
        return deptDao.selectById(deptId);
    }

    /**
     * find all dept
     * @return
     */
    @Override
    public List<Dept> findAllDept() {
        return deptDao.selectList(null);
    }

    /**
     * select dept with page
     * @param deptPage
     * @return
     */
    @Override
    public IPage<Dept> findDeptWithPage(Page<Dept> deptPage) {
        return deptDao.selectPage(deptPage,null);
    }

    /**
     * update dept by DeptId
     * @param dept
     * @return
     */
    @Override
    public int updateDeptByDeptId(Dept dept) {
        return deptDao.updateById(dept);
    }

    /**
     * delete dept by deptId
     * @param deptId
     * @return
     */
    @Override
    public int deleteDeptByDeptId(Long deptId) {
        return deptDao.deleteById(deptId);
    }
}
