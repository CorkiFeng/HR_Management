package com.hrmanager.hr_manager.Manager.controller;



import com.hrmanager.hr_manager.Model.Uploadfile;
import com.hrmanager.hr_manager.Model.UploadfileExample;
import com.hrmanager.hr_manager.Model.User;
import com.hrmanager.hr_manager.Service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    //上传
    @RequestMapping(value = "index/upload")
    @ResponseBody
    public String uploadFile(HttpSession session , @RequestParam("file") MultipartFile file,
                             HttpServletRequest request, Model model) throws IOException {
        String filePath = request.getServletContext().getRealPath("/files/");
        String resFilename = UUID.randomUUID()+file.getOriginalFilename();
        File resFilePath = new File(filePath, resFilename);
        //判断路径是否存在，如果不存在就创建一个
        if (!resFilePath.getParentFile().exists()) {
            resFilePath.getParentFile().mkdirs();
        }
//            files[i].transferTo(new File(resFilePath + File.separator + filename));
        file.transferTo(new File(resFilePath + File.separator));
        //保存数据到数据库
        Uploadfile uploadfile = new Uploadfile();
        //保存绝对路径
        uploadfile.setFilename(resFilename);
        //日期
        uploadfile.setUploaddate(new Date());
        //上传人
        User loginUser = (User)session.getAttribute("LoginUser");
        String loginUsername = loginUser.getUsername();
        uploadfile.setUploadname(loginUsername);
        fileService.insert(uploadfile);


        return "/upload.html";
    }

    //下载
    @RequestMapping(value = "index/download",produces="application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
                                               @RequestParam("file_name") String resourceName,
                                               Model model)throws Exception{


        //下载文件路径
        String path = request.getServletContext().getRealPath("/files/");

        File file = new File(path + resourceName);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(resourceName.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);


        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    //显示能下载的文件
    @GetMapping("query_all_files")
   public String showFile(Model model, Map<String,Object> map){
        //取出所有文件并显示
        List<Uploadfile> uploadfiles = fileService.findAllfiles(new UploadfileExample());

        map.put("uploadfiles",uploadfiles);
        model.addAttribute(map);


        return "index/download";
    }


    @RequestMapping("delete_file")
    public String delFile(@RequestParam("id") Integer id){
        fileService.deleteByKey(id);
        return "redirect:query_all_files";
    }

    @PostMapping("/search_uploader")
    public String SearchUploader(Uploadfile uploadfile, Model model){
        UploadfileExample uploadfileExample = new UploadfileExample();
        UploadfileExample.Criteria criteria = uploadfileExample.createCriteria();
        criteria.andUploadnameLike("%"+uploadfile.getUploadname()+"%");
        List<Uploadfile> uploadfiles = fileService.selectByExample(uploadfileExample);
        model.addAttribute("uploadfiles",uploadfiles);
        return "index/download";
    }

}
