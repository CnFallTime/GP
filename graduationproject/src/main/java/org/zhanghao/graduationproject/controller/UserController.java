package org.zhanghao.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zhanghao.graduationproject.bean.User;
import org.zhanghao.graduationproject.mapper.UserMapper;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.Predicate;


/**
 * @author 张昊
 * @date 2019/9/26 - 12:57
 **/
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/user/login")
    public String login(String email,String password,HttpSession session,Model model) throws Exception{
        List<User>  row =userMapper.getByName(email,password);
        if(row.size() == 1){
            session.setAttribute("LoginUser",row.get(0));
            return "redirect:/main.html";
        }else if(row.size()>=2){
            model.addAttribute("msg","抱歉这是一个意料之中的错误，而且已经解决了，这只是个错误展示专用页面");
            model.addAttribute("msg2","该错误造成的原因是由于数据库对应字段没有设置唯一性约束，导致本应返回一个结果的selectOne却查出了两个结果，最终导致该异常");
            model.addAttribute("msg3","当然也有多种解决办法，比如添加字段的唯一性约束，或者对返回结果进行二次查验等");
            return "error/405";
        }else {
            if (session.getAttribute("tips")!=null){
                session.removeAttribute("tips");
            }
            session.setAttribute("tips","您输入的密码有误，或用户不存在");
        }
        return "redirect:/login";
    }

    @PostMapping("/user/zhuce")
    public String addUser(User user,HttpSession session){
        String trim01 = user.getName().trim();
        String trim02 = user.getEmail().trim();
        String trim03 = user.getPassword().trim();
        List<String> allEmail = userMapper.getAllEmail();
        boolean b = allEmail.stream().anyMatch(s -> {
            if (s.equals(user.getEmail())) {
                return true;
            }
            return false;
        });
        if (trim01.equals("")||trim02.equals("")||trim03.equals("")) {
            if (session.getAttribute("tips")!=null){
                session.removeAttribute("tips");
            }
            session.setAttribute("tips","邮箱，姓名，密码不能为空");
            return "redirect:/login";
        }else if(b){
            if (session.getAttribute("tips")!=null){
                session.removeAttribute("tips");
            }
            session.setAttribute("tips","抱歉邮箱被占用");
            return "redirect:/login";
        }else {
            userMapper.insertUser(user);
            List<User> byName = userMapper.getByName(user.getEmail(), user.getPassword());
            session.setAttribute("LoginUser",byName.get(0));
            return "redirect:/home";
        }
    }
    @PostMapping("/emp")
    public String update(User user,HttpSession session){
        String trim01 = user.getPassword().trim();
        String trim02 = user.getName().trim();
        if (trim01.equals("")||trim02.equals("")){
            session.setAttribute("now","密码和姓名不能为空或空格");
            return "redirect:/update";
        }else{
            userMapper.updateDept(user);
            session.invalidate();
            return "redirect:/";
        }
    }
    @GetMapping("/emps/{id}")
    public String deleteUser(@PathVariable("id") Integer id,HttpSession session){
        userMapper.deleteDeptById(id);
        session.invalidate();
        return "redirect:/";
    }
}
