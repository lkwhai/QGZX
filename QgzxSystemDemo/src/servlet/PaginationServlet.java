package servlet;

import beam.Human;
import beam.User;
import dao.HumanDao;
import sun.security.krb5.internal.ccache.CCacheInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/mainServlet")
public class PaginationServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HumanDao dao = new HumanDao();
        resp.setContentType("text/html; charset=UTF-8");

        //获取身份类型
        String status = (String) req.getSession().getAttribute("status");
        int pageindex = 1;//当前索引第几页
        int pagesize = 4;

        try {
            pageindex = Integer.parseInt(req.getParameter("start"));
        }catch (Exception e){ }

        int next = pageindex +1;
        int pre = pageindex -1;
        int total = 0;
        try {
            String name = String.valueOf(req.getSession().getAttribute("name"));
            if (status.equals("user")) {
                total = dao.GetTotal(name);//根据用户登陆名获取信息
            }else {
                total = dao.GetTotal();//管理员获取全部信息
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int last;
        last = (total + pagesize -1)/ pagesize;//?????????
//        last = total/pagesize + 1;
        int currPage = 1;
        currPage = pageindex;

        req.setAttribute("currPage",currPage);
        req.setAttribute("start",pageindex);
        req.setAttribute("next",next);
        req.setAttribute("pre",pre);
        req.setAttribute("last",last);

        List<Human> humans = null;
        int page = (pageindex -1)*pagesize;
        String username=null;
        try {
            if (status.equals("user")){
                User user = (User) req.getSession().getAttribute("user");
                username = user.getUsername();
                req.setAttribute("unExemineDuration",dao.durationStatisticSuccess(username,0));
                req.setAttribute("successExemineDuration",dao.durationStatisticSuccess(username,1));
            }else {
                //管理员
                req.setAttribute("unExemineDuration",dao.durationStatisticSuccess(null,2));
                req.setAttribute("successExemineDuration",dao.durationStatisticSuccess(null,3));
            }

            humans = new HumanDao().Pagination(page,pagesize,username,status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("humans",humans);
        req.getSession().setAttribute("humans",humans);
        req.getRequestDispatcher("QueryInfo.jsp").forward(req,resp);
    }
}
