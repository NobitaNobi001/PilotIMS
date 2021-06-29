package com.pilot.boot;

import com.pilot.boot.dao.PilotBodyDao;
import com.pilot.boot.service.PilotBodyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

/**
 * pilotBody test operation
 *
 * @author ezuy
 * @date 21/1/21 13:31
 */
@SpringBootTest
public class PilotBodyTest {

    @Resource
    private PilotBodyService pilotBodyService;

    @Resource
    private PilotBodyDao pilotBodyDao;

//    @Test
    void testPilotBodyConditionQuery() {

        Map<String, List<Long>> condition = new HashMap<>();

        List<Long> demo = new ArrayList<>();

        demo.add(1L);
        demo.add(2L);

        condition.put("height", demo);
        condition.put("weight", demo);
        condition.put("back_width", demo);

//        System.out.println(pilotBodyDao.selectPilotBodyWithCondition(condition));

    }


}
