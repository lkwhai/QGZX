package cn.service;

import cn.domain.Account;
import cn.domain.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {

    public void saveUser(User user);

    public List<User> findAll();

    public User verify(User user);
}
