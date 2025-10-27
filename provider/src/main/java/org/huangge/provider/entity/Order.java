package org.huangge.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.*;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hfw
 * @since 2025-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "雪花算法主键")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "订单编号")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "订单下单时间")
    @TableField("order_time")
    private String orderTime;

    @ApiModelProperty(value = "支付时间")
    @TableField("pay_time")
    private String payTime;

    @ApiModelProperty(value = "是否删除 1:是 2:否")
    @TableField("is_delete")
    @TableLogic(value = "2", delval = "1")
    private Integer isDelete;


}
