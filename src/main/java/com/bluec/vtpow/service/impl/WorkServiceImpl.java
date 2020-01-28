package com.bluec.vtpow.service.impl;

import com.bluec.vtpow.mapper.WorkMapper;
import com.bluec.vtpow.pagemodel.Work;
import com.bluec.vtpow.pagemodel.Course;
import com.bluec.vtpow.pagemodel.FullWorkInfo;
import com.bluec.vtpow.po.UploadHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/15 18:52
 */

@Service
public class WorkServiceImpl {
    @Autowired
    WorkMapper workMapper;

    public List<FullWorkInfo> getWork(String stu_id) {
        return workMapper.getWork(stu_id);
    }

    public String getPathByWorkId(int work_id) {
        return workMapper.getPathByWorkId(work_id);
    }

    public List<UploadHistory> getHistory(String stu_id) {
        return workMapper.getHistoryByStuId(stu_id);
    }

    public List<Course> getAllCourse() {
        return workMapper.getAllCourse();
    }

    public List<Work> getAllWork() {
        return workMapper.getAllWork();
    }

    public String uploadWork(int work_id, String fileName, String stu_id) {
        int version;
        /*Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String currentDatetime = dateFormat.format(now);
        System.out.println("current time: "+currentDatetime);*/
        List<Integer> versions = workMapper.getExactHistory(stu_id, work_id);
        if (!versions.isEmpty()) {
            versions.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });
            version = versions.get(versions.size() - 1) + 1;
        } else {
            version = 1;
        }
        int i = workMapper.uploadWork(work_id, fileName, stu_id, version);
        if (i == 0) {
            return "db ok";
        } else {
            return "db error";
        }
    }
}
