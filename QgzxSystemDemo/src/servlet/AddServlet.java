package servlet;

import beam.Human;
import beam.User;
import dao.HumanDao;
import util.Mysql;
import util.StringUtil;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            Human human = new Human();
            User user = (User) req.getSession().getAttribute("user");

            if (req.getSession().getAttribute("status").equals("user")){
                human.setWorkName(user.getUsername());
                System.out.println(user.getUsername());
            }else {
                human.setWorkName(req.getParameter("name"));
            }

            human.setWorkDate(req.getParameter("date"));
            human.setWorkWeek(Integer.parseInt(req.getParameter("week")));
            human.setWorkLocation(req.getParameter("location"));
            human.setWorkSubstance(req.getParameter("substance"));
            human.setWorkDuration(Double.parseDouble((req.getParameter("duration"))));
            HumanDao humanDao = new HumanDao();
            humanDao.addHuman(human);


            resp.sendRedirect("successLogin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
