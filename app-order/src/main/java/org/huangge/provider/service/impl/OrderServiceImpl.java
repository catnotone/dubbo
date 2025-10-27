package org.huangge.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.huangge.provider.controller.Req.Order.ListOrderReq;
import org.huangge.provider.controller.Req.Order.UpdateOrderReq;
import org.huangge.provider.controller.Req.Order.SaveOrderReq;
import org.huangge.provider.controller.Res.Order.OrderRes;
import com.shop.card.demos.web.entity.Order;
import org.huangge.provider.mapper.OrderMapper;
import org.huangge.provider.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import com.shop.card.demos.web.config.common.BaseRes;
import com.shop.card.demos.web.config.common.util.BeanUtils;
import com.shop.card.demos.web.config.common.util.QueryWrapperUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
* (Order) 服务实现类
* @author hfw
* @since 2025-10-17
*/
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    OrderMapper dataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes saveOrder (SaveOrderReq reqEntity) {
        Order data = BeanUtils.convertor(reqEntity,Order.class);
        save(data);
        return BaseRes.Success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes deleteOrderById (Integer id) {
        Optional<Order> data = dataMapper.getOneById(id);
        BaseRes.isNull(data, "参数错误");
        removeById(id);
        return BaseRes.Success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes updateOrder (UpdateOrderReq reqEntity) {
        Optional<Order> data = dataMapper.getOneById(reqEntity.getId());
        BaseRes.isNull(data, "参数错误");
        UpdateWrapper<Order> updateWrapper = QueryWrapperUtils.dealWithUpdate(reqEntity, new UpdateWrapper<>());
        update(updateWrapper);
        return BaseRes.Success();
    }

    @Override
    public BaseRes< List< OrderRes>> listOrder (ListOrderReq reqEntity) {
        QueryWrapper<Order> queryWrapper = QueryWrapperUtils.dealWithQuery(reqEntity, new QueryWrapper<>());
        queryWrapper.orderByDesc("id");
        PageHelper.startPage(reqEntity.getPageNum(), reqEntity.getPageSize());
        List< Order> list = dataMapper.selectList(queryWrapper);
        List< OrderRes> data = BeanUtils.pojoDtoCollectionConvertor(list,OrderRes.class);
        return BaseRes.SuccessLimit(new PageInfo<>(list), data);
    }

    @Override
    public BaseRes< OrderRes> getOrderById (Integer id) {
        Optional<Order> data = dataMapper.getOneById(id);
        BaseRes.isNull(data, "参数错误");
        OrderRes res = BeanUtils.convertor(data.get(), OrderRes.class);
        return BaseRes.Success(res);
    }

}
