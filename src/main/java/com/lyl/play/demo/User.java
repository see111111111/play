package com.lyl.play.demo;


import java.util.Objects;

public class User {

    private Integer id;
    private String name;
    private Integer sex;
    public String address;
    public Double price;

    public User(Integer id, String name, Integer sex, String address, Double price) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.price = price;
    }
    public User(){

    }

    private void aaa(String value){

    }

    private void bbb(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(address, user.address) &&
                Objects.equals(price, user.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, address, price);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
