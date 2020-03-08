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


@WebServlet("/nameSearch")
public class NameSearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            HumanDao dao = new HumanDao();
            String name = request.getParameter("nameSearch");
            List<Human> humans = null;
            humans = dao.nameSerach(name);
            request.setAttribute("humans", humans);

            String status = (String) request.getSession().getAttribute("status");
            if (status.equals("user")){
                User user = (User) request.getSession().getAttribute("user");
                request.setAttribute("unExemineDuration",dao.durationStatisticSuccess(name,0));
                request.setAttribute("successExemineDuration",dao.durationStatisticSuccess(name,1));
            }else {
                //管理员
                request.setAttribute("unExemineDuration",dao.durationStatisticSuccess(name,0));
                request.setAttribute("successExemineDuration",dao.durationStatisticSuccess(name,1));
            }
            request.getRequestDispatcher("nameSearch.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
