package org.huangge.provider.service;

import org.huangge.provider.controller.Req.Order.ListOrderReq;
import org.huangge.provider.controller.Req.Order.UpdateOrderReq;
import org.huangge.provider.controller.Req.Order.SaveOrderReq;
import org.huangge.provider.controller.Res.Order.OrderRes;
import com.shop.card.demos.web.entity.Order;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.card.demos.web.config.common.BaseRes;
import java.util.List;

/**
* (Order) 服务接口
* @author hfw
* @since 2025-10-17
*/
public interface IOrderService extends IService<Order>  {
    /** 保存 */
    BaseRes saveOrder (SaveOrderReq reqEntity);

    /** 根据id删除 */
    BaseRes deleteOrderById (Integer id);

    /** 修改 */
    BaseRes updateOrder (UpdateOrderReq reqEntity);

    /** 查询列表 */
    BaseRes< List< OrderRes>> listOrder (ListOrderReq reqEntity);

    /** 查询详情 */
    BaseRes< OrderRes> getOrderById (Integer id);

}