package servlet;

import beam.Admin;
import beam.User;
import com.mysql.cj.Session;
import dao.HumanDao;
import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import sun.reflect.Reflection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {

    private String password;
    private String name;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        String status = req.getParameter("status");
        // 获取请求参数
        //用于判断登陆人员身份信息，以跳转到对应界面
        session.setAttribute("status", status);
        /*
            拿到页面传过来的手动输入的验证码, 该验证码要和生成图片上的
            文本验证码比较, 如果相同, 验证码输入成功!
         */
        String imageText = req.getParameter("image");
        // 图片的验证码
        String text = (String) session.getAttribute("text");
        //立刻删除验证码，防止回退时验证码字符串不变。
        session.removeAttribute("text");

        //防止验证码图片不变
        if (text == null || !text.equalsIgnoreCase(imageText)) {
            req.setAttribute("imageMess", "验证码输入错误!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

        name = req.getParameter("name");
        password = req.getParameter("password");

        HumanDao dao = new HumanDao();
        User user = null;
        Admin admin = null;
        try {
            if (status==null) {
                req.setAttribute("statusMess", "请选择身份信息!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else if (status.equals("admin")) {
                //建立admin对象，作为管理员身份进入系统
                admin = dao.adminlogin(name, password);
                //判断用户名密码是否匹配
                if (admin != null) {
                    req.getSession().setAttribute("admin", admin);
                } else {
                    req.setAttribute("error", "账号或密码错误");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } else if (status.equals("user")){
                //建立user对象，作为用户身份进入系统
                user = dao.userlogin(name, password);
                //判断用户名密码是否匹配
                if (user != null) {
                    req.getSession().setAttribute("user", user);
                } else {
                    req.setAttribute("error", "账号或密码错误");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }

            //把name传给successLogin
            req.getSession().setAttribute("name", name);
            // 使用cookie实现回写用户名
            Cookie cookie = new Cookie("name", name);
            cookie.setMaxAge(60 * 60);
            //发送cookie到客户端
            resp.addCookie(cookie);
            //重定向到登陆页面
            resp.sendRedirect("successLogin.jsp");

        }catch (SQLException e){
            e.printStackTrace();
        }




    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }


    public static void main(String[] args) {
        Number n = 0;
        Class<?> c = n.getClass();
        System.out.println(c.getName()); //java.lang.Integer
    }
}
