package servlet;


import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findHumanServlet")
public class FindHumanServlet extends HttpServlet {

    private JSONObject jsonObject;
    private PrintWriter writer;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名
        String username = request.getParameter("username");

        //2.调用service层判断用户名是否存在

        //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
        //                         {"userExsit":false,"msg":"用户名可用"}

        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");
        jsonObject = new JSONObject();

        if("tom".equals(username)){
            //存在
            jsonObject.put("userExist",true);
            jsonObject.put("msg","此用户名太受欢迎,请更换一个");
        }else{
            //不存在
            jsonObject.put("userExist",false);
            jsonObject.put("msg","用户名可用");
        }

        //将map转为json，并且传递给客户端
        //将map转为json
//        ObjectMapper mapper = new ObjectMapper();
//        //并且传递给客户端
//        mapper.writeValue(response.getWriter(),map);

        writer = response.getWriter();
        writer.println(jsonObject);

        //System.out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
