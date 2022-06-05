package org.zhanghao.graduationproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 张昊
 * @date 2019/10/5 - 22:52
 **/
@Controller
public class JumpController {
    private final String PREFIX="pages/";
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/pwd")
    public String pwd(){
        return "pwdlogin";
    }
    @GetMapping("/getpwd")
    public String getpwd(){
        return "getpwd";
    }
    @GetMapping("/vippage/contact")
    public String contact(){
        return "vippage/contact";
    }
    @GetMapping("/update")
    public String update(){
        return "emp/update";
    }
//    @RequestMapping("/loginout")
//    public String loginout(HttpSession session){
//        session.invalidate();
//        return "redirect:/logout";
//    }
    @GetMapping("/bootstrap")
    public String bootstrap(){
        return "bootstrap";
    }
}
