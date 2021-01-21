package com.pilot.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pilot.boot.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface UserDao extends BaseMapper<User> {

    /**
     * find user by username
     * @param name
     * @return
     */
    User findUserByName(String name);

    /**
     * update password by userId
     * @param userId
     * @param password
     * @return
     */
    int updatePasswordByUserId(@Param("userId") Long userId, @Param("password") String password);
}
