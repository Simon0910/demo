package com.example.mvc.controller;

import com.example.mvc.common.PageInfo;
import com.example.mvc.common.PageParams;
import com.example.mvc.mapper.Category;
import com.example.mvc.model.HasNAV;
import com.example.mvc.model.Person;
import com.example.mvc.model.TokenNotifyReq;
import com.example.mvc.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 16:52
 */
@Slf4j
@RestController
// @Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

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


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Category query(@RequestBody Category category, int pageNum, Integer pageSize, String orderBy) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("createTime", "2018-03-16 17:46:27");

        PageParams<Category> pageParams = new PageParams<>();
        pageParams.setPageNum(pageNum);
        pageParams.setPageSize(pageSize);
        pageParams.setEntity(category);
        pageParams.setOrderBy(orderBy);
        pageParams.setSqlWhere(conditions);

        int count = categoryService.count(pageParams);
        int totalPage = PageInfo.getTotalPage(count, pageSize);
        for (pageNum = 1; pageNum <= totalPage; pageNum++) {
            pageParams.setPageNum(pageNum);
            List<Category> categories = categoryService.selectTaskListByPage(pageParams);
            if (categories == null || categories.isEmpty()) {
                break;
            }
            log.info("==> page size:" + categories.size());

            // 处理当前页
            for (int i = 0, size = categories.size(); i < size; i++) {

            }
        }


        return category;
    }

}
