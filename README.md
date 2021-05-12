# 一个非常基础的论坛
## 2021年春季xjtu软件工程小组项目
---
### 用到的技术
> 开发环境：Windows10
> 
> IDE：IntelliJ IDEA
> 
> 项目管理工具：Maven
>
> 前端技术：HTML , CSS , JavaScript , JQuery , AJax , BootStrap
> 
> 后端技术：Java , Spring , SpringMVC , MySQL , Druid
> 
> 运行环境：Tomcat , JDK11
### 已实现的功能
* 登陆注册
* 浏览看帖
* 分类分区
* 发帖
* 评论
* 回复
* 收藏
* 搜索
* 修改头像
* 修改昵称
* 发帖记录
* 个人收藏
* 新消息提醒
* 阅读提醒并清除消息
---
### 部分项目文件说明
1. 静态资源在` webapp `目录下,其中包括`css` ,`fonts`, `js`, `img`, `pages`,对应具体的资源，页面资源都在`pages`中，主页直接在`webapp`下；
2. java代码就在`src`的最底层,包括`dao`,`controller`, `domain`, 然后`service` 层忘了写了。。都写在`controller`里了；
3. 配置文件，`pom.xml`中是maven的配置，包含了引入的依赖,在`WEB-INF`下有`web.xml`中进行了web项目的配置,包括servlet的配置和引入配置文件等，
在`reasources`下的`beans.xml`配置了Spring和SpringMVC，扫描器、数据库相关配置都是在这里。

### 使用
1. 使用git将代码clone到本地，建议导入IDEA;
2. 配置Tomcat和MySql，修改`beans.xml`中的数据库部分为自己的数据库，并在数据库中建立相应的表；
3. 在中添加一些数据，如在art_type中按分类加入一些内容。

### 目前存在的问题
1. 可能是由于服务器的原因，图片上传后无法立刻回显，得重新部署才行，所以建议把图片的保存路径设置到项目文件夹之外，同时在图片标签中修改加载图片的路径；
2. 页面之间的跳转不是特别人性化，设计主页的跳转每次都会会到固定的页面；
3. 发帖和回帖并不能发图片、表情等；
4. 分类专区部分没有添加图标；
5. 后端数据库没有进行事务操作，并发量稍大大概率出现问题。

### 下一步更新
1. 帖子和回复中应该有图片，我觉得这个应该是最重要的；
2. 对ui进行美化；
3. 加入安全框架，将权限相关的判断交给Shiro(或Spring Security)，借此可以开发出版主的功能，应包括看到和普通用户不一样的界面，对帖子和用户进行一些管理，
加精置顶类似的；
4. 添加事务操作和修复bug；
5. 增添一些新的功能，如：在浏览时可以进行排序，个人可以删贴等；
6. 开发后台管理系统，这个我个人觉得应该是独立的pc端的。

### something
* 第一次写项目，所以有很多做得不好的，数据库建的很不规范，后端代码很乱，前端代码也有很多冗余。。。大佬们不要嘲笑
---
### 附：数据库中的表
##### art_type
![art_type](https://user-images.githubusercontent.com/83497285/116804925-616c7600-ab55-11eb-9d6b-5486332aa4d4.png)
##### article
![article](https://user-images.githubusercontent.com/83497285/116804960-84972580-ab55-11eb-8d1a-60805ddd0656.png)
##### collect
![collect](https://user-images.githubusercontent.com/83497285/116804986-bf00c280-ab55-11eb-905c-8fcd4ed4c614.png)
##### comments
![comments](https://user-images.githubusercontent.com/83497285/116804994-ce800b80-ab55-11eb-849c-a577607cd9e8.png)
##### user
![user](https://user-images.githubusercontent.com/83497285/116804996-da6bcd80-ab55-11eb-8ced-9d649679e62e.png)


