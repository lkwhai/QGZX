//package servlet;
//
//import beam.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet("/VerifySuccessServlet")
//public class VerifySuccessServlet extends HttpServlet {
//
//    private HttpSession session;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;utf-8");  //解决乱码问题
//        session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null){
//            response.getWriter().println("您还未登陆，请<a href='login.html'>登陆</a>");
//        }else {
//            response.getWriter().println("您已登陆");
//            response.getWriter().println("<a href='exitServlet'>退出</a>");
//
////            Cookie cookie = new Cookie("name",session.getId());//客户端cookie
////            response.addCookie(cookie);
//        }
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
