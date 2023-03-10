package com.bluec.assignment.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluec.assignment.service.impl.FileServiceImpl;
import com.bluec.assignment.service.impl.WorkServiceImpl;
import com.bluec.assignment.util.FileUtil;
import com.bluec.assignment.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 16:41
 */

@Controller
public class FileController {

    @Autowired
    FileServiceImpl fileServiceImpl;
    @Autowired
    WorkServiceImpl workServiceImpl;

    //上传作业
    @RequestMapping(value = "/bus/work_upload", method = RequestMethod.POST)
    public @ResponseBody
    String workUpload(@RequestParam(value = "file", required = false) MultipartFile uploadfile, @RequestParam(value = "data", required = false) String data, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Part part;
        part = request.getPart("file");
        String fileName = FileUtil.getFileName(part);
        JSONObject jsonObject = JSONObject.parseObject(data);
        String stu_id = String.valueOf(jsonObject.get("stu_id"));
        int work_id = Integer.parseInt(String.valueOf(jsonObject.get("work_id")));
        InputStream inputStream = part.getInputStream();
        String msg = fileServiceImpl.saveFile(stu_id, work_id, fileName, inputStream);
        if (msg.equals("save error")) {
            return "save error";
        }
        msg += workServiceImpl.uploadWork(work_id, fileName, stu_id);
        inputStream.close();
        System.out.println("学生id和作业id：" + stu_id + work_id);
        System.out.println("作业上传结果：" + msg);
        return msg;
    }

    //？？忘了这是干啥的
    @RequestMapping(value = "/work_check", method = RequestMethod.POST)
    public @ResponseBody
    String workCheck(@RequestParam(value = "file", required = false) MultipartFile uploadfile, @RequestParam(value = "data", required = false) String data, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Part part;
        part = request.getPart("file");
        String fileName = FileUtil.getFileName(part);
        JSONObject jsonObject = JSONObject.parseObject(data);
        String stu_id = String.valueOf(jsonObject.get("stu_id"));
        int work_id = Integer.parseInt(String.valueOf(jsonObject.get("work_id")));
        InputStream inputStream = part.getInputStream();
        String msg = fileServiceImpl.saveFile(stu_id, work_id, fileName, inputStream);
        if (msg.equals("save error")) {
            return "save error";
        }
        msg += workServiceImpl.uploadWork(work_id, fileName, stu_id);
        inputStream.close();
        System.out.println("学生id和作业id：" + stu_id + work_id);
        System.out.println("作业上传结果：" + msg);
        return msg;
    }

    //多文件压缩下载

    @RequestMapping("/download")
    public void downloadWork(@RequestParam(name = "work_id") String work_id, @RequestParam(name = "file_name") String file_name, HttpServletResponse response) throws IOException {
        System.out.println("download request: work_id=" + work_id + "name=" + file_name);
        List<File> files = FileUtil.readfile(workServiceImpl.getPathByWorkId(Integer.parseInt(work_id)));
        //-- 检查需要下载多文件列表中文件路径是否都存在
        for (File file : files) {
            if (!file.exists()) {
                //-- 需要下载的文件中存在不存在地址
                return;
            }
        }
        //-- 响应头的设置
        String downloadName = file_name + ".zip";
        System.out.println(downloadName);
        ZipUtil.setDownloadResponse(response, downloadName);
        //-- 第二种方案,不产生临时文件，直接写文件入输出流
        try {
            //-- 将多个文件压缩写进响应的输出流
            ZipUtil.compressZip(files, response.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping("/dld_excel_temp")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        System.out.println("download excel request");

/**
 * return File
 * path : document/xxxxxx.docx

 */

        String file = this.getClass().getClassLoader().getResource("static/tempFiles/template.xlsx").getPath();//获取文件路径
        System.out.println(file);
        FileUtil.downloadFileByPath(file, "模板.xlsx", response);
    }

    @RequestMapping(value = "/peek_path_filename", method = RequestMethod.POST)
    public @ResponseBody
    JSONArray getPathFileName(@RequestParam("work_id") int work_id) throws Exception {
        return fileServiceImpl.getAllFileNameByPath(work_id);
    }
}
