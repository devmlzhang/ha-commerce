package com.ha.generator.config;

import com.ha.generator.common.PackageHelper;
import com.ha.generator.config.builder.ConfigBuilder;

import java.io.File;

/**
 * <p>
 *  文件覆盖接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21
 */
public interface IFileCreate {

    /**
     * 自定义判断是否创建文件
     *
     * @param configBuilder 配置构建器
     * @param fileType      文件类型
     * @param filePath      文件路径
     * @return ignore
     */
    boolean isCreate(ConfigBuilder configBuilder, String filePath);

    /**
     * 检查文件目录，不存在自动递归创建
     *
     * @param filePath 文件路径
     */
    default void checkDir(String filePath) {
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            PackageHelper.mkDir(file.getParentFile());
        }
    }
}
