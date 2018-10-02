package com.cfcp.incc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by zyh on 16/4/1.
 */
public class DemoEntity {
    private long id;
    private String name;
    private Date birthdate;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String work;
    private String pic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public String getGenderStr() {
        return gender!=null && gender.equalsIgnoreCase("F")?"女":"男";
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public DemoEntity(){}

    public DemoEntity(Long id, String name, Date birthdate, Integer age, String gender, String phone, String address, String work, String pic) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.work = work;
        this.pic = pic;
    }

}
