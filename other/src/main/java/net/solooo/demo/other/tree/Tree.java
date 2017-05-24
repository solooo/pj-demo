package net.solooo.demo.other.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/24
 */
public class Tree<T extends ITreeNode> implements ITree<T> {
    private HashMap<String, T> treeNodesMap = new HashMap<>();
    private List<T> treeNodesList = new ArrayList<>();

    public Tree(List<T> list){
        initTreeNodeMap(list);
        initTreeNodeList();
    }

    private void initTreeNodeMap(List<T> list){
        T treeNode = null;
        for(T item : list){
            treeNode = item;
            treeNodesMap.put(treeNode.getNodeId(), treeNode);
        }

        Iterator<T> iter = treeNodesMap.values().iterator();
        T parentTreeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParentNodeId() == null || treeNode.getParentNodeId() == ""){
                continue;
            }

            parentTreeNode = treeNodesMap.get(treeNode.getParentNodeId());
            if(parentTreeNode != null){
                treeNode.setParent(parentTreeNode);
                parentTreeNode.addChild(treeNode);
            }
        }
    }

    private void initTreeNodeList(){
        if(treeNodesList.size() > 0){
            return;
        }
        if(treeNodesMap.size() == 0){
            return;
        }
        Iterator<T> iter = treeNodesMap.values().iterator();
        T treeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getParent() == null){
                this.treeNodesList.add(treeNode);
                this.treeNodesList.addAll(treeNode.getAllChildren());
            }
        }
    }

    @Override
    public List<T> getTree() {
        return this.treeNodesList;
    }

    @Override
    public List<T> getRoot() {
        List<T> rootList = new ArrayList<>();
        if (this.treeNodesList.size() > 0) {
            for (T node : treeNodesList) {
                if (node.getParent() == null)
                    rootList.add(node);
            }
        }
        return rootList;
    }

    @Override
    public T getTreeNode(String nodeId) {
        return this.treeNodesMap.get(nodeId);
    }
}
