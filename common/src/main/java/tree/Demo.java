package tree;

import java.util.*;

/**
 * @author lzp on 2020/8/24.
 */
public class Demo {
    public static void main(String[] args) {
        List<NodeEntity> nodeList = getNodes();

        //转换 list ==> tree
        List<TreeNode<NodeEntity>> treeNodes = TreeNodeUtil.listToTree(nodeList);
        Map<String, Object> result = new HashMap<>();
        result.put("nodeTreeNodeList", treeNodes);
        System.out.println(result);

        // 根据id获取
        TreeNode<NodeEntity> treeNodeOne = TreeNodeUtil.getTreeNodeById(treeNodes, "120000");
        System.out.println(treeNodeOne);

        // 根据id集合1
        Set<Integer> set1 = TreeNodeUtil.getIdSetByTreeNode(treeNodeOne, Integer.class);
        System.out.println(set1);

        // 根据id集合2
        Set<Integer> set2 = TreeNodeUtil.getIdSetByTreeNodeList(treeNodes, Integer.class);
        System.out.println(set2);

        //转换 tree ==> list
        List<NodeEntity> nodes = TreeNodeUtil.treeToList(treeNodes);
        System.out.println(nodes);
    }

    private static List<NodeEntity> getNodes() {
        //国节点 中国
        NodeEntity rootNode = new NodeEntity();
        rootNode.setNodeId(110000);
        rootNode.setNodeName("中国");
        rootNode.setParentNodeId(0);
        rootNode.setTime(System.currentTimeMillis());

        //省节点 广东
        NodeEntity pNode1 = new NodeEntity();
        pNode1.setNodeId(120000);
        pNode1.setNodeName("广东");
        pNode1.setParentNodeId(110000);
        pNode1.setTime(System.currentTimeMillis());

        //市节点 广州
        NodeEntity cNode1 = new NodeEntity();
        cNode1.setNodeId(120001);
        cNode1.setNodeName("广州");
        cNode1.setParentNodeId(120000);
        cNode1.setTime(System.currentTimeMillis());

        //区节点 广州
        NodeEntity aNode1 = new NodeEntity();
        aNode1.setNodeId(1200011);
        aNode1.setNodeName("天河区");
        aNode1.setParentNodeId(120001);
        aNode1.setTime(System.currentTimeMillis());

        //子节点 湖南
        NodeEntity pNode2 = new NodeEntity();
        pNode2.setNodeId(130000);
        pNode2.setNodeName("湖南");
        pNode2.setParentNodeId(110000);
        pNode2.setTime(System.currentTimeMillis());

        //市节点 长沙
        NodeEntity cNode2 = new NodeEntity();
        cNode2.setNodeId(130001);
        cNode2.setNodeName("长沙");
        cNode2.setParentNodeId(130000);
        cNode2.setTime(System.currentTimeMillis());

        //子节点 上海
        NodeEntity pNode3 = new NodeEntity();
        pNode3.setNodeId(140000);
        pNode3.setNodeName("上海");
        pNode3.setParentNodeId(110000);
        pNode3.setTime(System.currentTimeMillis());

        List<NodeEntity> nodeList = new ArrayList<>();
        nodeList.add(rootNode);
        nodeList.add(pNode1);
        nodeList.add(cNode1);
        nodeList.add(aNode1);
        nodeList.add(pNode2);
        nodeList.add(cNode2);
        nodeList.add(pNode3);
        return nodeList;
    }
}
