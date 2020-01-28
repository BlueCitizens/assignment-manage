package com.bluec.vtpow.controller;

import com.bluec.vtpow.pagemodel.FullWorkInfo;
import com.bluec.vtpow.po.UploadHistory;
import com.bluec.vtpow.po.WorkApply;
import com.bluec.vtpow.service.impl.FileServiceImpl;
import com.bluec.vtpow.service.impl.UserServiceImpl;
import com.bluec.vtpow.service.impl.WorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/15 19:38
 */

@Controller
public class WorkController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    WorkServiceImpl workService;
    @Autowired
    FileServiceImpl fileService;

    //获取单个人自己的作业 拼表直接看workmapper
    @RequestMapping(value = "/get_own_work", method = RequestMethod.POST)
    public @ResponseBody
    List<FullWorkInfo> getOwnWork(@RequestParam("stu_id") String stu_id) throws Exception {
        List<FullWorkInfo> res = workService.getWork(stu_id);
        System.out.println(res.toString());
        return res;
    }

    //获取单个人自己的提交记录
    @RequestMapping(value = "/get_own_history", method = RequestMethod.POST)
    public @ResponseBody
    List<UploadHistory> getOwnHistory(@RequestParam("stu_id") String stu_id) throws Exception {
        System.out.println(stu_id);
        List<UploadHistory> res = workService.getHistory(stu_id);
        System.out.println(res.toString());
        return res;
    }

    //添加单个任务（作业）
    @RequestMapping(value = "/add_work", method = RequestMethod.POST)
    public @ResponseBody
    int addWork(@RequestBody WorkApply workApply, HttpServletRequest request) throws Exception {
        System.out.println(workApply.toString());
        String msg = fileService.generateWorkPath(workApply);
        if (msg == "duplicate") {
            return 0;//存在同名目录，重复的作业
        }

        return 1;//正常返回
    }
}
