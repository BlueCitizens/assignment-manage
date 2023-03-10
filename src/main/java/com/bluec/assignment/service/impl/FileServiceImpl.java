package com.bluec.assignment.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.bluec.assignment.mapper.WorkMapper;
import com.bluec.assignment.po.UploadHistory;
import com.bluec.assignment.po.WorkApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 16:44
 */

@Service
@PropertySource("classpath:custom.properties")
public class FileServiceImpl {

    @Autowired
    WorkMapper workMapper;

    @Value("${work.rootPath}")
    private String path;

    public String generateWorkPath(WorkApply workApply) {
        //String path = "D:\\testcode";
        if (workApply.getCourse_id() == 0) {
            System.out.println("course empty");
        } else {
            path += "\\";
            path += workApply.getCourse_id();
        }
        if (workApply.getWork_name() == null) {
            System.out.println("work empty");
        } else {
            path += "\\";
            path += workApply.getWork_name();
        }
        System.out.println(path);
        System.out.println("测试路径配置" + path);
        String msg = createFilePath(path);
        System.out.println(msg);
        if (msg == "同名目录已存在!") {
            return "duplicate";
        }
        workApply.setPath(path);
        workMapper.addWork(workApply);
        return "ok";
    }

    public String createFilePath(String path) {
        String p = "D:\\testcode\\Index"; //所创建文件目录
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs(); //创建目录
        } else {
            return "同名目录已存在!";
        }
        //生成目录说明文件 不需要就注释掉
        String fileName = "说明readme.txt"; //文件名及类型
        File file = new File(path, fileName);
        if (!file.exists()) { //surround with try/catch
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    /**
     * 将InputStream写入本地文件
     *
     * @param input 输入流
     * @throws IOException
     */
    public final String saveFile(String stu_id, int work_id, String fileName, InputStream input) {
        UploadHistory uploadHistoryOther = workMapper.getHistoryByFileName(fileName);
        System.out.println(uploadHistoryOther == null);
        //如果已存在其他用户上传的同名文件 返回错误
        if (uploadHistoryOther != null) {
            if (!stu_id.equals(uploadHistoryOther.getStu_id())) {
                return "文件名冲突";
            }
        }
        //上传逻辑优化 上传新文件先删除对应旧文件
        UploadHistory uploadHistory = workMapper.getNewestUpload(work_id, stu_id);
        System.out.println("旧的文件名：" + uploadHistory.getFile_name());
        String msg = "";
        String path = workMapper.getPathByWorkId(work_id);
        deleteFile(path + "\\" + uploadHistory.getFile_name());
        System.out.println(path);
        int index;
        byte[] bytes = new byte[1024];
        File file = new File(path, fileName);
        FileOutputStream fileOutputStream = null;
        if (file.exists()) {
            msg += "overwrite";
        } else {
            msg += "write";
        }
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "save error";
        }
        try {
            while ((index = input.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, index);
                fileOutputStream.flush();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return "save error";
        }
        try {
            fileOutputStream.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 删除文件	 * 	 * @param pathname	 * @return	 * @throws IOException
     */
    public static boolean deleteFile(String pathname) {
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println(pathname + "old文件已经被成功删除");
        }
        return result;
    }

    List<String> fileNameList = null;

    public JSONArray getAllFileNameByPath(int work_id){
        fileNameList = new ArrayList<String>();
        String path = workMapper.getPathByWorkId(work_id);
        System.out.println(path);
        File f = new File(path);
        getFileName(f);
        System.out.println(fileNameList);
        JSONArray jsonObject = JSONArray.parseArray(fileNameList.toString());
        return jsonObject;
    }

    //递归查找文件
    public void getFileName(File file) {
        if (file != null) {
            File[] f = file.listFiles();
            if (f != null) {
                for (File value : f) {
                    getFileName(value);
                }
            } else {
                System.out.println(file);
                fileNameList.add("{\"fileName\":\""+file.getName()+"\"}");
            }
        }
    }
}
