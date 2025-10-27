package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import ${package.Controller}.Req.${entity}.List${entity}Req;
import ${package.Controller}.Req.${entity}.Update${entity}Req;
import ${package.Controller}.Req.${entity}.Save${entity}Req;
import ${package.Controller}.Res.${entity}.${entity}Res;
import com.shop.card.demos.web.entity.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
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
* ${table.comment!}(${entity}) 服务实现类
* @author ${author}
* @since ${date}
*/
@Service
@Slf4j
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    ${table.mapperName} dataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes save${entity} (Save${entity}Req reqEntity) {
        ${entity} data = BeanUtils.convertor(reqEntity,${entity}.class);
        save(data);
        return BaseRes.Success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes delete${entity}ById (Integer id) {
        Optional<${entity}> data = dataMapper.getOneById(id);
        BaseRes.isNull(data, "参数错误");
        removeById(id);
        return BaseRes.Success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseRes update${entity} (Update${entity}Req reqEntity) {
        Optional<${entity}> data = dataMapper.getOneById(reqEntity.getId());
        BaseRes.isNull(data, "参数错误");
        UpdateWrapper<${entity}> updateWrapper = QueryWrapperUtils.dealWithUpdate(reqEntity, new UpdateWrapper<>());
        update(updateWrapper);
        return BaseRes.Success();
    }

    @Override
    public BaseRes< List< ${entity}Res>> list${entity} (List${entity}Req reqEntity) {
        QueryWrapper<${entity}> queryWrapper = QueryWrapperUtils.dealWithQuery(reqEntity, new QueryWrapper<>());
        queryWrapper.orderByDesc("id");
        PageHelper.startPage(reqEntity.getPageNum(), reqEntity.getPageSize());
        List< ${entity}> list = dataMapper.selectList(queryWrapper);
        List< ${entity}Res> data = BeanUtils.pojoDtoCollectionConvertor(list,${entity}Res.class);
        return BaseRes.SuccessLimit(new PageInfo<>(list), data);
    }

    @Override
    public BaseRes< ${entity}Res> get${entity}ById (Integer id) {
        Optional<${entity}> data = dataMapper.getOneById(id);
        BaseRes.isNull(data, "参数错误");
        ${entity}Res res = BeanUtils.convertor(data.get(), ${entity}Res.class);
        return BaseRes.Success(res);
    }

}
