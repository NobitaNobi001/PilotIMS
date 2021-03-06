package com.pilot.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pilot.boot.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ezuy
 * @date 20/12/22 15:48
 */
public interface UserDao extends BaseMapper<User> {


    /**
     * batch add user
     *
     * @param users
     * @return
     */
    int batchInsertUser(@Param("list") List<User> users);

    /**
     * update password by userId
     *
     * @param userId
     * @param password
     * @return
     */
    int updatePasswordByUserId(@Param("userId") Long userId, @Param("password") String password);

    /**
     * find user by username
     *
     * @param userName
     * @return
     */
    User findUserByName(@Param("userName") String userName);

    /**
     * Fuzzy query
     *
     * @param userIPage
     * @param userName
     * @return
     */
    IPage<User> findUsersByNamed(IPage<User> userIPage, @Param("userName") String userName,@Param("userId") Long userId);

    /**
     * find user with dept by userId
     *
     * @param userId
     * @return
     */
    User findUserWithDeptByUserId(@Param("userId") Long userId);

    /**
     * select user page
     *
     * @param userIPage
     * @param userId
     * @return
     */
    IPage<User> selectUserPage(IPage<User> userIPage, @Param("userId") Long userId);
}
