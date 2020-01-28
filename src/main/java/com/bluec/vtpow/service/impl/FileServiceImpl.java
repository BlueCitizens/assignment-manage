package com.bluec.vtpow.service.impl;

import com.bluec.vtpow.mapper.WorkMapper;
import com.bluec.vtpow.po.WorkApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;

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
        String fileName = "abc.txt"; //文件名及类型
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
    public final String saveFile(int work_id, String fileName, InputStream input) {
        String msg = "";
        String path = workMapper.getPathByWorkId(work_id);
        System.out.println(path);
        int index;
        byte[] bytes = new byte[1024];
        File file = new File(path, fileName);
        FileOutputStream fileOutputStream = null;
        if (file.exists()) {
            msg += "over_write file";
        } else {
            msg += "write file";
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
}
