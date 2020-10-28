package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.*;
import com.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("LoginUser")
public class NoticeController {


    @Autowired
    private NoticeService noticeService;

    //公告显示
    @GetMapping("query_all_notices")
    public String QueryAllNotices(Model model, Map<String,Object> map){
        List<Notice> notices = noticeService.findAllNotice(new NoticeExample());
        map.put("notices",notices);
        model.addAttribute(map);
        return "index/query-announcement";
    }


    //加公告
    @PostMapping("add_notice")
    @ResponseBody
    public String postNotice(@ModelAttribute("LoginUser")User LoginUser, Notice notice){
        System.out.println("========================"+notice);
        notice.setName(notice.getTitle());
        notice.setUsename(LoginUser.getUsername());
        int i = noticeService.insert(notice);

        if(i!=1){return "0";}

        return "1";
    }

    //公告删除
    @DeleteMapping("/delete_notice")
    public String DeleteNotice(@RequestParam("id") Integer id){
        noticeService.deleteByKey(id);
        return "redirect:query_all_notices";
    }

    //搜索
    @GetMapping("/search_notice")
    public String SearchNotice( @RequestParam(value = "mytitle") String title,Model model,Map<String, Object> map){

        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();

        criteria.andTitleLike("%"+title+"%");
//        criteria.andTitleEqualTo(title);
        List<Notice> notices = noticeService.selectByExample(noticeExample);

        map.put("notices",notices);

        model.addAttribute(map);
        return "index/query-announcement";
    }

    @PostMapping("detail_notices")
    @ResponseBody
    public String DetailNotice(@RequestParam("id") Integer id){
        Notice notice = noticeService.findById(id);

        JSONArray jsonArray = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.put("title", notice.getTitle());
        jo.put("name", notice.getUsename());
        jo.put("content", notice.getContent());
        jsonArray.add(jo);
        return jsonArray.toString();
    }
}
