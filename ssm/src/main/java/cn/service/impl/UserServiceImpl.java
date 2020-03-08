package cn.service.impl;

import cn.dao.AccountDao;
import cn.dao.UserDao;
import cn.domain.Account;
import cn.domain.User;
import cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        System.out.println("业务层，保存用户");
        userDao.saveUser(user);
    }


    @Override
    public List<User> findAll() {
        System.out.println("业务层，查找全部用户");
        return userDao.findAll();
    }

    @Override
    public User verify(User user) {
        System.out.println("业务层：登陆验证");
        return userDao.verify(user);
    }

}
