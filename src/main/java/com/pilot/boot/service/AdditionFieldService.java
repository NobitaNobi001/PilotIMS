package com.pilot.boot.service;

import com.pilot.boot.entity.AdditionField;

import java.util.List;

/**
 * @author ezuy
 * @date 21/3/2 15:04
 */
public interface AdditionFieldService {

    /**
     * select all blank field
     * @return
     */
    List<AdditionField> selectAllField();

    /**
     * add addition field
     * @param additionField
     * @return
     */
    int updateAdditionField(AdditionField additionField);
}
