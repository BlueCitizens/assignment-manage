package com.bluec.vtpow.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/25 23:35
 */
public class FileUtil {
    public FileUtil() {
    }

    //获取part文件名的函数，应该会经常复用
    public static String getFileName(Part part) {
        String head = part.getHeader("Content-Disposition");
        String fileName = head.substring(head.indexOf("filename=\"") + 10, head.lastIndexOf("\""));
        System.out.println(fileName);
        return fileName;
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static List<File> readfile(String filepath) throws FileNotFoundException, IOException {
        List<File> fileList = new ArrayList<File>();
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                //System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    fileList.add(readfile);
                    if (!readfile.isDirectory()) {
                        //System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return fileList;
    }

    /**
     * 下载文件
     * @param outputStream 下载输出流
     * @param filePath     需要下载文件的路径
     */

    public static void downloadFile(OutputStream outputStream, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("目标文件不存在");
            //-- 需要下载压塑包文件不存在

            return;

        }

        FileInputStream inputStream = null;

        try {

            inputStream = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];

            inputStream.read(data);

            outputStream.write(data);

            outputStream.flush();

        } catch (IOException e) {

            /*Xlogger.error(XMsgError.buildSimple(CompressDownloadUtil.class.getName(), "downloadZip", e));*/

        } finally {

            try {

                if (Objects.nonNull(inputStream)) {

                    inputStream.close();

                }

                if (Objects.nonNull(outputStream)) {

                    outputStream.close();

                }

            } catch (IOException e) {

                /*Xlogger.error(XMsgError.buildSimple(CompressDownloadUtil.class.getName(), "downloadZip", e));*/

            }

        }

    }

    /**
     * 下载文件
     * @param response
     * @return 成功下载文件，失败返回0
     * @throws IOException
     */
    public static int downloadFileByPath(String filePath, String downloadName, HttpServletResponse response) throws IOException {
        System.out.println("文件路径：" + filePath);
        //得到该文件
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("no such file!");
            return 0;//文件不存在就退出方法
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        //设置Http响应头告诉浏览器下载这个附件,下载的文件名也是在这里设置的
        response.setHeader("Content-Disposition", "attachment;Filename=" + new String(downloadName.getBytes("utf-8"), "ISO8859-1"));
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[2048];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }
        fileInputStream.close();
        outputStream.close();

        return 0;
    }


}

