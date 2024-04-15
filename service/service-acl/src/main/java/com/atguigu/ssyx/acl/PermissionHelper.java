package com.atguigu.ssyx.acl;

import com.atguigu.ssyx.model.acl.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
        //构建树形结构,创建最终数据封装List集合
        List<Permission> trees = new ArrayList<>();
        //遍历所有菜单list集合，得到第一次数据，pid=0
        for (Permission treeNode : treeNodes) {
            //pid=0数据，第一层数据
            if (treeNode.getPid() == 0) {
                treeNode.setLevel(1);
                //调用方法，递归查找子节点
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());
        //遍历treeNodes所有节点，比较当前节点id和pid是否一样,封装，递归往下找
        for (Permission it : treeNodes) {
            //当前节点id = pid是否一样
            if(treeNode.getId().longValue() == it.getPid().longValue()) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                //封装下一层数据
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}