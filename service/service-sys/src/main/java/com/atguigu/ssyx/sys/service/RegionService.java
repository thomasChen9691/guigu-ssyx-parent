package com.atguigu.ssyx.sys.service;

import com.atguigu.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2024-04-11
 */
public interface RegionService extends IService<Region> {
    //根据关键字获取地区列表
    List<Region> findRegionByKeyword(String keyword);
}
