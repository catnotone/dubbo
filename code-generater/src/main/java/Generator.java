import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.text.CaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generator {
    // 项目路径
    private static final String ProjectPath =  System.getProperty("user.dir");
    // 作者
    private static final String Author = "hfw";

    private static final String ProjectURL = ProjectPath +  "\\app-order\\src\\main\\java\\";

    private static final String FeignURL = ProjectPath + "\\projectName\\src\\main\\java\\";


    // 数据库配置
    private static final String DriverName = "com.mysql.cj.jdbc.Driver";
    private static final String Url = "jdbc:mysql://rm-bp1k1lz08dl4ejus8ao.mysql.rds.aliyuncs.com/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    private static final String Username = "LkyTest";
    private static final String Password = "LKY_Admin";

    // 包路径 模块名 表名
    private static final String Parent =  "org.huangge.provider";
    private static final String projectName = "provider";
    private static final String ModuleName = "";
    private static final String[] TableNames = {
            "order"
    };
    // 是否只生成实体
    private static final boolean OnlyCreateEntity = false;
    // 是否覆盖文件
    private static final boolean FileOverride = true;
    //是否生成req res
    private static final boolean CreateReqAndRes = true;
    //是否把实体生成到feign-center
    private static final boolean isHaveFeignApi = true;
    private static String projectFeignName = projectName; //+ "-api";

    // 表填充字段
    private static List<TableFill> tableFillList = new ArrayList<TableFill>() {{
        add(new TableFill("create_time", FieldFill.INSERT));
        add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
    }};

    public static void main(String[] args) {
        // 控制台执行
        Scanner scan = new Scanner(System.in);
        System.out.println("是否继续？");
        Integer str = scan.nextInt();
        if (str.equals(1)) {
            if (CreateReqAndRes) {
                codeGenerator(TableNames, "controller.Req", "List%sReq", true);
                codeGenerator(TableNames, "controller.Req", "Save%sReq", true);
                codeGenerator(TableNames, "controller.Req", "Update%sReq", true);
                codeGenerator(TableNames, "controller.Res", "%sRes", true);
            }
            codeGenerator(TableNames, "entity", "%s", OnlyCreateEntity);
            codeGenerator(TableNames, "service", "%s", OnlyCreateEntity);
        }
        scan.close();
    }

    public static void codeGenerator(String[] TableNames, String entity, String entityName, boolean OnlyCreateEntity) {
        for (String tableName : TableNames) {

            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 配置自定义输出模板
            if (!OnlyCreateEntity && "service".equals(entity)) {
                mpg.setTemplate(new TemplateConfig()
                                .setEntity(null)
//                        .setEntity(entityName.contains("Update") ? "templates/UpdateEntityReq.java"
//                                : (entityName.contains("List") && entityName.contains("Req")) ? "templates/ListEntityReq.java"
//                                : (entityName.contains("Save") && entityName.contains("Req")) ? "templates/SaveEntityReq.java"
//                                : (entityName.contains("Res")) ? "templates/EntityRes.java"
//                                : "templates/entity.java")//entity模板
                                .setMapper("templates/mapper.java")//mapper模板
                                .setServiceImpl("templates/serviceImpl.java")
                                .setService("templates/service.java")
                                .setXml(null)
                                .setController("templates/controller.java")
                );
            } else {
                mpg.setTemplate(new TemplateConfig()
                        .setEntity(entityName.contains("Update") ? "templates/UpdateEntityReq.java"
                                : (entityName.contains("List") && entityName.contains("Req")) ? "templates/ListEntityReq.java"
                                : (entityName.contains("Save") && entityName.contains("Req")) ? "templates/SaveEntityReq.java"
                                : (entityName.contains("Res")) ? "templates/EntityRes.java"
                                : "templates/entity.java")//entity模板
                        .setXml(null)
                        .setMapper(null)
                        .setService(null)
                        .setServiceImpl(null)
                        .setController(null)
                );
            }

            // 全局配置
            String outPutDir = isHaveFeignApi && (entity.contains("controller") || "entity".equals(entity)) ?
                    FeignURL.replace("projectName", projectFeignName) :
                    ProjectURL.replace("projectName", projectName);

            mpg.setGlobalConfig(new GlobalConfig()
                    .setOutputDir(outPutDir)
                    .setAuthor(Author)//作者
                    .setDateType(DateType.ONLY_DATE)
                    .setOpen(false)
                    .setFileOverride(FileOverride) //是否覆盖文件
                    .setSwagger2(true)//是否使用swagger2
                    .setEntityName(entityName)//实体类后缀

            );

            // 数据源配置
            mpg.setDataSource(new DataSourceConfig()
                    .setDriverName(DriverName)//驱动
                    .setUrl(Url)//url
                    .setUsername(Username)//用户名
                    .setPassword(Password)//密码
            );

            // 包配置
            mpg.setPackageInfo(new PackageConfig()
                    .setModuleName(ModuleName)//模块名
                    .setParent(Parent)//包路径
                    .setEntity(entity.equals("entity") ? entity : entity + "." + CaseUtils.toCamelCase(tableName, true, '_'))
            );


            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
            strategy.setTablePrefix("");// 此处可以修改为您的表前缀
            strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);//字段名下划线转驼峰
            strategy.setInclude(tableName); // 需要生成的表
            strategy.setEntityLombokModel(true);//lombok依赖
            strategy.setRestControllerStyle(true);
            strategy.setLogicDeleteFieldName("is_delete");
            // 生成类的时候排除的字符串
            if (entityName.contains("Save") || entityName.contains("List")) {
                strategy.setSuperEntityColumns(
                        "create_time", "update_time", "id", "is_delete"
                );
            } else if (entityName.contains("Update")) {
                strategy.setSuperEntityColumns(
                        "create_time", "update_time", "is_delete"
                );
            } else {
                strategy.setSuperEntityColumns(
                        "create_time", "update_time"
                );
            }
//        strategy.setTableFillList(tableFillList);//表填充字段
            strategy.setEntityTableFieldAnnotationEnable(true);//@TableField注解
            // strategy.setExclude(new String[]{"AY230050Z"}); // 排除生成的表
            // 自定义实体父类
            strategy.setEntitySerialVersionUID(true);
            // 自定义实体，公共字段
            // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
            // 自定义 mapper 父类
            strategy.setSuperMapperClass("com.shop.card.demos.web.config.common.EasyBaseMapper");
            // 自定义 service 父类
            // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
            // 自定义 service 实现类父类
            // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
            // 自定义 controller 父类
            // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
            // 【实体】是否生成字段常量（默认 false）
            // public static final String ID = "test_id";
            // strategy.setEntityColumnConstant(true);
            // 【实体】是否为构建者模型（默认 false）
            // public User setName(String name) {this.name = name; return this;}
            // strategy.setEntityBuilderModel(true);
            mpg.setStrategy(strategy);
            // 自定义文件输出位置（非必须）
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        String templatePath = "/templates/mapper.xml.ftl";
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return FileURL + "/src/main/java/controller/req"
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        if (!OnlyCreateEntity) {
//            mpg.setCfg(cfg);
//        }

            // 选择模板
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());

// 控制台继续
            mpg.execute();

        }
    }


}
