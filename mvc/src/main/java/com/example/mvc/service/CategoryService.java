package com.example.mvc.service;

import com.example.mvc.common.PageParams;
import com.example.mvc.mapper.Category;
import com.example.mvc.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lzp
 * @date: 2020/1/1
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public int count(PageParams pageParams) {
        return categoryMapper.count(pageParams);
    }

    public List<Category> selectTaskListByPage(PageParams pageParams) {
        return categoryMapper.selectListByPage(pageParams);
    }
}
