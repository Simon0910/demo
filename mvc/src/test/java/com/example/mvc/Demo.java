package com.example.mvc;

import com.alibaba.fastjson.JSON;
import com.example.mvc.json.JsonEngine;
import com.example.mvc.model.Person;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(11);
        p.setName("san");
        p.setBrithday(new Date());
        System.out.println(JSON.toJSONString(p));

        int productId = 100;
        String updateTimeNow = "2020-02-21 12:20:15";
        String productInfoJSON = "{\"id\":" + productId + ", \"name\": \"iphone7手机Kafka\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"brithday\": \"" + updateTimeNow + "\"}";
        Person person = JSON.parseObject(productInfoJSON, Person.class);
        System.out.println(person);
    }
}
