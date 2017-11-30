package com.module.user;

import com.ServerEntry;
import com.module.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginManage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public ModelAndView indexManage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/doLogin"})
    public @ResponseBody
    String doLogin(@RequestParam(value = "username", required = true) String name,
                   @RequestParam(value = "password", required = true) String password,
                   HttpSession httpSession, Model model) {
        UserEntity user = userService.getUser(name);

        if (user == null) {
            return ResponseResult.Http_Error;
        }
        userService.saveUser();
        return ResponseResult.Http_Success;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            HttpServletRequest request, Model model) {
        System.out.print("ddddd");
        return "d";
    }
}
