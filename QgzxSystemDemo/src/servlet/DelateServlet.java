package servlet;

import beam.Human;
import beam.User;
import dao.HumanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deleteServlet")
public class DelateServlet extends HttpServlet {


    private String date;
    private String location;
    private String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("text/html");
            req.setCharacterEncoding("utf-8");
            String status = (String) req.getSession().getAttribute("status");
            int id = Integer.parseInt(req.getParameter("id"));

            HumanDao humanDao = new HumanDao();
            humanDao.deleteHumbleById(id);


            req.getRequestDispatcher("successLogin.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
