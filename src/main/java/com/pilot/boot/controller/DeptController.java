package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.Dept;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.service.DeptService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:50
 */
@Slf4j
@CrossOrigin
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public CommonResult AddDept(@Valid @RequestBody Dept dept) {
        //1. get service result
        int result = deptService.addDept(dept);
        //2.response to front
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }
        return CommonResult.success(dept);
    }

    @PostMapping("/dept/check")
    public CommonResult checkDeptExist(@RequestBody Map<String, String> dept) {

        String deptName = dept.get(ConstantUtil.deptName.toString());

        //1.check format
        Assert.validDeptName(deptName,CommonResult.fail(100,"部门名称格式不正确"));

        //2.check deptName exist
        if (deptService.checkDeptExist(deptName)) {
            return new CommonResult(200, "部门名称可用");
        }

        return CommonResult.fail(100,"部门名称已存在");
    }

    @GetMapping("/dept/get/{deptId}")
    public CommonResult getDeptByDeptId(@PathVariable("deptId") Long deptId) {

        //1.get dept
        Dept dept = deptService.findDeptByDeptId(deptId);

        //2.check and response
        Assert.notNull(dept,CommonResult.fail(100,"此部门不存在"));

        return CommonResult.success(dept);

    }

    @GetMapping("/dept/list")
    public CommonResult getAllDept(@RequestParam(name = "current", required = false) Long current,
                                   @RequestParam(name = "size", required = false) Long size) {

        //1.check page or not
        if (current == null || size == null) {

            //2.get query result
            List<Dept> depts = deptService.findAllDept();

            //3.response to front
            return CommonResult.success(depts);

        } else {
            //2.Encapsulate the dept page
            Page<Dept> deptPage = new Page<>(current, size);

            //3.get query result
            IPage<Dept> deptIPage = deptService.findDeptWithPage(deptPage);

            //4.response to front
            return CommonResult.success(deptIPage);
        }
    }

    @PutMapping("/dept/update")
    public CommonResult updateDept(@Valid @RequestBody Dept dept) {

        //1.get result
        int result = deptService.updateDeptByDeptId(dept);

        //2.response to front
        if (result == 0) {
            return CommonResult.fail(100, "更新失败,请检查格式信息");
        }
        return CommonResult.success(dept);
    }

    @DeleteMapping("/dept/delete")
    public CommonResult deleteDept(@RequestBody Map<String, Long> dept) {

        //1.check deptId and get deptId
        Long deptId = dept.get(ConstantUtil.deptId.toString());

        Assert.notNull(deptId,CommonResult.fail(100,"数据格式不正确"));

        //3.operation
        int result = deptService.deleteDeptByDeptId(deptId);

        //4.check result and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

}
