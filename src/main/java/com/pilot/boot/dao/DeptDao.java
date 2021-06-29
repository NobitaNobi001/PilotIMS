package com.pilot.boot.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pilot.boot.entity.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * @author ezuy
 * @date 20/12/22 15:47
 */
public interface DeptDao extends BaseMapper<Dept> {

    /**
     * select deptId by deptName
     * @param deptName
     * @return
     */
    Long selectDeptIdByDeptName(@Param("deptName") String deptName);

    /**
     * check deptName exist
     * @param deptName
     * @return
     */
    Long selectDeptNameExist(@Param("deptName") String deptName);

    /**
     * select dept reference
     * @param deptId
     * @return
     */
    Long selectDeptReference(@Param("deptId") Long deptId);
}
