package ${package.Mapper};

import com.shop.card.demos.web.entity.${entity};
import ${superMapperClassPackage};
import ${package.Controller}.Res.${entity}.${entity}Res;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.List;
/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    @Select("select * from ${table.name} ${'$'}{ew.customSqlSegment}")
    List< ${entity}Res> list${entity} (@Param(Constants.WRAPPER) QueryWrapper<${entity}> queryWrapper);

    @Select("select * from ${table.name} where id = ${'#'}{id} ")
    ${entity}Res get${entity}ById(@Param("id") Integer id);

}

