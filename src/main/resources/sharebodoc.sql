drop table if exists community;

drop table if exists equipment;

drop table if exists feeType;

drop table if exists vehicle;

drop table if exists whitelist;

/*==============================================================*/
/* Table: community                                             */
/*==============================================================*/
create table community
(
   commId               varchar(36) not null comment '主键',
   feeId                varchar(36) comment '收费模式外键',
   cname                varchar(200) not null comment '小区名字',
   caddress             varchar(200) not null comment '小区地址',
   partner              varchar(20) not null comment '小区合作方',
   commFk               varchar(36) comment '小区标识1（用来作为APP小区标志）',
   issenior             int(1) not null comment '收费方式(0:默认线下收费，1：线上收费，2：混合收费)',
   psCount              int(6) comment '车位总数',
   primary key (commId)
);

alter table community comment '小区停车场表';

/*==============================================================*/
/* Table: equipment                                             */
/*==============================================================*/
create table equipment
(
   equipmentNumber      varchar(50) not null comment '设备号码（相机上序列号）',
   isinout              int(1) not null comment '是否进出(1:进  2：出)',
   equipmentName        varchar(200) not null comment '设备名字（用来标记设备出入口）',
   commId               varchar(36) not null,
   equipmentId          varchar(36) not null,
   primary key (equipmentNumber)
);

alter table equipment comment '设备表';

/*==============================================================*/
/* Table: feeType                                               */
/*==============================================================*/
create table feeType
(
   feeId                varchar(36) not null,
   feeModelContext      varchar(500) not null,
   updateTime           datetime comment '修改时间',
   primary key (feeId)
);

alter table feeType comment '收费模式';

/*==============================================================*/
/* Table: vehicle                                               */
/*==============================================================*/
create table vehicle
(
   vehicleId            varchar(36) not null,
   inTime               datetime not null comment '进入时间',
   outTime              datetime comment '出去时间',
   carNo                varchar(20) not null comment '车牌号',
   feeModel             varchar(500) not null comment '收费模式(传入Json串)',
   payType              int(1) not null comment '支付类型(0:未支付，1：线下收费，2：其他支付待定)',
   paidInFee            double(8,2) comment '实收价格',
   arFee                double(8,2) comment '应收价格',
   iswl                 int(1) not null comment '是否为白名单(0:是1：非)',
   inMac                varchar(50) not null comment '进入地址',
   outMac               varchar(50) comment '驶出地址'
);

alter table vehicle comment '进出记录';

/*==============================================================*/
/* Table: whitelist                                             */
/*==============================================================*/
create table whitelist
(
   whitelistId          varchar(36) not null comment '主键',
   com_commId           varchar(36) comment '主键',
   carNo                varchar(50) not null comment '车牌号',
   periodvalidity       date not null comment '白名单有效期',
   isfailure            int(1) not null comment '是否失效(0:正常1：失效)',
   entrytime            datetime not null comment '录入时间',
   commid               varchar(36) not null comment '小区外键',
   name                 varchar(100) comment '车主名字',
   address              varchar(100) comment '车主地址门户',
   phone                varchar(20) comment '车主电话',
   primary key (whitelistId)
);

alter table whitelist comment '白名单';

alter table community add constraint FK_community_feetype foreign key (feeId)
      references feeType (feeId) on delete restrict on update restrict;

alter table equipment add constraint FK_equipment_community foreign key (commId)
      references community (commId) on delete restrict on update restrict;

alter table vehicle add constraint FK_vehicle_equipment_inMac foreign key (inMac)
      references equipment (equipmentNumber) on delete restrict on update restrict;

alter table vehicle add constraint FK_vehicle_equipment_outMac foreign key (outMac)
      references equipment (equipmentNumber) on delete restrict on update restrict;

alter table whitelist add constraint FK_Reference_3 foreign key (com_commId)
      references community (commId) on delete restrict on update restrict;
