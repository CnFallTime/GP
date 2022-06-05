package org.zhanghao.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.zhanghao.graduationproject.bean.Text;
import org.zhanghao.graduationproject.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * @author 张昊
 * @date 2019/10/8 - 16:00
 **/
@Controller
public class TextController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/vippage/post")
    public String list(Model model){
        List<Text> texts = userMapper.queralll();
        model.addAttribute("emps",texts);
        return "vippage/post";
    }
    @PostMapping("/add")
    public String add(Text text){
        userMapper.insertText(text);
        return "redirect:/vippage/post";
    }
}
