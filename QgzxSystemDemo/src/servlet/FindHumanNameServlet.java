package servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.WriteAbortedException;
import java.util.jar.JarOutputStream;

@WebServlet("/findHumanNameServlet")
public class FindHumanNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        response.setContentType("text/html; charset=UTF-8");
        JSONObject jsonObject = new JSONObject();

        if ("tom".equals(username)){
            jsonObject.put("Exist",true);
            jsonObject.put("msg","已使用");
        }else {
            jsonObject.put("Exist",false);
            jsonObject.put("msg","可用");
        }

        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        System.out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
