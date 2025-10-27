package org.huangge.provider.mapper;

import com.shop.card.demos.web.entity.Order;
import com.shop.card.demos.web.config.common.EasyBaseMapper;
import org.huangge.provider.controller.Res.Order.OrderRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hfw
 * @since 2025-10-17
 */
@Mapper
public interface OrderMapper extends EasyBaseMapper<Order> {

    @Select("select * from order ${ew.customSqlSegment}")
    List< OrderRes> listOrder (@Param(Constants.WRAPPER) QueryWrapper<Order> queryWrapper);

    @Select("select * from order where id = #{id} ")
    OrderRes getOrderById(@Param("id") Integer id);

}

