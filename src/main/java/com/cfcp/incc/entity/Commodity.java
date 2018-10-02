package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


/**
 *
 * 全部 1 status > 0
 * 待审 2 status = 1
 * 审核中 3 1 < status <5
 * 终审通过 4 status = 5
 * 所有未通过 5 status < 0
 * 待一审（同待审和） 12 status = 1
 * 一审通过 6 status = 2
 * 一审未过 7 status = -2
 * 待缴费（一审通过） 6  status = 2
 * 已缴费（待复审） 8 status = 3
 * 复审通过（待终审） 9  status = 4
 * 待终审 13  status = 4
 * 复审未通过 10 status = -4
 * 终审未通过 11 status = -5
 * 终审通过(终审通过) 14 status = 5
 * Created by zhyj on 2017/7/8.
 */
/**
 * 商品
 */
public class Commodity extends BaseEntity {

    public static final int STATUS_DELETED = 0;
    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_FIRST = 2;
    public static final int STATUS_SECOND = 3;
    public static final int STATUS_THIRD = 4;
//    public static final int STATUS_FOURTH = 5;
    public static final int STATUS_FINAL = 5;
    public static final int STATUS_FAILED = -2;
    public static final int STATUS_FIRST_FAILED = -2;
    public static final int STATUS_RE_FAILED = -4;
    public static final int STATUS_FINAL_FAILED = -5;

//    private String id;

    private String name;

    private String category;
    private Dictionary categoryPo;

    private String industry;
    private Dictionary industryPo;

    private String pic;

    private String distributorId;

    private String companyId;

    private Company company;

    private String factoryId;

    private Company factory;

    private String brandId;

    private Brand brand;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String creator;

    private Integer status;
    private String statusValue;

    private List<OtherQualification> otherQualifications;

    private List<SpecialItem> specialItems;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId == null ? null : distributorId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;

        switch (status){
            case -2 : this.statusValue = "不通过"; break;
            case -4 : this.statusValue = "不通过"; break;
            case -5 : this.statusValue = "不通过"; break;
            case 0 : this.statusValue = "删除"; break;
            case 1: this.statusValue = "待审核"; break;
            case 2: this.statusValue = "一审通过"; break;
            case 3: this.statusValue = "已缴费"; break;
            case 4: this.statusValue = "复审通过"; break;
            case 5: this.statusValue = "终审通过"; break;
            default:
                this.statusValue = "未通过";
        }

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getFactory() {
        return factory;
    }

    public void setFactory(Company factory) {
        this.factory = factory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Dictionary getCategoryPo() {
        return categoryPo;
    }

    public void setCategoryPo(Dictionary categoryPo) {
        this.categoryPo = categoryPo;
    }

    public Dictionary getIndustryPo() {
        return industryPo;
    }

    public void setIndustryPo(Dictionary industryPo) {
        this.industryPo = industryPo;
    }

    public List<OtherQualification> getOtherQualifications() {
        return otherQualifications;
    }

    public void setOtherQualifications(List<OtherQualification> otherQualifications) {
        this.otherQualifications = otherQualifications;
    }

    public List<SpecialItem> getSpecialItems() {
        return specialItems;
    }

    public void setSpecialItems(List<SpecialItem> specialItems) {
        this.specialItems = specialItems;
    }

    public String getStatusValue() {
        return statusValue;
    }
}