-- we don't know how to generate schema ntmx (class Schema) :(
create table if not exists ntm_api
(
	id bigint default '0' not null
		primary key,
	`unique` varchar(64) not null,
	name varchar(50) not null comment '名称',
	version int(4) default '1' not null comment '版本',
	method tinyint(2) not null comment '请求方式 1-GET 2-POST 3-PUT 4-PATCH 5-DELETE',
	appid varchar(32) default '0' not null comment '应用标识',
	inner_interface varchar(128) null comment '业务传输对象',
	inner_method varchar(128) null,
	is_ia bit not null comment '是否需要认证授权 0-不需要 1-需要',
	is_async bit not null comment '是否需要异步 0-同步 1-异步',
	daily_flow_limit int(8) default '0' not null,
	result varchar(3072) null comment '返回结果',
	status tinyint(2) default '1' not null comment '状态 0-失效 1-生效',
	create_time datetime not null,
	modify_time datetime not null
)
charset=utf8
;

create table if not exists ntm_api_param
(
	id bigint not null
		primary key,
	pid bigint default '0' not null comment '判断当前参数的前置判断条件',
	api_id bigint not null,
	name varchar(32) not null comment '字段名称',
	status tinyint(2) not null comment '状态 0-失效 1-生效',
	type tinyint(2) not null comment '类型 具体的查看ApiParamTypeEnum',
	is_required bit not null comment '是否必须 0-非必须 1-必须',
	err_msg varchar(128) null,
	desp varchar(128) null,
	example varchar(512) null,
	create_time datetime not null comment '创建时间',
	modify_time datetime not null comment '修改时间'
)
charset=utf8
;

create table if not exists ntm_config_setting
(
	id bigint auto_increment
		primary key,
	pid bigint default '0' not null,
	config_key varchar(64) not null,
	config_value varchar(512) null,
	content varchar(64) null,
	status tinyint(2) default '1' not null,
	create_time datetime not null,
	modify_time datetime not null
)
;

