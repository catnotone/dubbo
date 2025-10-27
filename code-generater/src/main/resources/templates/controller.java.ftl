package ${package.Controller};

import com.shop.card.demos.web.config.common.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import ${package.Service}.${table.serviceName};
import com.shop.card.demos.web.entity.${entity};
import ${package.Controller}.Req.${entity}.List${entity}Req;
import ${package.Controller}.Req.${entity}.Update${entity}Req;
import ${package.Controller}.Req.${entity}.Save${entity}Req;
import ${package.Controller}.Res.${entity}.${entity}Res;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ${table.comment!}(${entity}) 控制器
 * @author ${author}
 * @since ${date}
 */
@RestController
@Slf4j
@Api(tags = "${table.comment!}")
@RequestMapping("/${entity}")
public class ${table.controllerName} {
   
    @Resource
    public ${table.serviceName} i${entity}Service;

    @ApiOperation("保存${table.comment!}")
    @PostMapping("/save${entity}")
    public BaseRes save${entity}(@RequestBody @Valid Save${entity}Req reqEntity) {
        return i${entity}Service.save${entity}(reqEntity);
    }

    @ApiOperation("根据id删除${table.comment!}")
    @DeleteMapping("/delete${entity}ById/{id}")
    public BaseRes delete${entity}ById(@PathVariable Integer id) {
        return i${entity}Service.delete${entity}ById(id);
    }

    @ApiOperation("修改${table.comment!}")
    @PutMapping("/update${entity}")
    public BaseRes update${entity}(@RequestBody @Valid Update${entity}Req reqEntity) {
        return i${entity}Service.update${entity}(reqEntity);
    }

    @ApiOperation("查询${table.comment!}列表")
    @GetMapping("/list${entity}")
    public BaseRes< List< ${entity}Res>> list${entity}(@Valid List${entity}Req reqEntity) {
        return i${entity}Service.list${entity}(reqEntity);
    }

    @ApiOperation("查询${table.comment!}详情")
    @GetMapping("/get${entity}ById/{id}")
    public BaseRes< ${entity}Res> get${entity}ById(@PathVariable Integer id) {
        return i${entity}Service.get${entity}ById(id);
    }

}