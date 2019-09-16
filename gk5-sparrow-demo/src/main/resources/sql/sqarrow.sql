-- DDL

create schema if not exists sparrow collate utf8mb4_general_ci;

create table if not exists sparrow.sys_admin
(
	uid int auto_increment
		primary key,
	nickname varchar(50) not null comment '用户名',
	email varchar(100) null comment '邮箱',
	phone varchar(11) null comment '手机',
	status smallint(2) not null comment '状态 0：禁用 1：正常',
	create_uid int not null comment '创建者ID',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	login_name varchar(16) not null comment '登陆名',
	password varchar(64) not null comment '密码',
	ip varchar(32) null comment 'IP地址'
)
comment '系统用户表';

INSERT INTO sparrow.sys_admin (uid, nickname, email, phone, status, create_uid, create_time, update_time, login_name, password, ip) VALUES (1, 'admin', 'bannongvipp@163.com', '13819468874', 1, 1, '2018-11-05 17:19:05', '2019-09-16 10:49:13', 'admin', '$apr1$admin$yvpPMP1LXvPfZIssZW4XI/', '0:0:0:0:0:0:0:1');

create table if not exists sparrow.sys_admin_role
(
	id int auto_increment
		primary key,
	uid int null comment '用户ID',
	role_id int null comment '角色ID'
)
comment '系统用户角色关系表';

INSERT INTO sparrow.sys_admin_role (id, uid, role_id) VALUES (54, 1, 1);

create table if not exists sparrow.sys_menu
(
	id int auto_increment
		primary key,
	parent_id int null comment '父菜单ID，一级菜单为0',
	menu_name varchar(32) not null comment '菜单名称',
	path varchar(64) null comment '路径',
	menu_type smallint(2) not null comment '类型:0:目录,1:菜单,2:按钮',
	icon varchar(32) null comment '菜单图标',
	create_uid int not null comment '创建者ID',
	update_uid int not null comment '修改者ID',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	status smallint(2) not null comment '状态 0：禁用 1：正常',
	router varchar(64) null comment '路由',
	alias varchar(64) null comment '别名'
)
comment '菜单表';

INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (1, 0, '系统管理', '', 1, 'layui-icon-set', 1, 1, '2018-11-27 14:52:10', '2018-11-27 15:11:15', 1, null, '');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (23, 1, '用户管理', 'views/user/index.html', 2, 'layui-icon-username', 1, 1, '2018-11-27 15:10:32', '2018-12-12 15:39:18', 1, 'user', 'sys:user:list');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (24, 1, '角色管理', 'views/role/index.html', 2, 'layui-icon-face-surprised', 1, 1, '2018-11-27 15:16:59', '2018-12-12 15:40:03', 1, 'role', 'sys:role:list');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (25, 1, '菜单管理', 'views/menu/index.html', 2, 'layui-icon-template', 1, 1, '2018-11-27 15:17:59', '2018-12-12 15:37:35', 1, 'menu', 'sys:menu:list');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (26, 1, '资源管理', 'views/resource/index.html', 2, 'layui-icon-read', 1, 1, '2018-11-27 15:18:31', '2018-12-12 15:35:38', 1, 'resource', 'sys:resource:list');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (27, 26, '刷新资源', '', 3, 'layui-icon-refresh-3', 1, 1, '2018-11-27 15:19:15', '2018-12-12 15:35:14', 1, null, 'sys:resource:refresh');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (28, 25, '添加', '', 3, 'layui-icon-add-circle-fine', 1, 1, '2018-11-27 15:20:06', '2018-12-12 15:45:47', 1, null, 'sys:menu:add');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (29, 25, '修改', '', 3, 'layui-icon-senior', 1, 1, '2018-11-27 15:20:27', '2018-12-12 15:36:51', 1, null, 'sys:menu:edit');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (30, 25, '删除', '', 3, 'layui-icon-close', 1, 1, '2018-11-27 15:21:14', '2018-12-12 15:35:49', 1, null, 'sys:menu:delete');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (31, 24, '添加', '', 3, 'layui-icon-add-circle-fine', 1, 1, '2018-11-27 15:20:06', '2018-12-12 15:38:07', 1, null, 'sys:role:add');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (32, 24, '修改', '', 3, 'layui-icon-senior', 1, 1, '2018-11-27 15:20:27', '2018-12-12 15:44:19', 1, null, 'sys:role:edit');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (33, 24, '删除', '', 3, 'layui-icon-close', 1, 1, '2018-11-27 15:21:14', '2018-12-12 15:36:07', 1, null, 'sys:role:delete');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (34, 23, '添加', '', 3, 'layui-icon-add-circle-fine', 1, 1, '2018-11-27 15:20:06', '2018-12-12 15:44:04', 1, null, 'sys:user:add');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (35, 23, '修改', '', 3, 'layui-icon-senior', 1, 1, '2018-11-27 15:20:27', '2019-09-11 14:31:44', 1, null, 'sys:user:edit');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (36, 23, '重置密码', '', 3, 'layui-icon-fire', 1, 1, '2018-11-27 15:21:14', '2018-12-12 15:38:48', 1, null, 'sys:user:resetpwd');
INSERT INTO sparrow.sys_menu (id, parent_id, menu_name, path, menu_type, icon, create_uid, update_uid, create_time, update_time, status, router, alias) VALUES (42, 24, '菜单授权', null, 3, 'layui-icon-auz', 1, 1, '2018-12-08 23:58:42', '2018-12-12 15:41:52', 1, null, 'sys:role:perm');

