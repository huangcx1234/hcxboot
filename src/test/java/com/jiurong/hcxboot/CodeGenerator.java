package com.jiurong.hcxboot;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

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
        g.generateByTable("user");

//        自动搜索数据库中的所有表并生成文件,template为模板的根目录
//        g.generateByAllTable();
//        按table名字删除文件
//        g.deleteByTable(&quot;table_name&quot;, &quot;template&quot;);
    }

    private static String getCreateTime() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
    }
}
