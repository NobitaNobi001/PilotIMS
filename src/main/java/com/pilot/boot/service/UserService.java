package com.pilot.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.entity.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ezuy
 * @date 20/12/22 15:45
 */
public interface UserService {

    /**
     * add a user
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * find all user
     * @param userPage
     * @return users
     */
    IPage<User> findAllUser(Page<User> userPage);

    /**
     * find user by name and password
     * @param name
     * @param password
     * @return
     */
    User findUserByNameAndPassword(String name,String password);

    /**
     * find user by name
     * @param name
     * @return
     */
    boolean findUserByName(String name);

    /**
     * find user with id
     * @param userId userId
     * @return user
     */
    User findUserById(Long userId);

    /**
     * update a user
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * update pwd
     * @param pwdInfo
     * @return
     */
    int updateUserPassword(Map<String,String> pwdInfo);

    /**
     * delete user by id
     * @param userId
     * @return
     */
    int deleteUserById(Long userId);

    /**
     * batch delete userId
     * @param userIds
     * @return
     */
    int batchDeleteUser(List<String> userIds);
}
