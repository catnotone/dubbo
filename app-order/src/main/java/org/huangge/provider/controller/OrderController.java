package org.huangge.provider.controller;

import com.shop.card.demos.web.config.common.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.huangge.provider.service.IOrderService;
import com.shop.card.demos.web.entity.Order;
import org.huangge.provider.controller.Req.Order.ListOrderReq;
import org.huangge.provider.controller.Req.Order.UpdateOrderReq;
import org.huangge.provider.controller.Req.Order.SaveOrderReq;
import org.huangge.provider.controller.Res.Order.OrderRes;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * (Order) 控制器
 * @author hfw
 * @since 2025-10-17
 */
@RestController
@Slf4j
@Api(tags = "")
@RequestMapping("/Order")
public class OrderController {
   
    @Resource
    public IOrderService iOrderService;

    @ApiOperation("保存")
    @PostMapping("/saveOrder")
    public BaseRes saveOrder(@RequestBody @Valid SaveOrderReq reqEntity) {
        return iOrderService.saveOrder(reqEntity);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/deleteOrderById/{id}")
    public BaseRes deleteOrderById(@PathVariable Integer id) {
        return iOrderService.deleteOrderById(id);
    }

    @ApiOperation("修改")
    @PutMapping("/updateOrder")
    public BaseRes updateOrder(@RequestBody @Valid UpdateOrderReq reqEntity) {
        return iOrderService.updateOrder(reqEntity);
    }

    @ApiOperation("查询列表")
    @GetMapping("/listOrder")
    public BaseRes< List< OrderRes>> listOrder(@Valid ListOrderReq reqEntity) {
        return iOrderService.listOrder(reqEntity);
    }

    @ApiOperation("查询详情")
    @GetMapping("/getOrderById/{id}")
    public BaseRes< OrderRes> getOrderById(@PathVariable Integer id) {
        return iOrderService.getOrderById(id);
    }

}