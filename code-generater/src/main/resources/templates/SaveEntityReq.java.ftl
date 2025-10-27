package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;
<#if entityLombokModel>
import lombok.*;
import lombok.EqualsAndHashCode;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>

/**
* <p>
    * ${table.comment!}
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
    <#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("${table.name}")
<#if swagger2>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
public class ${entity}  {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if swagger2>
        <#if field.propertyType == "String">
    @NotBlank(message = "${field.comment}不能为空")
    @Size(max = ${(field?length / 2)?floor}, message = "${field.comment}长度非法")
        <#else>
    @NotNull(message = "${field.comment}不能为空")
        </#if>
    @ApiModelProperty(value = "${field.comment}")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
