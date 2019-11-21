package com.ha.generator;


import com.ha.generator.config.DataSourceConfig;
import com.ha.generator.config.GlobalConfig;
import com.ha.generator.config.PackageConfig;
import com.ha.generator.config.StrategyConfig;
import com.ha.generator.config.rules.DateType;
import com.ha.generator.service.AutoGeneratorService;

/**
 * <p>
 *  主函数
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21
 */
public class MybatisCodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGeneratorService generator = new AutoGeneratorService();
        // 策略配置
        StrategyConfig strategy = new StrategyConfig()
            .setInclude("tb_category");
            //.setSuperMapperClass("com.ha.generator.mybatis.MyMapper");
        generator.setStrategy(strategy);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
            .setFileOverride(true)
            .setAuthor("weirdo")
            .setSwagger2(true)
            .setDateType(DateType.ONLY_DATE)
            .setEntityName("%s")
            .setOutputDir(System.getProperty("user.dir") + "/ha-generator/src/main/java");
        generator.setGlobalConfig(globalConfig);
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent("com.ha.finacel");
        generator.setPackageConfig(packageConfig);
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            .setUrl("jdbc:mysql://127.0.0.1:3306/test")
            .setDriverName("com.mysql.cj.jdbc.Driver")
            .setUsername("root")
            .setPassword("root");
        generator.setDataSource(dataSourceConfig);
        generator.execute();
    }
}
