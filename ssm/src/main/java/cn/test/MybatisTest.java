package cn.test;

import cn.dao.AccountDao;
import cn.dao.UserDao;
import cn.domain.Account;
import cn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public  void run1() throws IOException {
        //配置文件加载
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //SqlSession对象创建
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);
        List<Account> list = dao.findAll();
        for (Account account:list
             ) {
            System.out.println(account);

        }
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public  void run4() throws IOException {
        //配置文件加载
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //SqlSession对象创建
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);
        Account account = new Account();
        account.setId(11);
        account.setMoney(100);
        account.setName("sda");
        dao.saveAccount(account);
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public  void run2() throws IOException {
        //配置文件加载
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //SqlSession对象创建
        SqlSession session = factory.openSession();
        //获取代理对象
        UserDao dao = session.getMapper(UserDao.class);
        User user = new User();
        user.setId(11);
        user.setUsername("11");
        user.setPassword("123");
//        dao.saveUser(user);
        System.out.println(dao.verify(user));
        session.commit();
        session.close();
        in.close();
    }


    @Test
    public  void run3() throws IOException {
        //配置文件加载
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //SqlSession对象创建
        SqlSession session = factory.openSession();
        //获取代理对象
        UserDao dao = session.getMapper(UserDao.class);
        List<User> list = dao.findAll();
        for (User user:list
        ) {
            System.out.println(user);

        }
        session.commit();
        session.close();
        in.close();
    }

}
