package test;

import beam.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Scanner;

public class test {

    ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
    JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);

    private List<Human> queryHumans(){

        return
    }
}
