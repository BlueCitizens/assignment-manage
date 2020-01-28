package com.bluec.vtpow.pagemodel;

import java.io.Serializable;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/22 23:35
 */
public class Course implements Serializable {
    int course_id;
    String course_name;

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

    @Override
    public String toString() {
        return "course{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                '}';
    }
}
