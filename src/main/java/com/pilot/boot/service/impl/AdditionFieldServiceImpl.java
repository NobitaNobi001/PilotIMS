package com.pilot.boot.service.impl;

import com.pilot.boot.dao.AdditionFieldDao;
import com.pilot.boot.entity.AdditionField;
import com.pilot.boot.service.AdditionFieldService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ezuy
 * @date 21/3/2 15:04
 */
@Service
public class AdditionFieldServiceImpl implements AdditionFieldService {

    @Resource
    private AdditionFieldDao additionFieldDao;

    @Override
    public List<AdditionField> selectAllField() {
        return additionFieldDao.selectAllField();
    }

    @Override
    public int updateAdditionField(AdditionField additionField) {
        return additionFieldDao.updateAdditionField(additionField);
    }
}
