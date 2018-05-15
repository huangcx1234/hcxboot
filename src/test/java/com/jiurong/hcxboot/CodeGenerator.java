package com.jiurong.hcxboot;

import cn.org.rapid_framework.generator.GeneratorFacade;

/**
 * @author hcx
 * @date 2018/5/15
 * explain:
 */
public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        String templatePath = "src\\main\\resources\\template";
        GeneratorFacade g = new GeneratorFacade();
        g.getGenerator().addTemplateRootDir(templatePath);
        //删除生成器的输出目录//
        g.deleteOutRootDir();
        //通过数据库表生成文件
        g.generateByTable("user");

//        自动搜索数据库中的所有表并生成文件,template为模板的根目录
//        g.generateByAllTable();
//        按table名字删除文件
//        g.deleteByTable(&quot;table_name&quot;, &quot;template&quot;);
    }
}
