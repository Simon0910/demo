package com.example.mvc.controller;

import com.example.mvc.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-05 14:17
 */
@Slf4j
@RequestMapping("/master/${master.api.env:}/mdes/digitization/${master.api.majorVer:}/${master.api.minorVer:}")
@Controller
public class DemoController {

    @Value("${master.api.env:}")
    private String env;

    @PostMapping("/notifyTokenUpdated")
    public @ResponseBody
    Person getPerson(@RequestBody Map<String, Object> requestSchema) {
        Person person = new Person();
        person.setName(EnvironmentAdapter.getEnv(env));
        return person;
    }
}
