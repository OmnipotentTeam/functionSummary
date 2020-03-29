package com.zhxx.admin.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zhxx.admin.server.constants.UserConstants;
import com.zhxx.admin.server.dao.UserDao;
import com.zhxx.admin.server.dto.UserDto;
import com.zhxx.admin.server.model.User;
import com.zhxx.admin.server.model.User.Status;
import com.zhxx.admin.server.service.UserService;
import com.zhxx.admin.server.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User saveUser(UserDto userDto) {
        User user = userDto;
        user.setSalt(DigestUtils
                .md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
        user.setPassword(passwordEncoder(user.getPassword(), user.getSalt()));
        user.setStatus(Status.VALID);
        userDao.save(user);
        saveUserRoles(user.getId(), userDto.getRoleIds());

        log.debug("新增用户{}", user.getUsername());
        return user;
    }
    @Override
    @Transactional
    public User saveTeacherUser(UserDto userDto) {
        User user = userDto;
        user.setSalt(DigestUtils
                .md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
        user.setPassword(passwordEncoder(user.getPassword(), user.getSalt()));
        user.setStatus(2);
        userDao.save(user);
        List<Long> roleIds = new ArrayList<Long>();
        roleIds.add((long) 26);
        saveUserRoles(user.getId(),roleIds);

        log.debug("新增用户{}", user.getUsername());
        return user;
    }
    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userDao.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userDao.saveUserRoles(userId, roleIds);
            }
        }
    }

    @Override
    public String passwordEncoder(String credentials, String salt) {
        Object object = new SimpleHash("MD5", credentials, salt, UserConstants.HASH_ITERATIONS);
        return object.toString();
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User u = userDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!u.getPassword().equals(passwordEncoder(oldPassword, u.getSalt()))) {
            throw new IllegalArgumentException("原密码错误");
        }

        userDao.changePassword(u.getId(), passwordEncoder(newPassword, u.getSalt()));

        log.debug("修改{}的密码", username);
    }

    @Override
    public User deleteUser(long id) {
        userDao.deleteUser(id);
        return null;
    }

    @Override
    @Transactional
    public User updateUser(UserDto userDto) {
        userDao.update(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        updateUserSession(userDto.getId());

        return userDto;
    }

    private void updateUserSession(Long id) {
        User current = UserUtil.getCurrentUser();
        if (current.getId().equals(id)) {
            User user = userDao.getById(id);
            UserUtil.setUserSession(user);
        }
    }


//    @Override
//    @Transactional
//    public User updateUserStatus(Long id) {
//        userDao.update();
//        saveUserRoles(userDto.getId(), userDto.getRoleIds());
//        updateUserSession(userDto.getId());
//        return userDto;
//    }
}
