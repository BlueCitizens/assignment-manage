package com.bluec.assignment.pagemodel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/15 17:03
 */
public class FullWorkInfo implements Serializable {
    int business_id;
    String stu_id;
    int work_id;
    String work_name;
    int course_id;
    String course_name;
    String naming_rule;
    String path;
    String file_name;
    int version;
    Date time;
    Date deadline;
    int status;

    @Override
    public String toString() {
        return "UploadApply{" +
                "business_id=" + business_id +
                ", stu_id=" + stu_id +
                ", work_id=" + work_id +
                ", work_name='" + work_name + '\'' +
                ", course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", naming_rule='" + naming_rule + '\'' +
                ", path='" + path + '\'' +
                ", file_name='" + file_name + '\'' +
                ", version=" + version +
                ", time=" + time +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getNaming_rule() {
        return naming_rule;
    }

    public void setNaming_rule(String naming_rule) {
        this.naming_rule = naming_rule;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
