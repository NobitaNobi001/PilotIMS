package com.pilot.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pilot.boot.entity.AdditionField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ezuy
 * @date 21/3/2 14:51
 */
public interface AdditionFieldDao extends BaseMapper<AdditionField> {

    /**
     * get all blank field
     * @return
     */
    List<AdditionField> selectAllField();


    /**
     * find field by name
     * @param name
     * @return
     */
    int findFieldByName(@Param("name") String name);

    /**
     * update addition field
     * @param additionField
     * @return
     */
    int updateAdditionField(@Param("additionField") AdditionField additionField);

}
