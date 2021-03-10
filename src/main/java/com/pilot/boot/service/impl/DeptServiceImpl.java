package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public int addDept(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public boolean checkDeptExist(String deptName) {
        return deptDao.selectDeptNameExist(deptName) == 0;
    }

    @Override
    public Long selectDeptIdByDeptName(String deptName) {
        return deptDao.selectDeptIdByDeptName(deptName);
    }

    @Override
    public Dept findDeptByDeptId(Long deptId) {
        return deptDao.selectById(deptId);
    }

    @Override
    public List<Dept> findAllDept() {
        return deptDao.selectList(null);
    }

    @Override
    public IPage<Dept> findDeptWithPage(Page<Dept> deptPage) {
        return deptDao.selectPage(deptPage,null);
    }

    @Override
    public int updateDeptByDeptId(Dept dept) {
        return deptDao.updateById(dept);
    }

    @Override
    public int deleteDeptByDeptId(Long deptId) {
        return deptDao.deleteById(deptId);
    }
}
