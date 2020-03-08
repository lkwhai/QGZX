package dao;

import beam.Admin;
import beam.Human;

import beam.StatisticHuman;
import beam.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import util.Mysql;
import util.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@WebServlet("/HumanDao")
public class HumanDao extends JdbcDaoSupport {




    public int GetTotal() throws SQLException {
        int total = 0;
        Connection connection = Mysql.gecon();
        Statement statement = connection.createStatement();
        String sql = "SELECT COUNT(WORK_NAME) FROM QGZX";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            total = resultSet.getInt(1);
        }
        return total;
    }
    public int GetTotal(String username) throws SQLException {
        int total = 0;
        Connection connection = Mysql.gecon();
        String sql = "SELECT COUNT(WORK_NAME) FROM QGZX where WORK_NAME=? AND EXEMINE=1 ";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            total = resultSet.getInt(1);
        }
        return total;
    }

    public boolean modifyHuman(Human human) throws Exception {
        Connection connection = Mysql.gecon();
        boolean flag = false;
        if (StringUtil.isEmpty(String.valueOf(connection))){
            throw new Exception("数据库连接失败");
        }
        String sql = "UPDATE QGZX SET WORK_DATE=?,WORK_WEEK=?,WORK_LOCATION=?,WORK_SUBSTANCE=?,WORK_DURATION=? WHERE WORK_NAME=? ";

        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setString(1,human.getWorkDate());
        preparedStatement.setString(2, String.valueOf(human.getWorkWeek()));
        preparedStatement.setString(3,human.getWorkLocation());
        preparedStatement.setString(4,human.getWorkSubstance());
        preparedStatement.setString(5, String.valueOf(human.getWorkDuration()));
        preparedStatement.setString(6,human.getWorkName());
        int result = preparedStatement.executeUpdate();
        if (result == 1){
            flag= true;
        }
        Mysql.close(connection,preparedStatement);
        return flag;
    }


    public int deleteHumbleById(int id) throws SQLException {
        Connection connection =Mysql.gecon();
        PreparedStatement preparedStatement;
        boolean flag = false;
//        connection = C3p0.getconnetion();

        String sql ="DELETE FROM QGZX WHERE ID=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);

        int result = preparedStatement.executeUpdate();
        Mysql.close(connection, preparedStatement);

        return result;
    }
    public int addHuman(Human human) throws SQLException {
        Connection connection = Mysql.gecon();
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {

            String sql = "INSERT INTO QGZX VALUES (?,?,?,?,?,?,?)";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,human.getWorkName());
            preparedStatement.setString(2,human.getWorkDate());
            preparedStatement.setInt(3,human.getWorkWeek());
            preparedStatement.setString(4,human.getWorkLocation());
            preparedStatement.setString(5,human.getWorkSubstance());
            preparedStatement.setDouble(6,human.getWorkDuration());
            preparedStatement.setInt(7, 0);
            result= preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (StringUtil.isEmpty(String.valueOf(connection))==false){
                Mysql.close(connection,preparedStatement);
            }
        }
        return result;
    }


    public List<Human> nameSerach(String name) throws SQLException {
        Connection connection = Mysql.gecon();
        String sql = "SELECT * FROM QGZX WHERE WORK_NAME=?";
        List<Human> humans = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        Human human;
        while (resultSet.next()){
            try {
                human = new Human();
                human.setWorkName(resultSet.getString("WORK_NAME"));
                human.setWorkDate(resultSet.getString("WORK_DATE"));
                human.setWorkWeek(Integer.parseInt(resultSet.getString("WORK_WEEK")));
                human.setWorkSubstance(resultSet.getString("WORK_SUBSTANCE"));
                human.setWorkLocation(resultSet.getString("WORK_LOCATION"));
                human.setWorkDuration(Double.parseDouble(resultSet.getString("WORK_DURATION")));
                humans.add(human);
            }catch (Exception e){
            }
        }

        Mysql.close(connection,resultSet,preparedStatement);
        return humans;

    }


    public static void main(String[] args) throws SQLException {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
//        String sql = "SELECT * FROM qgzx WHERE WORK_NAME=? ";
//        List<Human> humans =jt.query("SELECT * FROM qgzx WHERE WORK_NAME=? LIMIT ?,? ",new BeanPropertyRowMapper<Human>(Human.class),"3",1,5);
//        System.out.println(humans);
        List<Human> list = new HumanDao().Pagination(1,6,"3","user");

    }
    public List<Human> Pagination(int start , int count,String name,String status) throws SQLException {
        List<Human> humans = null;
        if (status.equals("user")) {
            String sql = "SELECT * FROM qgzx WHERE WORK_NAME=? LIMIT ?,? ";
            humans = getJdbcTemplate().query(sql,new BeanPropertyRowMapper<Human>(Human.class),name,start,count);
            System.out.println(humans);
        }else {
            String sql = "SELECT * FROM qgzx LIMIT ?,? ";
        }
        return humans;
    }

    public User userlogin(String username , String password) throws SQLException {
        PreparedStatement statement =null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user =null;
        try {
            connection = Mysql.gecon();
            String sql = "SELECT * FROM user where WORK_NAME=? AND PASSWORD=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setUsername(resultSet.getString("WORK_NAME"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Mysql.close(connection,resultSet,statement);
        }
        return user;
    }
    public Admin adminlogin(String adminName , String password) throws SQLException {
        PreparedStatement statement =null;
        Connection connection = null;
        ResultSet resultSet = null;
        Admin admin= null;
        try {
            connection = Mysql.gecon();
            String sql = "SELECT * FROM admin where admin=? AND PASSWORD=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,adminName);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                admin = new Admin();
                admin.setAdminName(resultSet.getString("admin"));
                admin.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Mysql.close(connection,resultSet,statement);
        }
        return admin;
    }

    public int addUser(String name,String password){
        Connection connection = null;
        int result = 0;
        try {
            connection = Mysql.gecon();
            PreparedStatement statement = null;
            String sql = "INSERT INTO USER VALUES (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password);
            try {
                result = statement.executeUpdate();
            }catch (SQLException e){
            }
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateExemine(int id) throws SQLException {
        Connection connection = Mysql.gecon();
        String sql = "UPDATE QGZX SET EXEMINE='1.0' WHERE id=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        int result = statement.executeUpdate();
        return result;
    }

    public float durationStatisticSuccess(String name,int exemine) throws SQLException {
        Connection connection = Mysql.gecon();
        PreparedStatement statement = null;
        String sql = null;
        ResultSet resultSet =null;
//        0,1为学生查询未审核和已经审核的记录
//        2,3为老师查询所有学生已经审核和未审核的记录
        if (exemine == 0||exemine == 1){
            sql = "SELECT SUM(WORK_DURATION) FROM qgzx WHERE WORK_NAME=? AND exemine=?;";
            statement= connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setInt(2,exemine);
            resultSet = statement.executeQuery();
        }else if (exemine ==2||exemine==3){
            exemine-=2;
            sql = "SELECT SUM(WORK_DURATION) FROM qgzx WHERE exemine=?;";
            statement= connection.prepareStatement(sql);
            statement.setInt(1,exemine);
            resultSet = statement.executeQuery();
        }

        float result = 0;
        while (resultSet.next()) {
            try {
                result = Float.parseFloat(resultSet.getString("SUM(WORK_DURATION)"));
            }catch (NullPointerException e){ }
        }
        return result;

    }
    public List<StatisticHuman> statisticHuman() throws SQLException {
        Connection connection = Mysql.gecon();
        Statement statement = connection.createStatement();
        String sql = "SELECT WORK_NAME ,SUM(if(exemine=1,WORK_DURATION,0)) AS SUCCESSEXEMINE,SUM(if(exemine=0,WORK_DURATION,0)) AS UNEXEMINE FROM qgzx GROUP BY WORK_NAME";
        ResultSet resultSet = statement.executeQuery(sql);
        List<StatisticHuman> statisticHumanList = new ArrayList<>();
        while (resultSet.next()){
            StatisticHuman statisticHuman = new StatisticHuman();
            statisticHuman.setWorkName(resultSet.getString("WORK_NAME"));
            statisticHuman.setSuccessExemineDuration(Float.parseFloat(resultSet.getString("SUCCESSEXEMINE")));
            statisticHuman.setUnExemineDuration(Float.parseFloat(resultSet.getString("UNEXEMINE")));
            statisticHumanList.add(statisticHuman);
        }
        return statisticHumanList;
    }



}

