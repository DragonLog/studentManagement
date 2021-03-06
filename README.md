> **写在前面的话：项目copy下来后，直接用idea导入应该是没有问题的（项目的文件夹和包结构要正确），根据你的需要修改pom中的mysql驱动和数据库资源文件后就可以部署到tomcat运行了！**
## 学生信息管理系统（**layUI + servlet + jdbc**）
***
> **使用原生 servlet作为后端进行开发**
1. **后端采用纯servlet进行开发，同时搭配原生的jdbc，整个架构没有使用任何框架，可以说是非常原始了；**    
2. **验证码工具类可以返回验证码图片和验证码内容，数据库连接工具类可以返回一个数据库连接和关闭数据库连接的方法（但是封装和设计模式不好，虽然线程安全，但使用会频繁创建数据库连接，开销很大），还有一个工具类借助gson将request的数据转换为json串；**   
3. **Dao层都是单表的CURD，没有复杂的业务所以也就没有添加事务的处理；**
4. **这里的业务层和控制层几乎耦合在了一起，一个业务一个servlet的方式造成了严重的代码冗余和资源浪费，在一个servlet里分别使用路径匹配和if-else方法匹配才是正确方法；**  
5. **典型的前后端不分离项目，前端集成了layui和jquery实现了非常精美和赏心悦目的效果，特别是登录模块和整个后台模块都比较精美，但是在开发过程中可以明显感到前端的开发难于后端！**    
6. **这里同时使用了util.date和sql.date，可以说是一大败笔，造成了很多地方的格式转换出现了问题（觉得别扭的小伙伴可以都换成util.date，然后用@JsonFormat与@DateTimeFormat注解就行，数据库字段可以使用datetime），后者是前者的子类，**
7. **这是我大三上学期的企业课大作业，整个项目皆为原创，如你所见除了前端稍微好看点外并没有任何优点，就连代码量最大的CURD在框架盛行的现在用个mybatis逆向工程或者MP都可以快速实现，所以任课老师也对该项目的评价很低（但是他教的就是servlet和jsp啊，早说用spring啥的可以加分，我一开始就去学框架了，气！开个玩笑哈！）。当时的我简直是着了魔的拼命做前端，甚至忘了自己应该专注于后端，复杂的业务（那种多表联查）也没怎么做，看着其他同学那时候就开始用spring和mybatis了，对比之下我这就太low了！哈哈，不过我也不后悔，毕竟servlet比springmvc偏向底层，作为入门学习，整个项目使用servlet也能为后面的框架学习打下基础嘛，然而花大量时间在前端还是不合适的，虽然前端也学到了很多，但你始终要为后端服务的。努力很重要，选择更重要！**
> **注：mysql5到mysql8还是做了很多改进的，不仅仅是安装和配置，就连它们的连接驱动包很多地方都做了改动。** 
***
![示例图片](https://github.com/DragonLog/studentManagement/blob/main/pictureForExample/show1.jpg)
![示例图片](https://github.com/DragonLog/studentManagement/blob/main/pictureForExample/show2.jpg)
![示例图片](https://github.com/DragonLog/studentManagement/blob/main/pictureForExample/show3.jpg)
![示例图片](https://github.com/DragonLog/studentManagement/blob/main/pictureForExample/show4.jpg)
![示例图片](https://github.com/DragonLog/studentManagement/blob/main/pictureForExample/show5.jpg)

