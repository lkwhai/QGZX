//package servlet;
//
//import beam.Human;
//import dao.HumanDao;
//import org.apache.commons.lang.StringUtils;
//import util.Mysql;
//import util.StringUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet("/humanServlet1")
//public class HumanServlet extends HttpServlet {
//    private HumanDao dao;
//
//
////    @Override
////    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
////        req.setCharacterEncoding("utf-8");
////        String method = req.getParameter("method");
////        try {
////        switch (method){
////            case "add":
////                addHum(req,res);
////            case "create":
////                createHum(req,res);
////        }
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req, resp);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            queryHuman(req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void queryHuman(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException {
//
//        dao = new HumanDao();
//        request.setCharacterEncoding("utf-8");
//        List<Human> humans = dao.queryAllHuman();
//        request.setAttribute("humans", humans);
//        request.getRequestDispatcher("QueryInfo.jsp").forward(request, response);
//    }
//
//    private void addHum(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.getRequestDispatcher("addHum.jsp").forward(request,response);
//    }
//
//    private void createHum(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HumanDao humanDao = dao;
//        request.setCharacterEncoding("utf-8");
//        Human human = new Human();
//        String name = request.getParameter("WORK_NAME");
//        if (StringUtil.isEmpty(name) || "null".equals(name)) {
//            human.setWorkName(request.getParameter("WORK_NAME"));
//            human.setWorkDate(request.getParameter("WORK_DATE"));
////            human.setWorkWeek((request.getParameter("WORK_WEEK")));
//            human.setWorkLocation(request.getParameter("WORK_LOCATION"));
//            human.setWorkSubstance(request.getParameter("WORK_SUBSTANCE"));
////            human.setWorkDuration((request.getParameter("WORK_DURATION")));
//            humanDao.addHuman(human);
//            response.sendRedirect("addHum.jsp");
//            request.setAttribute("declaration", "查看人员列表");
//            request.getRequestDispatcher("QueryInfo.jsp").forward(request,response);
//        } else {
////            modifyHumanInfo(request, response);
//        }
//
//    }
//
//
//
//
//    private void modifyHumanInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setCharacterEncoding("utf-8");
//        HumanDao dao = this.dao;
//        Human human = new Human();
//        try {
//            human.setWorkName(request.getParameter("WORK_NAME"));
//            human.setWorkDate(request.getParameter("WORK_NAME"));
////            human.setWorkWeek(request.getParameter("WORK_NAME"));
//            human.setWorkLocation(request.getParameter("WORK_NAME"));
//            human.setWorkSubstance(request.getParameter("WORK_NAME"));
////            human.setWorkDuration((request.getParameter("WORK_NAME")));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        boolean flag = dao.modifyHumanByName(human);
//        if (!flag){
//            request.setAttribute("msg","修改员工信息失败");
//            request.getRequestDispatcher("error.jsp").forward(request,response);
//        }else {
//            response.sendRedirect("QueryInfo.jsp");
//        }
//    }
//
//    private  void deleteHuman(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ServletException {
//        HumanDao humanDao = dao;
//        request.setCharacterEncoding("utf-8");
//        String name = request.getParameter("WORK_NAME");
//        boolean flag = humanDao.deleteHumbleByName(name);
//        if (!flag){
//            request.setAttribute("msg","删除信息失败");
//            request.getRequestDispatcher("error.jsp").forward(request,response);
//        }else {
//            request.getRequestDispatcher("QueryInfo.jsp").forward(request,response);
//        }
//    }
//}
