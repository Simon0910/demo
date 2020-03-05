package com.lzp.demo.controller;

import com.lzp.demo.entity.Navigation;
import com.lzp.demo.mapper.NavigationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    NavigationMapper navigationMapper;

    @RequestMapping(value = "/tree.do", method = RequestMethod.GET)
    public @ResponseBody
    List<Navigation> tree(Integer id) throws Exception {
        if (id == null) {
            id = 0;
        }
        List<Navigation> navList = navigationMapper.selectListByNId(id);
        return navList;
    }

}
