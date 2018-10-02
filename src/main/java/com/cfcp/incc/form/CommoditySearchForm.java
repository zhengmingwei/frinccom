package com.cfcp.incc.form;

import java.util.Date;

/**
 * Created by zhyj on 2017/7/23.
 */
public class CommoditySearchForm {

    private String companyName;
    private String orgCode;
    private String name;
    private Date pubDateBegin;
    private Date pubDateEnd;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPubDateBegin() {
        return pubDateBegin;
    }

    public void setPubDateBegin(Date pubDateBegin) {
        this.pubDateBegin = pubDateBegin;
    }

    public Date getPubDateEnd() {
        return pubDateEnd;
    }

    public void setPubDateEnd(Date pubDateEnd) {
        this.pubDateEnd = pubDateEnd;
    }
}
