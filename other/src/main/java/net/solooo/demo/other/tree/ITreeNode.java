package net.solooo.demo.other.tree;

import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/24
 */
public interface ITreeNode<T> {

    public String getNodeId();

    public String getParentNodeId();

    public void setParent(T parentTreeNode);

    public void addChild(T treeNode);

    public Object getParent();

    public List<? extends ITreeNode> getAllChildren();
}
