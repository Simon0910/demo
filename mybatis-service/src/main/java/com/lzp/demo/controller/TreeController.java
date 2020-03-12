package com.lzp.demo.controller;

import com.lzp.demo.entity.Navigation;
import com.lzp.demo.mapper.NavigationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @RequestMapping(value = "/tree2.do", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, List<Node>> tree2(Integer id) throws Exception {
        Map<String, List<Node>> map = new HashMap();
        List<Node> leftNodes = new ArrayList<>();
        List<Node> rightNodes = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(6);
        ids.add(8);
        for (int i = 1; i < 20; i++) {
            Node node = new Node();
            node.setId(i);
            if (ids.contains(i)) {
                node.setpId(0);
            } else {
                Random random = new Random();
                int index = random.nextInt(2);
                node.setpId(ids.get(index));
            }
            node.setName("id-" + i);
            leftNodes.add(node);
        }

        for (int i = 0; i < leftNodes.size(); i++) {
            if (i % 2 == 0) {
                Node node = leftNodes.get(i);
                if (node.getId() == 3
                        || node.getId() == 6
                        || node.getId() == 8
                ) {
                    rightNodes.add(node);
                }
            }
        }
        map.put("leftNodes", leftNodes);
        map.put("rightNodes", rightNodes);
        return map;
    }

    class Node {
        Integer id;
        Integer pId;
        String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getpId() {
            return pId;
        }

        public void setpId(Integer pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
