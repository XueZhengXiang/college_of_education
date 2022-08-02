# college_of_education
教育平台
该教育平台分为后端管理模块，前端用户使用模块。
一、项目背景

在线教育顾名思义，是以网络为介质的教学方式，通过网络，学员与教师即使相隔万里也可以开展教学活动，此外，借助网络课件，学员还可以随时随地进行学习，真正打破了时间和空间的限制，对于工作繁忙，学习时间不固定的职场人而言网络远程教育是最方便不过的学习方式。
二、项目商业模式

比较常用的商业模式

B2C模式（Business To Customer会员模式）
商家到用户，这种模式是自己制作大量自有版权的视频，放在自有平台上，让用户按月付费或者按年付费。这种模式简单，快速，只要专心录制大量视频即可快速发展，其曾因为lynda的天价融资而大热。但在中国由于版权保护意识不强，教育内容易于复制，有海量的免费资源的竞争对手众多等原因，难以取得像样的现金流。
代表网站：
lynda https://www.lynda.com/
慕课网 https://www.imooc.com/
谷粒学院 http://www.gulixueyuan.com/
B2B2C（商家到商家到用户)
平台链接第三方教育机构和用户，平台一般不直接提供课程内容，而是更多承担教育的互联网载体角色，为教学过程各个环节提供全方位支持和服务。
代表网站：
51cto http://edu.51cto.com/
腾讯课堂 https://ke.qq.com/

谷粒学院（在线教育项目），是一个B2C模式的职业技能在线教育系统，分为前台用户系统和后台运营平台。
三、工程模块
系统后台（管理员使用） 	系统前台（用户使用）
讲师管理模块 	首页数据显示（front)
课程分类管理模块 	讲师列表和详情(front)
课程管理模块(视频) 	课程列表和详情（视频)
统计分析模块 	微信登录和手机注册
权限管理 	微信扫码支付
四、技术架构

系统开发阶段使用了前后端分离开发，部署阶段使用了容器技术。

前后端分离开发：

在这里插入图片描述

后端技术：spingboot，springcloud，mybatisplus，spring security，redis，mysql，maven，easyExcel，jwt，OAuth2

前段技术：vue，element-ui，axios，node.js

其他技术：阿里云oss，阿里云视频点播服务，阿里云短信服务，微信支付和登录，docker，git

在这里插入图片描述
五、项目源码

后端接口源码（包含数据库、学习笔记）：https://github.com/XueZhengXiang/college_of_education/tree/master

前端页面和后端页面源码：https://gitee.com/StarSea007/vscode2020

education-vue-admin：后端的前台页面
education-vue-web：前端的前台页面
vue1010：项目中练习的页面
