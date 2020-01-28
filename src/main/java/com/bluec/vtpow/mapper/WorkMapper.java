package com.bluec.vtpow.mapper;

import com.bluec.vtpow.pagemodel.Work;
import com.bluec.vtpow.pagemodel.Course;
import com.bluec.vtpow.pagemodel.FullWorkInfo;
import com.bluec.vtpow.po.UploadHistory;
import com.bluec.vtpow.po.WorkApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/14 10:35
 */

@Mapper
public interface WorkMapper {
    int addWork(WorkApply workApply);

    List<FullWorkInfo> getWork(String stu_id);

    String getPathByWorkId(int work_id);

    List<Integer> getExactHistory(String stu_id, int work_id);

    int uploadWork(int work_id, String fileName, String stu_id, int version);

    List<UploadHistory> getHistoryByStuId(String stu_id);

    List<Course> getAllCourse();

    List<Work> getAllWork();

}
