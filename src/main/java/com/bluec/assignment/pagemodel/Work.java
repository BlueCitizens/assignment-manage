package com.bluec.assignment.pagemodel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/25 13:51
 */
public class Work implements Serializable {
    int work_id;
    String work_name;
    int course_id;
    String course_name;
    String naming_rule;
    String path;
    Date deadline;
    int sub_count;

    @Override
    public String toString() {
        return "Work{" +
                "work_id=" + work_id +
                ", work_name='" + work_name + '\'' +
                ", course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", naming_rule='" + naming_rule + '\'' +
                ", path='" + path + '\'' +
                ", deadline=" + deadline +
                ", sub_count=" + sub_count +
                '}';
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
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

    public int getSub_count() {
        return sub_count;
    }

    public void setSub_count(int sub_count) {
        this.sub_count = sub_count;
    }
}
