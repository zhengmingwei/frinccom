package com.cfcp.incc.entity;

/**
 * Created by zyh on 16/4/18.
 */
public class UploadedFile {
    private String uid;
    private String url;
    private String ext;
    private String name;
    private String status;

    public UploadedFile(String uid, String url, String ext, String name, String status) {
        this.url = url;
        this.ext = ext;
        this.name = name;
        this.uid = uid;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        if(ext!=null) return ext.toLowerCase();
        return null;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }



    public String getUrl() {
        return url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
