package com.bluec.vtpow.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluec.vtpow.pagemodel.Work;
import com.bluec.vtpow.pagemodel.Course;
import com.bluec.vtpow.po.User;
import com.bluec.vtpow.service.ExcelService;
import com.bluec.vtpow.service.impl.FileServiceImpl;
import com.bluec.vtpow.service.impl.UserServiceImpl;
import com.bluec.vtpow.service.impl.WorkServiceImpl;
import com.bluec.vtpow.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 18:26
 */

@Controller
public class AdminController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    private ExcelService importService;
    @Autowired
    FileServiceImpl fileService;
    @Autowired
    WorkServiceImpl workService;

    //读取文件流，解析excel文件。返回list给前端ajax处理
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    List<User> fileupload(@RequestParam(value = "file", required = false) MultipartFile uploadfile, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        /*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("uploadfile");
        if (file.isEmpty()) {
            return "文件不能为空";
        }*/
        Part part;
        part = request.getPart("file");
        String fileName = FileUtil.getFileName(part);
        InputStream inputStream = part.getInputStream();
        List list = importService.getBankListByExcel(inputStream, fileName);
        inputStream.close();
        System.out.println(list);
        return list;
    }

    // 批量导入学生（用户）信息
    // 实际上经过了ajax传Excel文件->controller解析返回->ajax返回list->controller导入的复杂过程
    // 可以考虑直接通过前端代码解析
    @RequestMapping(value = "/save_all", method = RequestMethod.POST)
    @ResponseBody
    public int uploadExcel(@RequestBody ArrayList<User> users, HttpServletRequest request) throws Exception {

        List<User> list = new ArrayList<User>();
        User user = new User();
        list = users;
        System.out.println("List==" + list);
        userService.saveAllUser(list);
        return 1;
    }

    //静态页面无法直接获取后端传入的session，借助controller返回+ajax传入静态页面
    //但是静态页面之间可以互传session，关闭浏览器时清除
    @RequestMapping(value = "/get_session", method = RequestMethod.GET)
    public void getSession(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if (session.isNew()) {
            response.getWriter().write("new");
            System.out.println("出错");
        } else {
            String username = (String) session.getAttribute("username");
            String id = (String) session.getAttribute("id");
            String pwd_status = (String) session.getAttribute("status");
            System.out.println(username);
            User user = new User(id, username, pwd_status);
            JSONObject jsonObject = new JSONObject();
            System.out.println(jsonObject.toJSONString(user));
            response.getWriter().write(jsonObject.toJSONString(user));
        }
    }

    @RequestMapping(value = "/get_all_user", method = RequestMethod.POST)
    public @ResponseBody
    List<User> getAllUser() throws Exception {
        List<User> res = userService.getAllUser();
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/get_all_course", method = RequestMethod.POST)
    public @ResponseBody
    List<Course> getAllCourse() throws Exception {
        List<Course> res = workService.getAllCourse();
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/get_all_work", method = RequestMethod.POST)
    public @ResponseBody
    List<Work> getAllWork() throws Exception {
        List<Work> res = workService.getAllWork();
        System.out.println(res.toString());
        return res;
    }

}
