package com.pilot.boot.controller;

import com.pilot.boot.entity.AdditionField;
import com.pilot.boot.service.AdditionFieldService;
import com.pilot.boot.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ezuy
 * @date 21/3/2 15:06
 */
@Valid
@CrossOrigin
@RestController
public class AdditionFieldController {

    @Autowired
    private AdditionFieldService additionFieldService;

    @PutMapping("/field/add")
    public CommonResult addField(@Valid @RequestBody AdditionField additionField) {
        additionFieldService.updateAdditionField(additionField);
        return CommonResult.success(additionField);
    }


    @GetMapping("/field/list")
    public CommonResult listAllField() {
        List<AdditionField> additionFields = additionFieldService.selectAllField();
        return CommonResult.success(additionFields);
    }

}
