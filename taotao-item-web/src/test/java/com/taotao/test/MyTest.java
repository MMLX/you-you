package com.taotao.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

public class MyTest {
    @Test
    public void demo1() throws Exception {
        //自动去找jar包的版本包
        Configuration configuration = new Configuration(Configuration.getVersion());
        //目前来说我们先设置本地路径
        configuration.setDirectoryForTemplateLoading(
                new File("E:\\taotao\\taotao-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("utf-8");
        //模板对象
        Template template = configuration.getTemplate("demo1.ftl");
        Map map = new HashMap();
        map.put("hello","欢迎使用freemarker模板");
        //告诉我们静态页面，输出到哪里
        Writer writer = new FileWriter(new File("E:\\abcd.html"));
        //第一个是数据，第二个是地址
        template.process(map,writer);
        writer.close();

    }
    @Test
    public void demo2() throws Exception {
        //自动去找jar包的版本包
        Configuration configuration = new Configuration(Configuration.getVersion());
        //目前来说我们先设置本地路径
        configuration.setDirectoryForTemplateLoading(
                new File("E:\\taotao\\taotao-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("utf-8");
        //模板对象
        Template template = configuration.getTemplate("demo3.ftl");
        Map map = new HashMap();
        Student student1 = new Student(1,"张三1",15);
        Student student2 = new Student(2,"张三2",25);
        Student student3 = new Student(3,"张三3",35);
        Student student4 = new Student(4,"张三4",45);
        Student student5 = new Student(5,"张三5",55);
        //告诉我们静态页面，输出到哪里
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        map.put("students",students);
        map.put("hello","xxxxxxxxxxxxxxxxxxxxxx");
        map.put("date",new Date());
        Writer writer = new FileWriter(new File("E:\\abc.html"));
        //第一个是数据，第二个是地址
        template.process(map,writer);
        writer.close();

    }
}
