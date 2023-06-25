package com.cc.sprigboot2demo.bean;

import lombok.Data;

/**
 * @author cc
 * @date 2023/6/25-22:43
 */
@Data
public class User {
    private String name;
    private Integer age;

    private Pet pet;

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }

}
