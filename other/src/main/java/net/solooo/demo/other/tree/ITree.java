package net.solooo.demo.other.tree;

import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/24
 */
public interface ITree<T extends ITreeNode> {
    public List<T> getTree();
    public List<T> getRoot();
    public T getTreeNode(String nodeId);
}
