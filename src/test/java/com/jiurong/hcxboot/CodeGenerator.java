package com.jiurong.hcxboot;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

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
        String className = StringUtils.replace(WordUtils.capitalizeFully(tableName,new char[]{'_'}), "_", "", -1);
        String classNameLower = StringUtils.uncapitalize(className);
        Files.copy(Paths.get(outRoot + "\\" + className + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\model\\" + className + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + className + "Mapper.xml"), Paths.get(".\\src\\main\\resources\\mapper\\" + className + "Mapper.xml"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + className + "Mapper.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\mapper\\" + className + "Mapper.java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + className + "Service.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\service\\" + className + "Service.java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\" + className + "Controller.java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\controller\\" + className + "Controller.java"), StandardCopyOption.REPLACE_EXISTING);
        new File(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + classNameLower).mkdirs();
        Files.copy(Paths.get(outRoot + "\\Page" + className + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + classNameLower + "\\Page" + className + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\Update" + className + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + classNameLower + "\\Update" + className + ".java"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(outRoot + "\\Save" + className + ".java"), Paths.get(".\\src\\main\\java\\com\\jiurong\\hcxboot\\request\\" + classNameLower + "\\Save" + className + ".java"), StandardCopyOption.REPLACE_EXISTING);
    }

    private static String getCreateTime() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
    }
}
