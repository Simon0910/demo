package tree;


import java.util.*;

/**
 * @author lzp on 2020/8/24.
 */
public class TreeNodeUtil {

    /**
     * TreeNodeUtil
     */
    private TreeNodeUtil() {
    }


    /**
     * list ==> tree
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> List<TreeNode<T>> listToTree(List<T> list) {
        List<TreeNode<T>> treeNodeList = new ArrayList<>();
        if (list == null || list.size() < 1) {
            return treeNodeList;
        }

        for (T t : list) {
            if (!isTreeNodeExist(list, t.getParentId())) {
                // 不存在以父ID为ID的点，说明是当前点是顶级节点
                TreeNode<T> treeNode = getTreeNode(list, t, 0);
                treeNodeList.add(treeNode);
            }
        }
        return treeNodeList;
    }


    /**
     * 根据实体获取TreeNode
     *
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    private static <T extends TreeNode> TreeNode<T> getTreeNode(List<T> list, T t, Integer level) {
        TreeNode<T> treeNode = new TreeNode<>();
        treeNode.setId(t.getId());
        treeNode.setParentId(t.getParentId());
        treeNode.setTitle(t.getTitle());
        treeNode.setChecked(t.getChecked());
        treeNode.setIcon(t.getIcon());
        treeNode.setAttributes(t.getAttributes());
        treeNode.setLevel(level++);
        t.setLevel(treeNode.getLevel());
        treeNode.setT(t);
        treeNode.setChildren(getChildTreeNodeList(list, treeNode, level));
        return treeNode;
    }


    /**
     * 获取指定父点的子树
     *
     * @param list
     * @param parentTreeNode
     * @param <T>
     * @return
     */
    private static <T extends TreeNode> List<TreeNode<T>> getChildTreeNodeList(List<T> list, TreeNode<T> parentTreeNode, Integer level) {
        List<TreeNode<T>> childTreeNodeList = new ArrayList<>();
        for (T t : list) {
            if (Objects.equals(parentTreeNode.getId(), t.getParentId())) {
                //如果父ID是传递树点的ID，那么就是传递树点的子点
                TreeNode<T> treeNode = getTreeNode(list, t, level);
                childTreeNodeList.add(treeNode);
            }
        }
        return childTreeNodeList;
    }


    /**
     * 根据ID判断该点是否存在
     *
     * @param list
     * @param id
     * @param <T>
     * @return
     */
    private static <T extends TreeNode> Boolean isTreeNodeExist(List<T> list, String id) {
        if (id == null || "".equals(id.trim())) {
            return false;
        }

        for (T t : list) {
            if (Objects.equals(t.getId(), id)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取根据指定ID所在点为父点的树
     *
     * @param treeNodeList
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> TreeNode<T> getTreeNodeById(List<TreeNode<T>> treeNodeList, String id) {
        if (id == null || "".equals(id.trim()) || treeNodeList == null || treeNodeList.size() < 1) {
            return null;
        }

        for (TreeNode<T> treeNode : treeNodeList) {
            if (id.equals(treeNode.getId())) {
                return treeNode;
            }

            if (treeNode.getChildren() != null && treeNode.getChildren().size() > 0) {
                TreeNode<T> childrenNode = getTreeNodeById(treeNode.getChildren(), id);
                if (childrenNode != null) {
                    return childrenNode;
                }
            }
        }
        return null;
    }

    /**
     * 将TreeList的所有点转换为ID的Set集合
     *
     * @param treeNodeList
     * @param clazz
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends TreeNode, E> Set<E> getIdSetByTreeNodeList(List<TreeNode<T>> treeNodeList, Class<E> clazz) {
        Set<E> idSet = new HashSet<>();
        if (treeNodeList == null || treeNodeList.size() < 1) {
            return idSet;
        }

        for (TreeNode<T> treeNode : treeNodeList) {
            idSet.add((E) treeNode.getId());
            if (treeNode.getChildren() != null && treeNode.getChildren().size() > 0) {
                idSet.addAll(getIdSetByTreeNodeList(treeNode.getChildren(), clazz));
            }
        }

        return idSet;
    }

    /**
     * 将Tree(单点)的所有点转换为ID的Set集合
     *
     * @param treeNode
     * @param clazz
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends TreeNode, E> Set<E> getIdSetByTreeNode(TreeNode<T> treeNode, Class<E> clazz) {
        Set<E> idSet = new HashSet<>();
        if (treeNode == null) {
            return idSet;
        }

        idSet.add((E) treeNode.getId());
        if (treeNode.getChildren() != null && treeNode.getChildren().size() > 0) {
            idSet.addAll(getIdSetByTreeNodeList(treeNode.getChildren(), clazz));
        }
        return idSet;
    }


    /**
     * tree ==> list
     *
     * @param treeList
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> List<T> treeToList(List<TreeNode<T>> treeList) {
        List<T> list = new ArrayList<>();
        if (treeList == null || treeList.size() < 1) {
            return list;
        }

        convertToList(treeList, list);
        return list;
    }

    /**
     * list ==> list
     *
     * @param childrenList
     * @param list
     * @param <T>
     */
    private static <T extends TreeNode> void convertToList(List<TreeNode<T>> childrenList, List<T> list) {
        if (childrenList == null || childrenList.size() < 1) {
            return;
        }
        for (int i = 0, size = childrenList.size(); i < size; i++) {
            TreeNode<T> childrenTreeNode = childrenList.get(i);
            T t = childrenTreeNode.getT();
            list.add(t);
            convertToList(childrenTreeNode.getChildren(), list);
        }
    }

}