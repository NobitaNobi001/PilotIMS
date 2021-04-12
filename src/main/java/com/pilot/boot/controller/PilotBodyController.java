package com.pilot.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.PilotBody;
import com.pilot.boot.exception.Assert;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.PilotBodyService;
import com.pilot.boot.utils.CheckFileFormat;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.InputStream;
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

    @PostMapping("/pilotBody/add/excel")
    public CommonResult addPilotBodyByExcel(@RequestParam("file") MultipartFile file) {

        //1.check file
        CheckFileFormat.checkExcelFile(file);

        InputStream inputStream;

        try {
            inputStream = file.getInputStream();

            pilotBodyService.importPilotBody(inputStream);

            return CommonResult.success("导入成功", "");

        } catch (MyException e) {
            throw new MyException(CommonResult.fail(e.getCode(),e.getMessage()));
        }catch (Exception e){
            throw new MyException(CommonResult.fail(100,"文件上传错误"));
        }
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
        Assert.notNull(pilotId, CommonResult.fail(100, "飞行员id为null"));

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
        Assert.notNull(pilotBody, CommonResult.fail(100, "不存在此飞行员的体型数据信息"));
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

        Assert.notNull(pilotId, CommonResult.fail(100, "飞行员id为null"));

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
     *
     * @param pilotIdMap
     * @return
     */
    @DeleteMapping("/pilotBody/delete")
    public CommonResult deletePilotBodyById(@RequestBody Map<String, Long> pilotIdMap) {

        //1.get pilotId and deleted check
        Long pilotId = pilotIdMap.get(ConstantUtil.pilotId.toString());
        Long deleted = pilotIdMap.get(ConstantUtil.deleted.toString());

        Assert.notNull(pilotId, CommonResult.fail(100, "飞行员id为null"));
        Assert.notNull(deleted, CommonResult.fail(100, "飞行员id为null"));

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

        Assert.notNull(pilotId, CommonResult.fail(100, "飞行员id为null"));
        Assert.notNull(deleted, CommonResult.fail(100, "飞行员id为null"));

        //2.delete operation
        int result = pilotBodyService.batchDeletePilotBodyByPilotIds(pilotId, deleted);
        //3.check and response
        if (result == 0) {
            return CommonResult.fail(100, "删除失败");
        }
        return CommonResult.success("删除成功");
    }
}
