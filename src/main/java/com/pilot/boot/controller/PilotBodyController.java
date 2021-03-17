package com.pilot.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.listener.PilotBodyListener;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ezuy
 * @date 20/12/22 15:49
 */
@Slf4j
@Validated
@CrossOrigin
@RestController
public class PilotBodyController {

    @Autowired
    private PilotBodyService pilotBodyService;
    @Autowired
    private PilotBodyListener pilotBodyListener;

    @PostMapping("/pilotBody/add")
    public CommonResult addPilotBody(@Valid @RequestBody PilotBody pilotBody) {

        //1.insert operation
        int result = pilotBodyService.addPilotBodyByPilotId(pilotBody);

        //2.check and response
        if (result == 0) {
            return CommonResult.fail(100, "添加失败");
        }
        return CommonResult.success("添加成功");
    }

    /**
     * TODO pilotBody excel add
     * @param file
     * @return
     */
    @PostMapping("/pilotBody/add/excel")
    public CommonResult addPilotBodyByExcel(@RequestParam("file") CommonsMultipartFile file) {

        //1.check file == null
        if (file.getSize() == 0) {
            return CommonResult.fail(100, "请选择文件");
        }
        //2.get file suffix
        int begin = file.getOriginalFilename().indexOf(".");
        String suffix = file.getOriginalFilename().substring(begin);
        //3.check file format
        if (!(".xls").equals(suffix)) {
            return CommonResult.fail(100, "请上传xls格式文件");
        }

        try {
            //4.read excel
            //4.1 work sheet
            ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getInputStream(), PilotBodyListener.class, pilotBodyListener);
            //4.2 work table
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();
            //4.3 read table
            sheetBuilder.headRowNumber(1).doRead();
        } catch (Exception e) {

            //5.clear remain data
            pilotBodyListener.getPilotBodies().clear();
            //6.exact error
            if (e instanceof MyException) {
                return CommonResult.fail(100, e.getMessage());
            }
        }
        return CommonResult.success("添加成功");
    }

    @PostMapping("/pilotBody/condition/list")
    public CommonResult findPilotBodyWithCondition(@RequestParam(name = "current", defaultValue = "1") Long current,
                                                   @RequestParam(name = "size", defaultValue = "8") Long size,
                                                   @RequestBody Map<String, List<Long>> listCondition) {
        //1.Encapsulate the pilotBody page
        Page<PilotBody> pilotBodyPage = new Page<>(current, size);

        //2.find all pilotBody with page
        IPage<PilotBody> pilotBodyIPage = pilotBodyService.findPilotBodyWithCondition(pilotBodyPage, listCondition);

        //3.response to front
        return CommonResult.success(pilotBodyIPage);
    }

    @PostMapping("/pilotBody/check")
    public CommonResult checkPilotBodyExist(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get pilotId
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());
        if (null == pilotId || ("".equals(pilotId))) {
            return CommonResult.fail(100, "不存在此飞行员");
        }

        //2.get result
        boolean flag = pilotBodyService.checkPilotBodyExist(pilotId);

        //3.check and response
        if (flag) {
            return CommonResult.fail(100, "此飞行员体型数据信息已存在");
        }
        return CommonResult.success("此飞行员可以进行体型数据信息的添加");
    }

    @GetMapping("/pilotBody/list")
    public CommonResult findAllPilotBody(@RequestParam(name = "current", defaultValue = "1") Long current,
                                         @RequestParam(name = "size", defaultValue = "8") Long size,
                                         @RequestParam(name = "deleted", defaultValue = "0") Long deleted) {

        //1.Encapsulate the pilotBody page
        Page<PilotBody> pilotBodyPage = new Page<>(current, size);
        //2.find pilotBody with page
        IPage iPage = pilotBodyService.findAllPilotBody(pilotBodyPage, deleted);
        //3.response to front
        return CommonResult.success(iPage);
    }

    @GetMapping("/pilotBody/get/{pilotId}")
    public CommonResult findPilotBodyById(@PathVariable("pilotId") Long pilotId) {

        //1.get result
        PilotBody pilotBody = pilotBodyService.findPilotBodyByPilotId(pilotId);
        //2.check and response
        if (pilotBody == null) {
            return CommonResult.fail(100, "查无此人");
        }
        return CommonResult.success(pilotBody);
    }

    @PutMapping("/pilotBody/update")
    public CommonResult updatePilotBodyById(@Valid @RequestBody PilotBody pilotBody) {

        //1.update operation
        int result = pilotBodyService.updatePilotBodyByPilotId(pilotBody);
        //2.check and response
        if (result == 0) {
            return CommonResult.fail(100, "更新失败");
        }
        return CommonResult.success(pilotBody);
    }

    /**
     * recovery delete pilotBody data
     *
     * @param maps
     * @return
     */
    @PutMapping("/pilotBody/update/deleted")
    public CommonResult updateDeletedPilotBodyByPilotId(@RequestBody Map<String, Long> maps) {

        // 1.get pilotId
        Long pilotId = maps.get(ConstantUtil.pilotId.toString());
        if ("".equals(pilotId) || null == pilotId) {
            return CommonResult.fail(100, "id不能为空");
        }

        // 2.check
        boolean flag = pilotBodyService.checkPilotBodyExist(pilotId);

        if (!flag) {

            // 3.recovery
            int result = pilotBodyService.updateDeletedPilotBody(pilotId);

            // 4. check and response
            if (result != 0) {
                return CommonResult.success("恢复成功");
            }
        }
        return CommonResult.fail(100, "恢复失败");
    }

    /**
     * logic delete and physical delete
     * deleted -> 0 -> logic delete
     * deleted -> 1 -> physical delete
     * @param pilotIdMap
     * @return
     */
    @DeleteMapping("/pilotBody/delete")
    public CommonResult deletePilotBodyById(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get pilotId and deleted check
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());
        Long deleted = pilotIdMap.get(ConstantUtil.deleted.toString());
        if ("".equals(pilotId) || pilotId == null || "".equals(deleted) || deleted == null) {
            return CommonResult.fail(100, "参数错误");
        }

        //2.delete operation
        int result = pilotBodyService.deletePilotBodyByPilotId(pilotId, deleted);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

    /**
     * logic delete and physical batch delete
     *
     * @param pilotIdsMap
     * @return
     */
    @DeleteMapping("/pilotBody/batchDelete")
    public CommonResult deletePilotBodyByIds(@RequestBody Map<String, List<Long>> pilotIdsMap) {

        //1.get pilotIds and deleted and check
        List<Long> pilotId = pilotIdsMap.get(ConstantUtil.pilotId.toString());
        Long deleted = pilotIdsMap.get(ConstantUtil.deleted.toString()).get(0);
        if ("".equals(pilotId) || pilotId == null || "".equals(deleted) || deleted == null) {
            return CommonResult.fail(100, "参数错误");
        }

        //2.delete operation
        int result = pilotBodyService.batchDeletePilotBodyByPilotIds(pilotId, deleted);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }

}
