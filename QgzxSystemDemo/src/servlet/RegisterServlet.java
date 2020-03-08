package servlet;

import dao.HumanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        String status = req.getParameter("status");
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

        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        int result =new HumanDao().addUser(name,password);
        if (result==0){
            out.println("用户名已存在。");
        }else {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }


    }
}
