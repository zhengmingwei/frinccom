package com.cfcp.incc.dto.user;

public class ImgDto {
/*"uid":"e9a884e2d61f20c9893488b76820b28d025a8918",
        "url":"http://icon.cfcpincc.com/incc/file/get/1/1/e9a884e2d61f20c9893488b76820b28d025a8918.png",
        "ext":"png",
        "name":"小米-绿色.png",
        "status":"done"
}*/

    private String uid;
    private String url;
    private String ext;
    private String name;
    private String status;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getName() {
        return name;
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
