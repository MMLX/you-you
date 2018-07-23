<hmtl>
    <head>
        <title>测试</title>
    </head>
    <body>
       <table cellpadding="0" cellspacing="0" border="1">
           <tr>
               <td>学号</td>
               <td>年龄</td>
               <td>学号</td>
           </tr>
           <#list students as student>
               <#if student_index%2==0>
               <tr style="background: red">
               <#else>
               <tr style="background: blue;">
               </#if>
                   <td>${student.id}</td>
                   <td>${student.username}</td>
                   <td>${student.age}</td>
               </tr>
           </#list>
       </table>
    <br/>
       <!--
       日期格式：${date?date}<br/>
       日期格式：${date?time}<br/>
       日期格式：${date?datetime}<br/>
       -->
       日期格式：${date?string('yyyy/MM/dd HH:mm:ss')}<br/>
       null值处理:${val!}<br/> <!-- 他代表空"" -->
       判断该值是否为null:
            <#if xxxxx??>
                该值不为null
            <#else>
                该值为null
            </#if>
        包含页面：
        <#include "demo1.ftl">

    </body>
</hmtl>
