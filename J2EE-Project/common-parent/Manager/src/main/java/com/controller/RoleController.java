package com.controller;

import com.model.*;

import com.service.IRoleService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IUserService userService;

    //搜所有
    @GetMapping("query_all_role/{id}")
    public String QueryAllRoles(@PathVariable("id") Integer roleid,Model model, Map<String,Object> map){
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRidLessThan(roleid);
        List<Role> roles = iRoleService.selectByExample(roleExample);
        map.put("roles",roles);
        map.put("roless",roles);
        model.addAttribute(map);
        return "index/query-role";
    }

//    //增
//    @PostMapping("add_role")
//    @ResponseBody
//    public String AddRoles(Role role){
//        int insert = iRoleService.insert(role);
//        if (insert == 1){
//            return "1";
//        }else{
//            return "0";
//        }
//    }

    //删
    @DeleteMapping("/delete_role")
    public String DeleteRoles(@RequestParam("id") Integer id,HttpSession session){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(id);
        userService.deleteByExample(example);
        iRoleService.deleteByPrimaryKey(id);
        User loginUser = (User) session.getAttribute("LoginUser");
        Integer roleId = loginUser.getRoleId();
        return "redirect:query_all_role/"+roleId;
    }
    //搜
    @GetMapping("/search_role")
    public String SearchRoles(Role role , Model model, Map<String, Object> map, HttpSession session){
        Integer rid = role.getRid();
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRidEqualTo(rid);
        List<Role> roles = iRoleService.selectByExample(roleExample);

        User loginUser = (User) session.getAttribute("LoginUser");
        Integer roleId = loginUser.getRoleId();
        RoleExample example = new RoleExample();
        RoleExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andRidLessThan(roleId);
        List<Role> roless = iRoleService.selectByExample(example);
        map.put("roles",roles);
        map.put("roless",roless);
        model.addAttribute(map);
        return "index/query-role";
    }
}
