package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminRoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * 用户角色服务实现类
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole>
        implements AdminRoleService {

    @Override
    public boolean saveBatch(Collection<AdminRole> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<AdminRole> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<AdminRole> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(AdminRole entity) {
        return false;
    }

    @Override
    public AdminRole getOne(Wrapper<AdminRole> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<AdminRole> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<AdminRole> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public AdminRoleMapper getBaseMapper() {
        return null;
    }

    @Override
    public Class<AdminRole> getEntityClass() {
        return null;
    }
}