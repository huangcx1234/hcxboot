package com.jiurong.hcxboot;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

/**
 * @author hcx
 * @date 2018/5/15
 * explain:
 */
public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        //数据库连接相关配置
        GeneratorProperties.setProperty("jdbc_username", "root");
        GeneratorProperties.setProperty("jdbc_password", "soyeajr");
        GeneratorProperties.setProperty("jdbc_url", "jdbc:mysql://localhost:3306/hcxboot?useUnicode=true&amp;characterEncoding=UTF-8");
        GeneratorProperties.setProperty("jdbc_driver", "com.mysql.jdbc.Driver");
        //数据库类型相关配置
        GeneratorProperties.setProperty("java_typemapping.java.sql.Timestamp", "java.util.Date");
        GeneratorProperties.setProperty("java_typemapping.java.sql.Date", "java.util.Date");
        GeneratorProperties.setProperty("java_typemapping.java.sql.Time", "java.util.Date");
        GeneratorProperties.setProperty("java_typemapping.java.lang.Byte", "java.lang.Integer");
        //模板目录相关配置
        String templatePath = CodeGenerator.class.getClassLoader().getResource("template").getFile();
        g.getGenerator().addTemplateRootDir(templatePath);
        //包名相关配置
        GeneratorProperties.setProperty("basePackage", "com.jiurong.hcxboot");
        //作者和时间配置
        GeneratorProperties.setProperty("author", "soyeajr");
        GeneratorProperties.setProperty("createTime", getCreateTime());
        //代码输出目录相关配置
        String outRoot = ".\\generator-output";
        g.getGenerator().setOutRootDir(outRoot);
        //删除生成器的输出目录//
        g.deleteOutRootDir();
        //通过数据库表生成文件
        GeneratorProperties.setProperty("tableComment", "用户");
        String tableName = "user";
        g.generateByTable(tableName);
        //复制文件内容到指定目录
        String modelName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        Files.copy(Paths.get(outRoot + "\\" + modelName + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\model\\" + modelName + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + modelName + "Mapper.xml"), Paths.get(".\\src\\main\\resources\\mapper\\" + modelName + "Mapper.xml"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + modelName + "Mapper.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\mapper\\" + modelName + "Mapper.java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + modelName + "Service.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\service\\" + modelName + "Service.java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + modelName + "Controller.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\controller\\" + modelName + "Controller.java"), StandardCopyOption.REPLACE_EXISTING);
        new File(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + tableName).mkdirs();
        Files.copy(Paths.get(outRoot + "\\Page" + modelName + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + tableName + "\\Page" + modelName + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\Update" + modelName + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + tableName + "\\Update" + modelName + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\Save" + modelName + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + tableName + "\\Save" + modelName + ".java"), StandardCopyOption.REPLACE_EXISTING);
    }

    private static String getCreateTime() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
    }
}
