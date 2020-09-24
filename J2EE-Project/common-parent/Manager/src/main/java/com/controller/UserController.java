package com.controller;

import com.model.User;
import com.model.UserExample;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    String prefix = "index/";

    @Autowired
    private IUserService userService;

    @PostMapping("/modifymsg")
    @ResponseBody
    public String ModifyMsg(@RequestParam("id") Integer id,
                         @RequestParam(value = "name") String username,
                         @RequestParam(value = "phone") String phone,
                         @RequestParam(value = "desc") String desc){
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setRemark(desc);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNumberEqualTo(id);
        int i = userService.updateByExampleSelective(user, userExample);
        if (i == 1){
            return "1";
        }else{
            return "0";
        }
    }

    @PostMapping("/modifypwd")
    @ResponseBody
    public String ModifyPwd(@RequestParam(value = "username") String name,
                            @RequestParam(value = "password1") String password1,
                            @RequestParam(value = "password2") String password2,
                            @RequestParam(value = "password3") String password3,
                            Map<String,Object> map){
        User user = new User();
        user.setPassword(password2);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        criteria.andPasswordEqualTo(password1);
        List<User> users = userService.selectByExample(userExample);
        if (!password2.equals(password3)){
            return "500";
        }
        if (users.isEmpty()){
            return "-1";
        }else{
            int i = userService.updateByExampleSelective(user, userExample);
            if (i == 1){
                return "1";
            }else{
                return "0";
            }
        }

    }

    @GetMapping("user/person_msg")
    public String PersonMsg(@RequestParam("username") String username
                            ,Model model){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userService.selectByExample(userExample);
        model.addAttribute("user",users);
        return prefix+"admin-info";
    }


}
