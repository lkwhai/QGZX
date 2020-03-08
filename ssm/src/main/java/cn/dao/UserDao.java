package cn.dao;


import cn.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Insert("insert into user (username,password) values (#{username},#{password})")
    public void saveUser(User user);

    @Select("select * from user")
    public List<User> findAll();

    @Select("SELECT * FROM user where USERNAME=#{username} AND PASSWORD=#{password}")
    public User verify(User user);
}
