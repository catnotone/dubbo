package ${package.Service};

import ${package.Controller}.Req.${entity}.List${entity}Req;
import ${package.Controller}.Req.${entity}.Update${entity}Req;
import ${package.Controller}.Req.${entity}.Save${entity}Req;
import ${package.Controller}.Res.${entity}.${entity}Res;
import com.shop.card.demos.web.entity.${entity};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.card.demos.web.config.common.BaseRes;
import java.util.List;

/**
* ${table.comment!}(${entity}) 服务接口
* @author ${author}
* @since ${date}
*/
public interface I${entity}Service extends IService<${entity}>  {
    /** 保存${table.comment!} */
    BaseRes save${entity} (Save${entity}Req reqEntity);

    /** 根据id删除${table.comment!} */
    BaseRes delete${entity}ById (Integer id);

    /** 修改${table.comment!} */
    BaseRes update${entity} (Update${entity}Req reqEntity);

    /** 查询${table.comment!}列表 */
    BaseRes< List< ${entity}Res>> list${entity} (List${entity}Req reqEntity);

    /** 查询${table.comment!}详情 */
    BaseRes< ${entity}Res> get${entity}ById (Integer id);

}