create table if not exists sparrow.sys_menu_resource
(
	id int auto_increment
		primary key,
	menu_id int null comment '菜单ID',
	resource_id varchar(32) null comment '资源ID'
)
comment '菜单资源关系表';

INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (70, 27, 'f45f1b577d72dcd86b84c6f033682b53');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (71, 26, '829a851334028a6e47b59f8dea0cf7cb');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (72, 30, 'f15f7b01ffe7166b05c3984c9b967837');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (73, 33, '6692d9d95184977f82d3252de2f5eac7');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (74, 29, 'a11e2191656cb199bea1defb17758411');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (75, 29, '6fd51f02b724c137a08c28587f48d7f3');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (76, 29, '2c654f1264fc85ac80516245672f3c47');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (77, 29, 'a5529264d2645996c83bba2e961d0ec3');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (80, 25, '6d1170346960aa8922b9b4d08a5bf71b');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (81, 25, '30218613e987e464b13e0c0b8721aec5');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (83, 31, 'd82de0a17f2c63106f98eb2f88d166e9');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (85, 36, '7baa5b852bc92715d7aa503c0a0d8925');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (87, 23, '579e469e8ac850de1ca0adc54d01acba');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (88, 23, 'b4770c0fe93fce7e829463328c800f20');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (89, 35, '30386fd7b8a4feb9c59861e63537acde');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (90, 35, '8a3b4dc05867f5946235ba5fbc492b76');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (91, 24, '04972e9f8e65b0364d9862687666da36');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (93, 42, 'a826bca352908155da4ca6702edfa2ed');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (94, 42, '30218613e987e464b13e0c0b8721aec5');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (95, 34, 'a71cb59835c613f39bd936deb20cdd27');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (96, 34, 'd9d6f7163b313b950710a637682354d1');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (97, 32, 'eaee955f405f6b96beab5136bfa3e29b');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (98, 32, 'd9d6f7163b313b950710a637682354d1');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (99, 28, '8cb1442c7814f65ce0d796e1ef93c715');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (100, 28, 'a5529264d2645996c83bba2e961d0ec3');
INSERT INTO sparrow.sys_menu_resource (id, menu_id, resource_id) VALUES (101, 28, '2c654f1264fc85ac80516245672f3c47');

create table if not exists sparrow.sys_resource
(
	id varchar(32) not null
		primary key,
	resource_name varchar(32) not null comment '资源名称',
	mapping varchar(64) not null comment '路径映射',
	method varchar(6) not null comment '请求方式',
	auth_type smallint(2) not null comment '权限认证类型',
	update_time datetime null,
	perm varchar(68) not null comment '权限标识'
)
comment '资源表';

INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('04972e9f8e65b0364d9862687666da36', '查询所有角色(分页)', '/roles', 'GET', 3, '2019-09-16 10:51:15', 'GET:/roles');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('29c4c75326ecf3a82f815c43b0085b2f', '修改账户信息', '/account/info', 'PUT', 1, '2019-09-16 10:51:15', 'PUT:/account/info');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('2c654f1264fc85ac80516245672f3c47', '查询父级菜单(下拉框)', '/menus/combos', 'GET', 3, '2019-09-16 10:51:15', 'GET:/menus/combos');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('30218613e987e464b13e0c0b8721aec5', '查询所有菜单', '/menus', 'GET', 3, '2019-09-16 10:51:15', 'GET:/menus');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('30386fd7b8a4feb9c59861e63537acde', '修改用户', '/users/{id}', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/users/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('3ae42391ca3abe20c5cca35f4427cf9c', '获取账户按钮', '/account/buttons/aliases', 'GET', 1, '2019-09-16 10:51:15', 'GET:/account/buttons/aliases');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('579e469e8ac850de1ca0adc54d01acba', '查询所有用户', '/users', 'GET', 3, '2019-09-16 10:51:15', 'GET:/users');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('58e6f712d017c5f020adb6d5f2f3089b', '查询所有管理员', '/admins', 'GET', 3, '2019-06-24 22:00:14', 'GET:/admins');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('6692d9d95184977f82d3252de2f5eac7', '删除角色', '/roles/{id}', 'DELETE', 3, '2019-09-16 10:51:15', 'DELETE:/roles/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('6ab0f8a49671e489f11a1bef2fcaf102', '清除Token', '/account/token', 'DELETE', 1, '2019-09-16 10:51:15', 'DELETE:/account/token');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('6d1170346960aa8922b9b4d08a5bf71b', '设置菜单状态', '/menus/{id}/status', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/menus/{id}/status');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('6fd51f02b724c137a08c28587f48d7f3', '查询单个菜单', '/menus/{id}', 'GET', 3, '2019-09-16 10:51:15', 'GET:/menus/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('7baa5b852bc92715d7aa503c0a0d8925', '重置用户密码', '/users/{id}/password', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/users/{id}/password');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('829a851334028a6e47b59f8dea0cf7cb', '查询所有资源(分页)', '/resources', 'GET', 3, '2019-09-16 10:51:15', 'GET:/resources');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('842e33410b5a97b6c797e4782c97a90e', '获取Token', '/account/token', 'POST', 2, '2019-09-16 10:51:15', 'POST:/account/token');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('8a3b4dc05867f5946235ba5fbc492b76', '查询单个用户', '/users/{id}', 'GET', 3, '2019-09-16 10:51:15', 'GET:/users/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('8cb1442c7814f65ce0d796e1ef93c715', '添加菜单', '/menus', 'POST', 3, '2019-09-16 10:51:15', 'POST:/menus');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('982803fc834e82cbb2ac1b93f2a47690', '查询单个角色', '/roles/{id}', 'GET', 3, '2019-09-16 10:51:15', 'GET:/roles/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('a11e2191656cb199bea1defb17758411', '修改菜单', '/menus/{id}', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/menus/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('a5529264d2645996c83bba2e961d0ec3', '查询所有资源', '/resources/resources', 'GET', 3, '2019-09-16 10:51:15', 'GET:/resources/resources');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('a71cb59835c613f39bd936deb20cdd27', '创建用户', '/users', 'POST', 3, '2019-09-16 10:51:15', 'POST:/users');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('a826bca352908155da4ca6702edfa2ed', '修改角色菜单', '/roles/{id}/menus', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/roles/{id}/menus');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('ab4d5b382c85011a0fc3aa4e2572d924', '查询单个管理员', '/admins/{id}', 'GET', 3, '2019-06-24 22:00:14', 'GET:/admins/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('b1694e25cfb30628a2bfa5cc272e5464', '设置管理员状态', '/admins/{id}/status', 'PUT', 3, '2019-06-24 22:00:14', 'PUT:/admins/{id}/status');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('b4770c0fe93fce7e829463328c800f20', '设置用户状态', '/users/{id}/status', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/users/{id}/status');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('b4770c0fe93fce7e829463328c800f27', 'APP首页Banner', '/home/banners', 'GET', 2, '2019-06-24 16:35:47', 'GET:/home/banners');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('c2db9729dcd4a7d703e45411bb445dfd', '修改密码', '/account/password', 'PUT', 1, '2019-09-16 10:51:15', 'PUT:/account/password');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('cd2426f2effd2ab4e8b96dd60447bc06', '修改管理员', '/admins/{id}', 'PUT', 3, '2019-06-24 22:00:14', 'PUT:/admins/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('d81bffa6ffd70cc443703820b5a95e8d', '获取账户菜单', '/account/menus', 'GET', 1, '2019-09-16 10:51:15', 'GET:/account/menus');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('d82de0a17f2c63106f98eb2f88d166e9', '添加角色', '/roles', 'POST', 3, '2019-09-16 10:51:15', 'POST:/roles');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('d9d6f7163b313b950710a637682354d1', '查询所有角色', '/roles/roles', 'GET', 3, '2019-09-16 10:51:15', 'GET:/roles/roles');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('db2b3f3162bdc63cf92d398e868500da', '创建管理员', '/admins', 'POST', 3, '2019-06-24 22:00:14', 'POST:/admins');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('e78940daf86b9ac5563d539e8802429c', '获取账户详情', '/account/info', 'GET', 1, '2019-09-16 10:51:15', 'GET:/account/info');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('eaee955f405f6b96beab5136bfa3e29b', '修改角色', '/roles/{id}', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/roles/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('f0af37b84883cbafc8a4587549c376e2', '重置管理员密码', '/admins/{id}/password', 'PUT', 3, '2019-06-24 22:00:14', 'PUT:/admins/{id}/password');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('f15f7b01ffe7166b05c3984c9b967837', '删除菜单', '/menus/{id}', 'DELETE', 3, '2019-09-16 10:51:15', 'DELETE:/menus/{id}');
INSERT INTO sparrow.sys_resource (id, resource_name, mapping, method, auth_type, update_time, perm) VALUES ('f45f1b577d72dcd86b84c6f033682b53', '刷新资源', '/resources', 'PUT', 3, '2019-09-16 10:51:15', 'PUT:/resources');

create table if not exists sparrow.sys_role
(
	id int auto_increment
		primary key,
	role_name varchar(64) collate utf8mb4_bin not null comment '角色名称',
	create_uid int not null comment '创建者ID',
	update_uid int not null comment '修改者ID',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	remark varchar(128) collate utf8mb4_bin null comment '备注'
)
comment '角色表';

INSERT INTO sparrow.sys_role (id, role_name, create_uid, update_uid, create_time, update_time, remark) VALUES (1, '超级管理员', 1, 1, '2018-11-12 15:59:43', '2018-11-12 15:59:47', '超级管理员');
INSERT INTO sparrow.sys_role (id, role_name, create_uid, update_uid, create_time, update_time, remark) VALUES (2, '普通管理员', 1, 1, '2018-11-12 16:00:17', '2018-11-12 16:00:19', '普通管理员');

create table if not exists sparrow.sys_role_menu
(
	id int auto_increment
		primary key,
	role_id int not null comment '角色ID',
	menu_id int not null comment '菜单ID'
)
comment '角色菜单关系表';

INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (262, 1, 1);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (263, 1, 23);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (264, 1, 34);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (265, 1, 35);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (266, 1, 36);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (267, 1, 24);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (268, 1, 31);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (269, 1, 32);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (270, 1, 33);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (271, 1, 42);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (272, 1, 25);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (273, 1, 28);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (274, 1, 29);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (275, 1, 30);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (276, 1, 26);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (277, 1, 27);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (287, 2, 1);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (288, 2, 23);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (289, 2, 34);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (290, 2, 35);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (291, 2, 36);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (292, 2, 24);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (293, 2, 31);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (294, 2, 32);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (295, 2, 33);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (296, 2, 42);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (297, 2, 25);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (298, 2, 28);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (299, 2, 29);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (300, 2, 30);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (301, 2, 26);
INSERT INTO sparrow.sys_role_menu (id, role_id, menu_id) VALUES (302, 2, 27);

create table if not exists sparrow.sys_role_resource
(
	id int auto_increment
		primary key,
	role_id int not null comment '角色ID',
	resource_id varchar(32) not null comment '菜单ID'
)
comment '角色资源关系表';
