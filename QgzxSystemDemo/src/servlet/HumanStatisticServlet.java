package servlet;

import beam.Human;
import beam.StatisticHuman;
import dao.HumanDao;
import util.Mysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@WebServlet("/HumanStatisticServlet")
public class HumanStatisticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String status = String.valueOf(request.getSession().getAttribute("status"));
        if (status.equals("admin")){
            HumanDao dao = new HumanDao();
            List<StatisticHuman> statisticHumanList = new ArrayList<>();
            try {
                statisticHumanList = dao.statisticHuman();
                request.setAttribute("humanStatisticServlet",statisticHumanList);
                request.getRequestDispatcher("HumanStatistic.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
