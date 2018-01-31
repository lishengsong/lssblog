##lssBlog
###技术简介
>
###cron表达式
>http://cron.qqe2.com/

##数据表
DROP TABLE IF EXISTS `bjy_article`;
CREATE TABLE `bjy_article` (
  `aid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章表主键',
  `title` char(100) NOT NULL DEFAULT '' COMMENT '标题',
  `author` varchar(15) NOT NULL DEFAULT '' COMMENT '作者',
  `content` mediumtext NOT NULL COMMENT '文章内容',
  `keywords` varchar(255) NOT NULL DEFAULT '' COMMENT '关键字',
  `description` char(255) NOT NULL DEFAULT '' COMMENT '描述',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '文章是否显示 1是 0否',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
  `is_top` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否置顶 1是 0否',
  `is_original` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否原创',
  `click` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点击数',
  `addtime` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `cid` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '分类id',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_article_pic
-- ----------------------------
DROP TABLE IF EXISTS `bjy_article_pic`;
CREATE TABLE `bjy_article_pic` (
  `ap_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT '图片路径',
  `aid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属文章id',
  PRIMARY KEY (`ap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `bjy_article_tag`;
CREATE TABLE `bjy_article_tag` (
  `aid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '文章id',
  `tid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '标签id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_category
-- ----------------------------
DROP TABLE IF EXISTS `bjy_category`;
CREATE TABLE `bjy_category` (
  `cid` tinyint(2) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `cname` varchar(15) NOT NULL DEFAULT '' COMMENT '分类名称',
  `keywords` varchar(255) DEFAULT '' COMMENT '关键词',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `pid` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '父级栏目id',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_chat
-- ----------------------------
DROP TABLE IF EXISTS `bjy_chat`;
CREATE TABLE `bjy_chat` (
  `chid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '碎言id',
  `date` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '发表时间',
  `content` text NOT NULL COMMENT '内容',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否显示',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_comment
-- ----------------------------
DROP TABLE IF EXISTS `bjy_comment`;
CREATE TABLE `bjy_comment` (
  `cmtid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ouid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论用户id 关联oauth_user表的id',
  `type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '1：文章评论',
  `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父级id',
  `aid` int(10) unsigned NOT NULL COMMENT '文章id',
  `content` text NOT NULL COMMENT '内容',
  `date` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论日期',
  `status` tinyint(1) unsigned NOT NULL COMMENT '1:已审核 0：未审核',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`cmtid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_config
-- ----------------------------
DROP TABLE IF EXISTS `bjy_config`;
CREATE TABLE `bjy_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置项键名',
  `value` text COMMENT '配置项键值 1表示开启 0 关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_link
-- ----------------------------
DROP TABLE IF EXISTS `bjy_link`;
CREATE TABLE `bjy_link` (
  `lid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `lname` varchar(50) NOT NULL DEFAULT '' COMMENT '链接名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接地址',
  `sort` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '文章是否显示 1是 0否',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `bjy_oauth_user`;
CREATE TABLE `bjy_oauth_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关联的本站用户id',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '类型 1：QQ  2：新浪微博 3：豆瓣 4：人人 5：开心网',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '第三方昵称',
  `head_img` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `openid` varchar(40) NOT NULL DEFAULT '' COMMENT '第三方用户id',
  `access_token` varchar(255) NOT NULL DEFAULT '' COMMENT 'access_token token',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '绑定时间',
  `last_login_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最后登录时间',
  `last_login_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '最后登录ip',
  `login_times` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '登录次数',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `is_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否是admin',
  `is_vip` tinyint(2) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_project_article
-- ----------------------------
DROP TABLE IF EXISTS `bjy_project_article`;
CREATE TABLE `bjy_project_article` (
  `aid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目表主键',
  `title` char(100) NOT NULL DEFAULT '' COMMENT '标题',
  `author` varchar(15) NOT NULL DEFAULT '' COMMENT '作者',
  `markdown` mediumtext NOT NULL COMMENT '文章内容',
  `html` mediumtext NOT NULL COMMENT '文章内容',
  `cover` varchar(255) NOT NULL DEFAULT '' COMMENT '封面图',
  `keywords` varchar(255) NOT NULL DEFAULT '' COMMENT '关键字',
  `description` char(255) NOT NULL DEFAULT '' COMMENT '描述',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '文章是否显示 1是 0否',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
  `is_top` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否置顶 1是 0否',
  `is_original` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否原创',
  `click` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点击数',
  `addtime` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `cid` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '分类id',
  `nolock` varchar(160) DEFAULT NULL,
  `yeslock` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_project_category
-- ----------------------------
DROP TABLE IF EXISTS `bjy_project_category`;
CREATE TABLE `bjy_project_category` (
  `cid` tinyint(2) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `cname` varchar(15) NOT NULL DEFAULT '' COMMENT '分类名称',
  `keywords` varchar(255) DEFAULT '' COMMENT '关键词',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `pid` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '父级栏目id',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bjy_tag
-- ----------------------------
DROP TABLE IF EXISTS `bjy_tag`;
CREATE TABLE `bjy_tag` (
  `tid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `tname` varchar(10) NOT NULL DEFAULT '' COMMENT '标签名',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
