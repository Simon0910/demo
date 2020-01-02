package com.example.mvc.controller;

import com.example.mvc.model.HasNAV;
import com.example.mvc.model.Person;
import com.example.mvc.model.TokenNotifyReq;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 16:52
 */
@RestController
// @Controller
public class IndexController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "hello cumtomer";
    }

    @PostMapping("/master/notify")
    public void notifyTokenUpdated(@RequestBody TokenNotifyReq notifyReq, HttpServletRequest request) {
        System.out.println("========>");
        System.out.println(notifyReq);
        System.out.println("========>");
        System.out.println(request);
    }


    @PostMapping(value = "/my-valid", produces = "application/json;charset=UTF-8")
    public void myValid(@RequestBody @Valid Person person, BindingResult bindingResult) {
        System.out.println("========>");
        System.out.println(person);
        System.out.println("========>");
    }

    @RequestMapping(value = "/my-valid2", method = RequestMethod.POST)
    public Person myValid2(@RequestBody @Valid Person person, BindingResult bindingResult) {
        System.out.println("========>");
        System.out.println(person);
        System.out.println("========>");
        return person;
    }

    @RequestMapping(value = "/hasNAV", method = RequestMethod.POST)
    public HasNAV nameAndValue(@RequestBody @Valid HasNAV hasNAV, BindingResult bindingResult) {
        System.out.println("========>");
        System.out.println(hasNAV);
        System.out.println("========>");
        return hasNAV;
    }

}
