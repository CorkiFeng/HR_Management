package com.controller;

import com.model.Role;
import com.model.RoleExample;
import com.model.User;
import com.model.UserExample;
import com.service.IRoleService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagerController {

    String prefix = "index/";

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @GetMapping("query_all_users")
    public String QueryAllUsers(HttpSession session,
                                Model model,
                                Map<String, Object> map) {

        User loginUser = (User) session.getAttribute("LoginUser");
        Integer role_id = loginUser.getRoleId();
        Integer number = loginUser.getNumber();

        User user = new User();
        user.setRoleId(role_id);
        user.setNumber(number);
        List<User> users = userService.selectUserRole(user);
        map.put("users",users);

        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria exampleCriteria = roleExample.createCriteria();
        exampleCriteria.andRidLessThan(role_id);
        List<Role> roles = roleService.selectByExample(roleExample);
        map.put("roles", roles);



        model.addAttribute(map);
        return prefix + "query-user";
    }

    @DeleteMapping("/delete_user")
    public String DeleteUser(@RequestParam("id") Integer id) {
        userService.deleteByPrimaryKey(id);
        return "redirect:query_all_users";
    }

    @RequestMapping("addUser")
    public String ToAddUser(HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("LoginUser");
        RoleExample example = new RoleExample();
        RoleExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andRidLessThan(loginUser.getRoleId());
        List<Role> roles = roleService.selectByExample(example);
        model.addAttribute("role",roles);
        return prefix + "add-user";
    }

    @PostMapping("add_user")
    @ResponseBody
    public String AddUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> users = userService.selectByExample(userExample);
        if (!users.isEmpty()){
            return "-1";
        }
        user.setCreatedate(new Date());
        int insert = userService.insert(user);
        if (insert == 1) {
            return "1";
        } else {
            return "0";
        }
    }

    @GetMapping("/search_user")
    public String SearchUser(User user,
                             Model model,
                             Map<String, Object> map,
                             HttpSession session) {


        User loginUser = (User) session.getAttribute("LoginUser");
        Integer role_id = loginUser.getRoleId();
        Integer number = loginUser.getNumber();

        User user1 = new User();
        user1.setNumber(number);
        user1.setRoleId(role_id);
        user1.setUsername("");
        user1.setPhone("");

        user.setUsername("%"+user.getUsername()+"%");


        List<User> users1 = userService.selectUserRoleByName(user1);


        List<User> users = userService.selectUserRoleByName(user);
        users.forEach(li-> System.out.println(li));

        List<User> list = new ArrayList<User>();
        for (User all_user : users){
            boolean flag = true;
            for (User login_user : users1){
                    if (all_user.getNumber() == login_user.getNumber()){
                        flag = false;
                        break;
                    }else if (all_user.getRoleId() >= login_user.getRoleId()){
                        flag = false;
                        break;
                    }
            }
            if (flag){
                list.add(all_user);
            }
        }


        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria exampleCriteria = roleExample.createCriteria();
        exampleCriteria.andRidLessThan(role_id);
        List<Role> roles = roleService.selectByExample(roleExample);

        map.put("roles", roles);
        map.put("users", list);

        model.addAttribute(map);

        return prefix + "query-user";


    }

    @PostMapping("/modify_role/{number}")
    public String ModifyRole(@PathVariable("number") Integer number, User user){
        User user1 = new User();
        user1.setRoleId(user.getRoleId());
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNumberEqualTo(number);
        userService.updateByExampleSelective(user1,example);

        return "redirect:/query_all_users";
    }


}
