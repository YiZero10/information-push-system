package com.njupt.system.util;

import org.apache.commons.io.FileUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Elaine Huang
 * @date 2021/12/20
 */
public class MybatisGeneratorApp {
    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mybatisGeneratorConfig.xml");

        File file = new File("code");
        FileUtils.deleteDirectory(file);

        file.mkdir();
        Properties properties = getProperties();

        ConfigurationParser cp = new ConfigurationParser(properties, warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        if (!warnings.isEmpty()) {
            for (String warn : warnings) {
                System.out.println(warn);
            }
        }
        System.out.println("生成成功！");
    }

    /**
     * 自定义配置
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        properties
                .setProperty("url", "jdbc:mysql://127.0.0.1:3306/information_push_system?useSSL=false&characterEncoding=utf8&serverTimezone=GMT");
        properties.setProperty("userId", "root");
        properties.setProperty("password", "password");
        // 包名
        properties.setProperty("package", "com.njupt.system");
        // 要生成的表名
        properties.setProperty("tableName", "old_man");

        return properties;
    }
}
