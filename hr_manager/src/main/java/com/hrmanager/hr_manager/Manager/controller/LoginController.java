package com.hrmanager.hr_manager.Manager.controller;



import com.hrmanager.hr_manager.Manager.other.MessageRegister;
import com.hrmanager.hr_manager.Model.Role;
import com.hrmanager.hr_manager.Model.User;
import com.hrmanager.hr_manager.Model.UserExample;
import com.hrmanager.hr_manager.Service.IRoleService;
import com.hrmanager.hr_manager.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController{

    String prefix = "index/";
    String verify = " ";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @PostMapping("user/login")
    @ResponseBody
    public String login(@RequestParam("id") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        UserExample example = new UserExample();
        UserExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andPhoneEqualTo(username);
        exampleCriteria.andPasswordEqualTo(password);

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<User> users = userService.selectByExample(userExample);
        List<User> userList = userService.selectByExample(example);

        if (!users.isEmpty() && userList.isEmpty()) {
            session.setAttribute("LoginUser", users.get(0));
            return "1";
        }else if(!userList.isEmpty() && users.isEmpty()){
            session.setAttribute("LoginUser", userList.get(0));
            return "1";
        }else if(!userList.isEmpty() && !users.isEmpty()){
            session.setAttribute("LoginUser", users.get(0));
            return "1";
        }
        else {
            return "0";
        }
    }

    @PostMapping("user/register")
    @ResponseBody
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("phone") String phone,
                           @RequestParam("verifynumber") String verifynumber,
                           Map<String, Object> map) {
        if (phone.length() != 11){
            return "-1";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setCreatedate(new Date());

        Role role = roleService.selectByPrimaryKey(1);
        if(role == null){
            Role role1 = new Role();
            role1.setRid(1);
            role1.setRname("管理员");
            role1.setRremark("");
            roleService.insert(role1);
        }

        user.setRoleId(1);

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userService.selectByExample(userExample);
        if (!users.isEmpty()){
            return "0";
        }
        if (!verifynumber.equals(verify)){
            return "-2";
        }

        int i = userService.insert(user);
        if (i == 1 && verifynumber.equals(verify)) {
            return "1";
        }else{
            map.put("msg", "账号注册失败，请重新尝试！");
            return "-3";
        }
    }

    @RequestMapping("verify")
    @ResponseBody
    public void PhoneVerify(String phone) {
        verify = MessageRegister.messageRegister(phone);
    }

    @RequestMapping("user/loginout")
    public String LoginOut(HttpSession session){
        session.removeAttribute("LoginUser");
        return "redirect:/login.html";
    }


}
