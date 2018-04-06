package net.solooo.demo.other.tree;

import java.awt.*;

/**
 * Created by Eric on 2017/5/24.
 */
public class MenuTree extends TreeNode<MenuTree> {

    public MenuTree(String nodeId, String nodeName, String parentNodeId) {
        super(nodeId, nodeName, parentNodeId);
    }
}
