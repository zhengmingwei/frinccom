package com.cfcp.incc.entity;

/**
 * Created by zhyj on 16/11/19.
 */

import java.util.HashMap;
import java.util.Map;

public class BaseEntity {
    protected Integer rowCount;
    protected Integer pageCount;
    protected Integer pageSize = 10;
    protected Integer pageNum;
    protected Integer currentUserId;
    protected String id;
    protected String key;

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.key = id;
    }

    public String getKey(){
        return this.key;
    }

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageSize", pageSize+"");
        map.put("pageNum", pageNum+"");
        return map;
    }

    public static Map<String, String> createMap() {
        return new BaseEntity().toMap();
    }

}

