package com.atguigu.ssyx.acl.mapper;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 菜单Mpper接口
 */
@Repository
public interface PermissionMapper extends BaseMapper< Permission> {

}