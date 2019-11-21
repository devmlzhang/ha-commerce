package com.ha.generator.engine;

import com.ha.generator.common.StringPool;
import com.ha.generator.config.ConstVal;
import com.ha.generator.config.builder.ConfigBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * <p>
 *    模板引擎实现文件输出
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine {

    private Configuration configuration;

    @Override
    public FreemarkerTemplateEngine init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        //configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration = new Configuration();
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
        return this;
    }


    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile){
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Template template = configuration.getTemplate(templatePath);
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        }catch (Exception e){
            throw new RuntimeException("文件写入失败");
        }
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }


    @Override
    public String templateFilePath(String filePath) {
        return filePath + ".ftl";
    }
}
