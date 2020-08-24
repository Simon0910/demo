package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzp on 2020/8/24.
 */
public class TreeNode<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 点名称
     */
    private String title;
    /**
     * 父点ID
     */
    private String parentId;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 该点是否展开，默认不展开
     */
    private Boolean spread = false;
    /**
     * 该点是否选中，默认不选中
     */
    private Boolean checked = false;
    /**
     * 该点的图标，默认不设置
     */
    private String icon;
    /**
     * 该点的其他属性
     */
    private Map<String, Object> attributes = new HashMap<>();
    /**
     * 该点的子树集合
     */
    private List<TreeNode<T>> children = new ArrayList<>();
    /**
     * 当前实体
     */
    private T t;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}