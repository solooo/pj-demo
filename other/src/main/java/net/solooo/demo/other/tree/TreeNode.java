package net.solooo.demo.other.tree;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/24
 */
public abstract class TreeNode<T extends ITreeNode> implements ITreeNode<T> {
    //树节点ID
    @JSONField(ordinal = 1, serialize = false)
    private String nodeId;

    //树节点名称
    @JSONField(ordinal = 2)
    private String nodeName;

    //父节点ID
    @JSONField(ordinal = 3, serialize = false)
    private String parentNodeId;

    //节点所在的层级
    @JSONField(ordinal = 5, serialize = false)
    private int level;

    @JSONField(serialize = false)
    private T parent;

    //当前节点的二子节点
    @JSONField(ordinal = 6)
    private List<T> children = new ArrayList<>();

    //当前节点的子孙节点
    @JSONField(serialize = false)
    private List<T> allChildren = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(String nodeId, String nodeName, String parentNodeId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.parentNodeId = parentNodeId;
    }

    public void addChild(T treeNode) {
        this.children.add(treeNode);
    }

    public void removeChild(T treeNode) {
        this.children.remove(treeNode);
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public List<T> getAllChildren() {
        if (this.allChildren.isEmpty()) {
            for (T treeNode : this.children) {
                this.allChildren.add(treeNode);
                this.allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return this.allChildren;
    }
}
