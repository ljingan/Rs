package com;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginAction {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginManage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView indexManage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/doLogin"})
    public @ResponseBody
    String doLogin(@RequestParam(value = "username", required = true) String name,
                   @RequestParam(value = "password", required = true) String password,
                   HttpSession httpSession, Model model) {
        System.out.print("ddd");
        JSONObject json = new JSONObject();
        json.put("state", 1);
        json.put("msg", "succes");
        String result = json.toString();
        return result;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            HttpServletRequest request, Model model) {
        System.out.print("ddddd");
        return "d";
//        User user = userBasic.getUserByName(userName);
//        if (user != null) {
//            if (user.getPassword().equals(password)) {
//                HttpSession session = request.getSession();
//                session.setAttribute("userName", userName);
//                model.addAttribute("state", "success");
//                model.addAttribute("message", "登录成功！");
//                return "user_project";
//            } else {
//                model.addAttribute("failure", "用户名或密码错误！");
//                return "error";
//            }
//        } else {
//            model.addAttribute("state", "failure");
//            model.addAttribute("message", "该用户不存在！");
//            return "error";
//        }
    }
}