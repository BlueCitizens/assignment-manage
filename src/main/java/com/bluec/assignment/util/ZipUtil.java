package com.bluec.assignment.util;

import java.io.*;

import java.util.Arrays;

import java.util.List;

import java.util.Objects;

import java.util.zip.ZipEntry;

import java.util.zip.ZipOutputStream;


import javax.servlet.http.HttpServletResponse;


/**
 * @Author: BlueCitizens
 * @Date: 2020/1/25 20:48
 */
public class ZipUtil {


    private ZipUtil() {
    }


    /**
     * 设置下载响应头
     *
     * @param response
     * @return
     * @author hongwei.lian
     * @date 2018年9月7日 下午3:01:59
     */

    public static HttpServletResponse setDownloadResponse(HttpServletResponse response, String downloadName) throws UnsupportedEncodingException {

        response.reset();

        response.setCharacterEncoding("utf-8");

        response.setContentType("application/octet-stream");

        response.setHeader("Content-Disposition", "attachment;filename=" + new String(downloadName.getBytes("utf-8"),"ISO8859-1"));

        return response;

    }


    /**
     * 字符串转换为整型数组
     *
     * @param param
     * @return
     * @author hongwei.lian
     * @date 2018年9月6日 下午6:38:39
     */

    public static Integer[] toIntegerArray(String param) {

        return Arrays.stream(param.split(","))

                .map(Integer::valueOf)

                .toArray(Integer[]::new);

    }


    /**
     * 将多个文件压缩到指定输出流中
     *
     * @param files        需要压缩的文件列表
     * @param outputStream 压缩到指定的输出流
     * @author hongwei.lian
     * @date 2018年9月7日 下午3:11:59
     */

    public static void compressZip(List<File> files, OutputStream outputStream) {

        ZipOutputStream zipOutStream = null;

        try {

            //-- 包装成ZIP格式输出流

            zipOutStream = new ZipOutputStream(new BufferedOutputStream(outputStream));

            // -- 设置压缩方法

            zipOutStream.setMethod(ZipOutputStream.DEFLATED);

            //-- 将多文件循环写入压缩包

            for (int i = 0; i < files.size(); i++) {

                File file = files.get(i);

                FileInputStream filenputStream = new FileInputStream(file);

                byte[] data = new byte[(int) file.length()];

                filenputStream.read(data);

                //-- 添加ZipEntry，并ZipEntry中写入文件流，这里，加上i是防止要下载的文件有重名的导致下载失败

                zipOutStream.putNextEntry(new ZipEntry(file.getName()));

                zipOutStream.write(data);

                filenputStream.close();

                zipOutStream.closeEntry();
            }
        } catch (IOException e) {
           /* Xlogger.error(XMsgError.buildSimple(CompressDownloadUtil.class.getName(), "downloadallfiles", e));*/
        } finally {
            try {
                if (Objects.nonNull(zipOutStream)) {
                    zipOutStream.flush();
                    zipOutStream.close();
                }
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
               /* Xlogger.error(XMsgError.buildSimple(CompressDownloadUtil.class.getName(), "downloadallfiles", e));*/
            }
        }
    }



    /**
     * 删除指定路径的文件
     *
     * @param filepath
     * @author hongwei.lian
     * @date 2018年9月7日 下午3:44:53
     */

    public static void deleteFile(String filepath) {

        File file = new File(filepath);

        deleteFile(file);

    }


    /**
     * 删除指定文件
     *
     * @param file
     * @author hongwei.lian
     * @date 2018年9月7日 下午3:45:58
     */

    public static void deleteFile(File file) {

        //-- 路径为文件且不为空则进行删除

        if (file.isFile() && file.exists()) {

            file.delete();

        }

    }


}

