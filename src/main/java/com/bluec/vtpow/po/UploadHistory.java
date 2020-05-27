package com.bluec.vtpow.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/22 0:02
 */
public class UploadHistory implements Serializable {
    int business_id;
    int work_id;
    String file_name;
    int version;
    Date time;
    String stu_id;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UploadHistory{" +
                "business_id=" + business_id +
                ", work_id=" + work_id +
                ", file_name='" + file_name + '\'' +
                ", version=" + version +
                ", time=" + time +
                ", stu_id='" + stu_id + '\'' +
                '}';
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
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

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }
}
