package com.evening.bean;


/**
 * 客户实体类
 * @author Evening
 */
public class Customer {
    private int id;
    private String customName;
    private String customPassword;
    private int age;
    private String address;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomPassword() {
        return customPassword;
    }

    public void setCustomPassword(String customPassword) {
        this.customPassword = customPassword;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "customName: " + customName + "\n" +
                "customPassword: " + customPassword + "\n" +
                "age: " + age + "\n" +
                "address: " + address + "\n" +
                "phone: " + phone + "\n" +
                "email: " + email;
    }
}
