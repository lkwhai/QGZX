package cn.controller;

import cn.domain.Account;
import cn.domain.User;
import cn.service.AccountService;
import cn.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping("/findAll")
    public @ResponseBody List<User> findAll(Model model){
        System.out.println("表现层：查询所有账户...");
        // 调用service的方法
        List<User> list = userService.findAll();
        model.addAttribute("list1",list);
        return list;
    }
    /**
     * 保存
     * @return
     */
    @RequestMapping("/save")
    public @ResponseBody User save(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.saveUser(user);
        System.out.println("成功保存");
//        response.sendRedirect(request.getContextPath()+"/user/findAll");
        return user;
    }


    @RequestMapping("verify")
    public @ResponseBody User verify(@RequestParam String username,String password){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        User user1 = userService.verify(user);
        System.out.println("验证");
        System.out.println(user1);
        return user1;
    }


    /**
     * 模拟异步请求响应
     */
    @RequestMapping("testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了...");
        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        // 做响应，模拟查询数据库
//        user.setUsername("haha");
//        user.setPassword("123");
        // 做响应
        return user;
    }

}
