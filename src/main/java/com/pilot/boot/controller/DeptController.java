package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Dept;
import com.pilot.boot.entity.Scan;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import com.pilot.boot.utils.ParamVerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * dept controller
 *
 * @author ezuy
 * @date 20/12/22 15:50
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * add a dept
     */
    @PostMapping("/dept/add")
    public CommonResult AddDept(@Valid @RequestBody Dept dept) {
        //1. get service result
        int result = deptService.addDept(dept);
        //2.response to front
        if (result == 0) {
            return new CommonResult(100, "添加失败", dept);
        }
        return new CommonResult(200, "添加" + result + "条部门信息", dept);
    }

    @PostMapping("/dept/check")
    public CommonResult checkDeptExist(@RequestBody Map<String, String> dept) {

        String deptName = dept.get(ConstantUtil.deptName.toString());

        //1.check format
        if (!ParamVerifyUtil.verifyDeptName(deptName)) {
            throw new MyException("部门名称不正确!");
        }

        //2.check deptName exist
        if (deptService.checkDeptExist(deptName)) {
            return new CommonResult(200, "部门名称可用");
        }

        return new CommonResult(200, "部门名称已存在");
    }

    /**
     * get a dept
     *
     * @param deptId
     * @return
     */
    @GetMapping("/dept/get/{deptId}")
    public CommonResult getDeptByDeptId(@PathVariable("deptId") Long deptId) {

        //1.get dept
        Dept dept = deptService.findDeptByDeptId(deptId);

        //2.check and response
        if (dept == null) {
            return new CommonResult(200, "没有此部门");
        }

        return new CommonResult(200, "查询成功", dept);

    }

    /**
     * get all dept ? page : not page
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/dept/list")
    public CommonResult getAllDept(@RequestParam(name = "current", required = false) Long current, @RequestParam(name = "size", required = false) Long size) {

        //1.check page or not
        if (current == null || size == null) {

            //2.get query result
            List<Dept> depts = deptService.findAllDept();

            //3.response to front
            return new CommonResult(200, "查询成功", depts);

        } else {
            //2.Encapsulate the dept page
            Page<Dept> deptPage = new Page<>(current, size);

            //3.get query result
            IPage<Dept> deptIPage = deptService.findDeptWithPage(deptPage);

            //4.response to front
            return new CommonResult(200, "查询成功", deptIPage);
        }
    }

    /**
     * update a dept
     *
     * @param dept
     * @return
     */
    @PutMapping("/dept/update")
    public CommonResult updateDept(@Valid @RequestBody Dept dept) {

        //1.get result
        int result = deptService.updateDeptByDeptId(dept);

        //2.response to front
        if (result == 0) {
            return new CommonResult(100, "更新失败,请检查格式信息", dept);
        }
        return new CommonResult(200, "更新成功", dept);
    }

    /**
     * delete a dept
     *
     * @param dept
     * @return
     */
    @DeleteMapping("/dept/delete")
    public CommonResult deleteDept(@RequestBody Map<String, Long> dept) {

        //1.check deptId
        if (dept == null) {
            throw new MyException("所传数据为空!");
        }
        //2.get deptId
        Long deptId = dept.get(ConstantUtil.deptId.toString());

        //3.operation
        int result = deptService.deleteDeptByDeptId(deptId);

        //4.check result and response
        if (result == 0) {
            return new CommonResult(100, "删除失败");
        }
        return new CommonResult(200, "删除" + result + "条记录");
    }

}
