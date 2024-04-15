package com.atguigu.ssyx.product.service.impl;

import com.atguigu.ssyx.common.exception.SsyxException;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.atguigu.ssyx.model.product.SkuImage;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.atguigu.ssyx.product.mapper.SkuInfoMapper;
import com.atguigu.ssyx.product.service.SkuAttrValueService;
import com.atguigu.ssyx.product.service.SkuImageService;
import com.atguigu.ssyx.product.service.SkuInfoService;
import com.atguigu.ssyx.product.service.SkuPosterService;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.atguigu.ssyx.vo.product.SkuStockLockVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-04-04
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    //sku图片
    @Autowired
    private SkuImageService skuImageService;

    //sku平台属性
    @Autowired
    private SkuAttrValueService skuAttrValueService;

    //sku海报
    @Autowired
    private SkuPosterService skuPosterService;


    //添加商品sku信息
    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
        //1 添加sku基本信息
        // SkuInfoVo 复制-- SkuInfo
        SkuInfo skuInfo = new SkuInfo();
//        skuInfo.setSkuName(skuInfoVo.getSkuName());
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
        baseMapper.insert(skuInfo);

        //2 保存sku海报
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if(!CollectionUtils.isEmpty(skuPosterList)) {
            //遍历，向每个海报对象添加商品skuid
            for (SkuPoster skuPoster:skuPosterList) {
                skuPoster.setSkuId(skuInfo.getId());
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        //3 保存sku图片
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if(!CollectionUtils.isEmpty(skuImagesList)) {
            for (SkuImage skuImage:skuImagesList) {
                //设置商品skuid
                skuImage.setSkuId(skuInfo.getId());
            }
            skuImageService.saveBatch(skuImagesList);
        }

        //4 保存sku平台属性
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if(!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (SkuAttrValue skuAttrValue:skuAttrValueList) {
                //设置商品skuid
                skuAttrValue.setSkuId(skuInfo.getId());
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    //获取sku信息
    @Override
    public SkuInfoVo getSkuInfo(Long id) {
        SkuInfoVo skuInfoVo = new SkuInfoVo();

        //根据id查询sku基本信息
        SkuInfo skuInfo = baseMapper.selectById(id);

        //根据id查询商品图片列表
        List<SkuImage> skuImageList = skuImageService.getImageListBySkuId(id);

        //根据id查询商品海报列表
        List<SkuPoster> skuPosterList = skuPosterService.getPosterListBySkuId(id);

        //根据id查询商品属性信息列表
        List<SkuAttrValue> skuAttrValueList = skuAttrValueService.getAttrValueListBySkuId(id);

        //封装所有数据，返回
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        skuInfoVo.setSkuImagesList(skuImageList);
        skuInfoVo.setSkuPosterList(skuPosterList);
        skuInfoVo.setSkuAttrValueList(skuAttrValueList);
        return skuInfoVo;
    }

    //修改sku
    @Override
    public void updateSkuInfo(SkuInfoVo skuInfoVo) {
        //修改sku基本信息
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
        baseMapper.updateById(skuInfo);

        Long skuId = skuInfoVo.getId();
        //海报信息
        LambdaQueryWrapper<SkuPoster> wrapperSkuPoster = new LambdaQueryWrapper<>();
        wrapperSkuPoster.eq(SkuPoster::getSkuId,skuId);
        skuPosterService.remove(wrapperSkuPoster);

        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if(!CollectionUtils.isEmpty(skuPosterList)) {
            //遍历，向每个海报对象添加商品skuid
            for (SkuPoster skuPoster:skuPosterList) {
                skuPoster.setSkuId(skuId);
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        //商品图片
        skuImageService.remove(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId,skuId));
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if(!CollectionUtils.isEmpty(skuImagesList)) {
            for (SkuImage skuImage:skuImagesList) {
                //设置商品skuid
                skuImage.setSkuId(skuId);
            }
            skuImageService.saveBatch(skuImagesList);
        }

        //商品属性
        skuAttrValueService.remove(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId,skuId));
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if(!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (SkuAttrValue skuAttrValue:skuAttrValueList) {
                //设置商品skuid
                skuAttrValue.setSkuId(skuId);
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    //商品审核
    @Override
    public void check(Long skuId, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }

    //商品上下架
    @Override
    public void publish(Long skuId, Integer status) {
        if(status == 1) { //上架
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            //整合mq把数据同步到es里面

        } else { //下架
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            //整合mq把数据同步到es里面

        }
    }

    //新人专享
    @Override
    public void isNewPerson(Long skuId, Integer status) {
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsNewPerson(status);
        baseMapper.updateById(skuInfoUp);
    }

    //根据skuId列表得到sku信息列表
    @Override
    public List<SkuInfo> findSkuInfoList(List<Long> skuIdList) {
        List<SkuInfo> skuInfoList = baseMapper.selectBatchIds(skuIdList);
        return skuInfoList;
    }

    //根据关键字匹配sku列表
    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        List<SkuInfo> skuInfoList = baseMapper.selectList(
                new LambdaQueryWrapper<SkuInfo>().like(SkuInfo::getSkuName, keyword)
        );
        return skuInfoList;
    }

    //获取新人专享商品
    @Override
    public List<SkuInfo> findNewPersonSkuInfoList() {
        //条件1 ： is_new_person=1
        //条件2 ： publish_status=1
        //条件3 ：显示其中三个
        //获取第一页数据，每页显示三条记录
        Page<SkuInfo> pageParam = new Page<>(1,3);
        //封装条件
        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuInfo::getIsNewPerson,1);
        wrapper.eq(SkuInfo::getPublishStatus,1);
        wrapper.orderByDesc(SkuInfo::getStock);//库存排序
        //调用方法查询
        IPage<SkuInfo> skuInfoPage = baseMapper.selectPage(pageParam, wrapper);
        List<SkuInfo> skuInfoList = skuInfoPage.getRecords();
        return skuInfoList;
    }

    //根据skuId获取sku信息
    @Override
    public SkuInfoVo getSkuInfoVo(Long skuId) {

        SkuInfoVo skuInfoVo = new SkuInfoVo();

        //skuId查询skuInfo
        SkuInfo skuInfo = baseMapper.selectById(skuId);

        //skuId查询sku图片
        List<SkuImage> imageList = skuImageService.getImageListBySkuId(skuId);

        //skuId查询sku海报
        List<SkuPoster> posterList = skuPosterService.getPosterListBySkuId(skuId);

        //skuId查询sku属性
        List<SkuAttrValue> attrValueList = skuAttrValueService.getAttrValueListBySkuId(skuId);

        //封装到skuInfoVo对象
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        skuInfoVo.setSkuImagesList(imageList);
        skuInfoVo.setSkuPosterList(posterList);
        skuInfoVo.setSkuAttrValueList(attrValueList);
        return skuInfoVo;
    }

    @Override
    public Boolean checkAndLock(List<SkuStockLockVo> skuStockLockVoList, String orderNo) {
        return null;
    }

    @Override
    public void minusStock(String orderNo) {

    }


    //sku列表
    @Override
    public IPage<SkuInfo> selectPageSkuInfo(Page<SkuInfo> pageParam,
                                            SkuInfoQueryVo skuInfoQueryVo) {
        String keyword = skuInfoQueryVo.getKeyword();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        String skuType = skuInfoQueryVo.getSkuType();

        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            wrapper.like(SkuInfo::getSkuName,keyword);
        }
        if(!StringUtils.isEmpty(categoryId)) {
            wrapper.eq(SkuInfo::getCategoryId,categoryId);
        }
        if(!StringUtils.isEmpty(skuType)) {
            wrapper.like(SkuInfo::getSkuType,skuType);
        }
        IPage<SkuInfo> pageModel = baseMapper.selectPage(pageParam,wrapper);
        return pageModel;
    }

}
