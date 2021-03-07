package com.pilot.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pilot.boot.dao.UserDao;
import com.pilot.boot.entity.User;
import com.pilot.boot.exception.MyException;
import com.pilot.boot.service.UserService;
import com.pilot.boot.utils.ConstantUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ezuy
 * @date 20/12/22 15:47
 */
@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddUser(List<User> users) {
        return userDao.batchInsertUser(users);
    }

    @Override
    public IPage<User> findAllUser(Long userId, Page<User> userPage) {
        return userDao.selectUserPage(userPage,userId);
    }

    @Override
    public User findUserByNameAndPassword(String name, String password) {

        //1.find user
        User user = userDao.findUserByName(name);

        //2.user is null
        if (user == null) {
            return null;
        }

        //3.compare password
        if (password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean findUserByName(String name) {
        return userDao.findUserByName(name) == null;
    }

    @Override
    public IPage findUsersByNamed(Page<User> userPage, String userName,Long userId) {
        return userDao.findUsersByNamed(userPage, userName,userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findUserWithDeptByUserId(userId);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int updateUserPassword(Map<String, String> pwdInfo) {

        //userId
        String userId = pwdInfo.get(ConstantUtil.userId.toString());
        //password
        String password = pwdInfo.get(ConstantUtil.password.toString());
        //rePass
        String rePass = pwdInfo.get(ConstantUtil.rePass.toString());

        //1. select user by id
        User user = userDao.findUserWithDeptByUserId(Long.valueOf(userId));

        //2.check pwd
        if (!user.getPassword().equals(password)) {
            throw new MyException("原密码输入错误!");
        } else {
            return userDao.updatePasswordByUserId(Long.valueOf(userId), rePass);
        }
    }

    @Override
    public int deleteUserById(Long userId) {
        return userDao.deleteById(userId);
    }

    @Transactional(rollbackFor = MyException.class)
    @Override
    public int batchDeleteUser(List<Long> userIds) {
        return userDao.deleteBatchIds(userIds);
    }
}
