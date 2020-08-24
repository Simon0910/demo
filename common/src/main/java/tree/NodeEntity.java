package tree;

import lombok.Data;

/**
 * @author lzp on 2020/8/24.
 */
@Data
public class NodeEntity extends TreeNode<NodeEntity> {
    /**
     * 节点编码
     */
    private Integer nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 父节点编码
     */
    private Integer parentNodeId;
    /**
     * 父节点编码
     */
    private Integer nodeLevel;
    /**
     * 创建时间
     */
    private Long time;

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
        super.setId(String.valueOf(nodeId));
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
        super.setTitle(nodeName);
    }

    public void setParentNodeId(Integer parentNodeId) {
        this.parentNodeId = parentNodeId;
        super.setParentId(String.valueOf(parentNodeId));
    }

    public void setTime(Long time) {
        this.time = time;
        super.getAttributes().put("time", time);
    }

    public Integer getNodeLevel() {
        return super.getLevel();
    }
}
