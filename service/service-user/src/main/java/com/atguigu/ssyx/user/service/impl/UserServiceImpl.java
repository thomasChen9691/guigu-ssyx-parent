package com.atguigu.ssyx.user.service.impl;


import com.atguigu.ssyx.enums.UserType;
import com.atguigu.ssyx.user.mapper.LeaderMapper;
import com.atguigu.ssyx.user.mapper.UserDeliveryMapper;
import com.atguigu.ssyx.user.mapper.UserMapper;
import com.atguigu.ssyx.model.user.Leader;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.model.user.UserDelivery;
import com.atguigu.ssyx.user.service.UserService;
import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import com.atguigu.ssyx.vo.user.UserLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDeliveryMapper userDeliveryMapper;

    @Resource
    private LeaderMapper leaderMapper;

    @Resource
    private RegionFeignClient regionFeignClient;

    @Override
    public LeaderAddressVo getLeaderAddressVoByUserId(Long userId) {
        //根据用户id查询用户默认收货地址
        LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDelivery::getUserId, userId);
        queryWrapper.eq(UserDelivery::getIsDefault, 1);
        UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
        if(null == userDelivery) return null;

        Leader leader = leaderMapper.selectById(userDelivery.getLeaderId());
        //封装到vo
        LeaderAddressVo leaderAddressVo = new LeaderAddressVo();
        BeanUtils.copyProperties(leader, leaderAddressVo);
        leaderAddressVo.setUserId(userId);
        leaderAddressVo.setLeaderId(leader.getId());
        leaderAddressVo.setLeaderName(leader.getName());
        leaderAddressVo.setLeaderPhone(leader.getPhone());
        leaderAddressVo.setWareId(userDelivery.getWareId());
        leaderAddressVo.setStorePath(leader.getStorePath());
        return leaderAddressVo;
    }

    @Override
    public User getByOpenid(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public UserLoginVo getUserLoginVo(Long userId) {
        UserLoginVo userLoginVo = new UserLoginVo();
        User user = this.getById(userId);
        userLoginVo.setNickName(user.getNickName());
        userLoginVo.setUserId(userId);
        userLoginVo.setPhotoUrl(user.getPhotoUrl());
        userLoginVo.setOpenId(user.getOpenId());
        userLoginVo.setIsNew(user.getIsNew());

/*        //如果是团长获取当前前团长id与对应的仓库id
        if(user.getUserType() == UserType.LEADER) {
            LambdaQueryWrapper<Leader> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Leader::getUserId, userId);
            queryWrapper.eq(Leader::getCheckStatus, 1);
            Leader leader = leaderMapper.selectOne(queryWrapper);
            if(null != leader) {
                userLoginVo.setLeaderId(leader.getId());
                Long wareId = regionFeignClient.getWareId(leader.getRegionId());
                userLoginVo.setWareId(wareId);
            }
        } else {
            //如果是会员获取当前会员对应的仓库id
            LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserDelivery::getUserId, userId);
            queryWrapper.eq(UserDelivery::getIsDefault, 1);
            UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
            if(null != userDelivery) {
                userLoginVo.setLeaderId(userDelivery.getLeaderId());
                userLoginVo.setWareId(userDelivery.getWareId());
            } else {
                userLoginVo.setLeaderId(1L);
                userLoginVo.setWareId(1L);
            }
        }
        return userLoginVo;*/
        UserDelivery userDelivery = userDeliveryMapper.selectOne(
                new LambdaQueryWrapper<UserDelivery>().eq(UserDelivery::getUserId, userId)
                        .eq(UserDelivery::getIsDefault, 1)
        );
        if(userDelivery != null) {
            userLoginVo.setLeaderId(userDelivery.getLeaderId());
            userLoginVo.setWareId(userDelivery.getWareId());
        } else {
            userLoginVo.setLeaderId(1L);
            userLoginVo.setWareId(1L);
        }
        return userLoginVo;
    }
}
