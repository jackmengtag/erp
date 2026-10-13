-- ----------------------------------------------------------------------------
-- 制造企业ERP系统 业务库表结构 (基于若依 RuoYi 3.9.2)
-- 使用方式：
--   1) 先导入若依基础脚本 sql/ry_20260417.sql（系统/权限/字典等基础表）
--   2) 再导入本脚本
--   3) 最后导入 sql/erp_menu.sql（ERP业务菜单与权限）
-- 说明：所有业务表均包含 create_by/create_time/update_by/update_time/remark 审计字段
-- ----------------------------------------------------------------------------

-- ============================================================================
-- 一、基础平台扩展 (erp-platform)
-- ============================================================================

-- 编码规则
drop table if exists erp_code_rule;
create table erp_code_rule (
  rule_id         bigint(20)      not null auto_increment    comment '规则ID',
  rule_code       varchar(64)     not null                   comment '规则编码',
  rule_name       varchar(100)    not null                   comment '规则名称',
  biz_object      varchar(64)     not null                   comment '业务对象(如 sales_order)',
  prefix          varchar(32)     default ''                 comment '前缀',
  date_pattern    varchar(32)     default 'yyyyMMdd'         comment '日期格式(yyyyMMdd/yyyyMM/none)',
  seq_length      int(4)          default 4                  comment '流水号位数',
  seq_reset       char(1)         default '2'                comment '重置周期(1按日 2按月 3按年 4不重置)',
  separator       varchar(8)      default ''                 comment '分隔符',
  sample          varchar(100)    default ''                 comment '示例',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志(0存在 2删除)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (rule_id),
  unique key uk_code_rule (rule_code)
) engine=innodb auto_increment=1 comment = 'ERP编码规则表';

-- 编码流水
drop table if exists erp_code_serial;
create table erp_code_serial (
  serial_id       bigint(20)      not null auto_increment    comment '流水ID',
  rule_code       varchar(64)     not null                   comment '规则编码',
  period_key      varchar(16)     not null                   comment '周期键(如 20261013)',
  current_seq     bigint(20)      default 0                  comment '当前流水值',
  update_time     datetime                                   comment '更新时间',
  primary key (serial_id),
  unique key uk_code_serial (rule_code, period_key)
) engine=innodb comment = 'ERP编码流水表';

-- 附件
drop table if exists erp_attachment;
create table erp_attachment (
  attachment_id   bigint(20)      not null auto_increment    comment '附件ID',
  biz_type        varchar(64)     default ''                 comment '业务类型',
  biz_id          varchar(64)     default ''                 comment '业务单据ID',
  file_name       varchar(255)    not null                   comment '文件名称',
  file_path       varchar(500)    not null                   comment '存储路径',
  file_url        varchar(500)    default ''                 comment '访问地址',
  file_suffix     varchar(32)     default ''                 comment '文件后缀',
  file_size       bigint(20)      default 0                  comment '文件大小(字节)',
  version         int(4)          default 1                  comment '版本号',
  del_flag        char(1)         default '0'                comment '删除标志(0存在 2删除)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (attachment_id),
  key idx_attach_biz (biz_type, biz_id)
) engine=innodb comment = 'ERP附件表';

-- 工作流定义
drop table if exists erp_workflow_def;
create table erp_workflow_def (
  wf_id           bigint(20)      not null auto_increment    comment '工作流ID',
  wf_code         varchar(64)     not null                   comment '工作流编码',
  wf_name         varchar(100)    not null                   comment '工作流名称',
  biz_object      varchar(64)     default ''                 comment '业务对象',
  amount_limit    decimal(18,2)   default 0                  comment '金额分级下限',
  assignee_type   varchar(16)     default 'role'             comment '审批人类型(role/user)',
  assignee_value  varchar(255)    default ''                 comment '审批人(角色key或用户)',
  approve_mode    varchar(16)     default 'single'           comment '会签模式(single或sign)',
  timeout_hours   int(6)          default 0                  comment '超时提醒(小时)',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (wf_id),
  unique key uk_wf_code (wf_code)
) engine=innodb comment = 'ERP工作流定义表';

-- 工作流实例
drop table if exists erp_workflow_instance;
create table erp_workflow_instance (
  instance_id     bigint(20)      not null auto_increment    comment '实例ID',
  wf_id           bigint(20)      not null                   comment '工作流ID',
  biz_type        varchar(64)     default ''                 comment '业务类型',
  biz_id          varchar(64)     default ''                 comment '业务单据ID',
  biz_title       varchar(255)    default ''                 comment '单据标题',
  amount          decimal(18,2)   default 0                  comment '单据金额',
  current_node    varchar(64)     default 'start'            comment '当前节点',
  status          char(1)         default '0'                comment '状态(0审批中 1通过 2驳回 3撤回)',
  submit_by       varchar(64)     default ''                 comment '提交人',
  submit_time     datetime                                   comment '提交时间',
  finish_time     datetime                                   comment '完成时间',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (instance_id),
  key idx_wf_inst_biz (biz_type, biz_id)
) engine=innodb comment = 'ERP工作流实例表';

-- 字段变更审计日志
drop table if exists erp_field_change_log;
create table erp_field_change_log (
  log_id          bigint(20)      not null auto_increment    comment '日志ID',
  biz_type        varchar(64)     default ''                 comment '业务类型',
  biz_id          varchar(64)     default ''                 comment '业务单据ID',
  field_name      varchar(64)     default ''                 comment '字段名',
  field_label     varchar(100)    default ''                 comment '字段说明',
  old_value       varchar(500)    default ''                 comment '修改前值',
  new_value       varchar(500)    default ''                 comment '修改后值',
  oper_type       varchar(16)     default ''                 comment '操作类型(insert/update/delete/audit)',
  oper_name       varchar(64)     default ''                 comment '操作人',
  oper_ip         varchar(128)    default ''                 comment '操作IP',
  oper_time       datetime                                   comment '操作时间',
  reason          varchar(500)    default ''                 comment '变更原因',
  primary key (log_id),
  key idx_fcl_biz (biz_type, biz_id)
) engine=innodb comment = 'ERP字段变更审计日志表';

-- ============================================================================
-- 二、主数据 (erp-mdm)
-- ============================================================================

-- 物料分类
drop table if exists erp_material_category;
create table erp_material_category (
  category_id     bigint(20)      not null auto_increment    comment '分类ID',
  parent_id       bigint(20)      default 0                  comment '父分类ID',
  ancestors       varchar(500)    default ''                 comment '祖级列表',
  category_code   varchar(64)     not null                   comment '分类编码',
  category_name   varchar(100)    not null                   comment '分类名称',
  order_num       int(4)          default 0                  comment '显示顺序',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (category_id)
) engine=innodb comment = 'ERP物料分类表';

-- 计量单位
drop table if exists erp_uom;
create table erp_uom (
  uom_id          bigint(20)      not null auto_increment    comment '单位ID',
  uom_code        varchar(32)     not null                   comment '单位编码',
  uom_name        varchar(50)     not null                   comment '单位名称',
  uom_type        varchar(16)     default 'qty'              comment '单位类型(qty数量/time时间/weight重量/volume体积)',
  precision_scale int(4)          default 2                  comment '小数位',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (uom_id),
  unique key uk_uom_code (uom_code)
) engine=innodb comment = 'ERP计量单位表';

-- 物料主数据
drop table if exists erp_material;
create table erp_material (
  material_id     bigint(20)      not null auto_increment    comment '物料ID',
  material_code   varchar(64)     not null                   comment '物料编码',
  material_name   varchar(200)    not null                   comment '物料名称',
  specification   varchar(255)    default ''                 comment '规格型号',
  category_id     bigint(20)      default 0                  comment '物料分类ID',
  uom_id          bigint(20)      default 0                  comment '基本单位ID',
  material_type   varchar(16)     default '1'                comment '物料类型(1原材料 2半成品 3成品 4外购件 5虚拟件)',
  batch_manage    char(1)         default '1'                comment '批次管理(1是 0否)',
  serial_manage   char(1)         default '0'                comment '序列号管理(1是 0否)',
  shelf_life_days int(6)          default 0                  comment '保质期(天)',
  purchase_flag   char(1)         default '1'                comment '采购属性(1是 0否)',
  produce_flag    char(1)         default '1'                comment '生产属性(1是 0否)',
  sale_flag       char(1)         default '1'                comment '销售属性(1是 0否)',
  lead_time_days  int(6)          default 0                  comment '采购提前期(天)',
  produce_lead_time int(6)        default 0                  comment '生产提前期(天)',
  safe_stock      decimal(18,4)   default 0                  comment '安全库存',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  cost_price      decimal(18,6)   default 0                  comment '标准成本',
  sale_price      decimal(18,6)   default 0                  comment '标准售价',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (material_id),
  unique key uk_material_code (material_code)
) engine=innodb comment = 'ERP物料主数据表';

-- 物料单位换算
drop table if exists erp_material_uom;
create table erp_material_uom (
  id              bigint(20)      not null auto_increment    comment '主键',
  material_id     bigint(20)      not null                   comment '物料ID',
  uom_id          bigint(20)      not null                   comment '单位ID',
  convert_rate    decimal(18,6)   default 1                  comment '换算率(相对基本单位)',
  create_time     datetime                                   comment '创建时间',
  primary key (id),
  key idx_mu_material (material_id)
) engine=innodb comment = 'ERP物料单位换算表';

-- 客户
drop table if exists erp_customer;
create table erp_customer (
  customer_id     bigint(20)      not null auto_increment    comment '客户ID',
  customer_code   varchar(64)     not null                   comment '客户编码',
  customer_name   varchar(200)    not null                   comment '客户名称',
  short_name      varchar(100)    default ''                 comment '客户简称',
  customer_level  varchar(16)     default 'A'                comment '客户等级(A/B/C/D)',
  contact_person  varchar(64)     default ''                 comment '联系人',
  contact_phone   varchar(64)     default ''                 comment '联系电话',
  address         varchar(255)    default ''                 comment '地址',
  credit_limit    decimal(18,2)   default 0                  comment '信用额度',
  credit_days     int(6)          default 0                  comment '账期(天)',
  tax_no          varchar(64)     default ''                 comment '税号',
  bank_account    varchar(64)     default ''                 comment '银行账号',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (customer_id),
  unique key uk_customer_code (customer_code)
) engine=innodb comment = 'ERP客户表';

-- 供应商
drop table if exists erp_supplier;
create table erp_supplier (
  supplier_id     bigint(20)      not null auto_increment    comment '供应商ID',
  supplier_code   varchar(64)     not null                   comment '供应商编码',
  supplier_name   varchar(200)    not null                   comment '供应商名称',
  short_name      varchar(100)    default ''                 comment '供应商简称',
  supplier_level  varchar(16)     default 'A'                comment '供应商等级(A/B/C/D)',
  contact_person  varchar(64)     default ''                 comment '联系人',
  contact_phone   varchar(64)     default ''                 comment '联系电话',
  address         varchar(255)    default ''                 comment '地址',
  credit_days     int(6)          default 0                  comment '账期(天)',
  tax_no          varchar(64)     default ''                 comment '税号',
  bank_account    varchar(64)     default ''                 comment '银行账号',
  evaluate_score  decimal(5,2)    default 0                  comment '评价得分',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (supplier_id),
  unique key uk_supplier_code (supplier_code)
) engine=innodb comment = 'ERP供应商表';

-- 工作中心
drop table if exists erp_work_center;
create table erp_work_center (
  wc_id           bigint(20)      not null auto_increment    comment '工作中心ID',
  wc_code         varchar(64)     not null                   comment '工作中心编码',
  wc_name         varchar(100)    not null                   comment '工作中心名称',
  dept_id         bigint(20)      default 0                  comment '所属部门',
  capacity_per_day decimal(18,2)  default 8                  comment '日产能(小时)',
  cost_rate       decimal(18,4)   default 0                  comment '单位工时成本',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (wc_id),
  unique key uk_wc_code (wc_code)
) engine=innodb comment = 'ERP工作中心表';

-- BOM 主表
drop table if exists erp_bom;
create table erp_bom (
  bom_id          bigint(20)      not null auto_increment    comment 'BOM ID',
  bom_code        varchar(64)     not null                   comment 'BOM编码',
  material_id     bigint(20)      not null                   comment '母件物料ID',
  version         varchar(32)     default 'V1'               comment '版本号',
  effect_date     date                                       comment '生效日期',
  expire_date     date                                       comment '失效日期',
  status          char(1)         default '0'                comment '状态(0草稿 1已审核 2停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (bom_id),
  unique key uk_bom_code (bom_code)
) engine=innodb comment = 'ERP BOM主表';

-- BOM 子件明细
drop table if exists erp_bom_line;
create table erp_bom_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  bom_id          bigint(20)      not null                   comment 'BOM ID',
  material_id     bigint(20)      not null                   comment '子件物料ID',
  qty_per         decimal(18,6)   default 1                  comment '单位用量',
  loss_rate       decimal(8,4)    default 0                  comment '损耗率(%)',
  substitute_flag char(1)         default '0'                comment '是否替代料(1是 0否)',
  line_no         int(6)          default 1                  comment '行号',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_bomline_bom (bom_id)
) engine=innodb comment = 'ERP BOM子件明细表';

-- 工艺路线主表
drop table if exists erp_routing;
create table erp_routing (
  routing_id      bigint(20)      not null auto_increment    comment '工艺路线ID',
  routing_code    varchar(64)     not null                   comment '工艺路线编码',
  routing_name    varchar(100)    not null                   comment '工艺路线名称',
  material_id     bigint(20)      default 0                  comment '产品物料ID',
  version         varchar(32)     default 'V1'               comment '版本号',
  status          char(1)         default '0'                comment '状态(0草稿 1已审核 2停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (routing_id),
  unique key uk_routing_code (routing_code)
) engine=innodb comment = 'ERP工艺路线主表';

-- 工序明细
drop table if exists erp_routing_operation;
create table erp_routing_operation (
  operation_id    bigint(20)      not null auto_increment    comment '工序ID',
  routing_id      bigint(20)      not null                   comment '工艺路线ID',
  seq_no          int(6)          default 10                 comment '工序号',
  operation_code  varchar(64)     default ''                 comment '工序编码',
  operation_name  varchar(100)    not null                   comment '工序名称',
  wc_id           bigint(20)      default 0                  comment '工作中心ID',
  standard_hours  decimal(18,4)   default 0                  comment '标准工时(小时)',
  yield_rate      decimal(8,4)    default 100                comment '良率(%)',
  inspect_flag    char(1)         default '0'                comment '是否质检点(1是 0否)',
  outsource_flag  char(1)         default '0'                comment '是否委外(1是 0否)',
  create_time     datetime                                   comment '创建时间',
  primary key (operation_id),
  key idx_op_routing (routing_id)
) engine=innodb comment = 'ERP工序明细表';

-- 价格条件(销售价目/采购价格)
drop table if exists erp_price;
create table erp_price (
  price_id        bigint(20)      not null auto_increment    comment '价格ID',
  price_type      char(1)         not null                   comment '价格类型(1销售价 2采购价)',
  material_id     bigint(20)      not null                   comment '物料ID',
  partner_id      bigint(20)      default 0                  comment '客户/供应商ID(0为通用)',
  currency        varchar(16)     default 'CNY'              comment '币种',
  qty_min         decimal(18,4)   default 0                  comment '数量下限',
  price           decimal(18,6)   not null                   comment '价格',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  effect_date     date                                       comment '生效日期',
  expire_date     date                                       comment '失效日期',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (price_id),
  key idx_price_mat (material_id, price_type)
) engine=innodb comment = 'ERP价格条件表';

-- ============================================================================
-- 三、销售管理 (erp-sales)
-- ============================================================================

-- 销售报价单主表
drop table if exists erp_sales_quote;
create table erp_sales_quote (
  quote_id        bigint(20)      not null auto_increment    comment '报价单ID',
  quote_code      varchar(64)     not null                   comment '报价单号',
  customer_id     bigint(20)      not null                   comment '客户ID',
  quote_date      date                                       comment '报价日期',
  valid_date      date                                       comment '有效期至',
  currency        varchar(16)     default 'CNY'              comment '币种',
  amount_tax      decimal(18,2)   default 0                  comment '价税合计',
  amount_notax    decimal(18,2)   default 0                  comment '未税金额',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已转订单 4已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (quote_id),
  unique key uk_quote_code (quote_code)
) engine=innodb comment = 'ERP销售报价单主表';

drop table if exists erp_sales_quote_line;
create table erp_sales_quote_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  quote_id        bigint(20)      not null                   comment '报价单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  qty             decimal(18,4)   default 0                  comment '数量',
  price           decimal(18,6)   default 0                  comment '单价',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  amount          decimal(18,2)   default 0                  comment '金额',
  delivery_date   date                                       comment '交期',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_quote_line (quote_id)
) engine=innodb comment = 'ERP销售报价单明细表';

-- 销售订单主表
drop table if exists erp_sales_order;
create table erp_sales_order (
  order_id        bigint(20)      not null auto_increment    comment '订单ID',
  order_code      varchar(64)     not null                   comment '订单号',
  customer_id     bigint(20)      not null                   comment '客户ID',
  order_date      date                                       comment '订单日期',
  delivery_date   date                                       comment '交货日期',
  currency        varchar(16)     default 'CNY'              comment '币种',
  amount_tax      decimal(18,2)   default 0                  comment '价税合计',
  amount_notax    decimal(18,2)   default 0                  comment '未税金额',
  credit_status   char(1)         default '0'                comment '信用状态(0未检查 1通过 2超限放行)',
  order_type      varchar(16)     default 'MTO'              comment '订单类型(MTO/MTS/ATO)',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3部分发货 4已完成 5已关闭 6已取消)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (order_id),
  unique key uk_sales_order_code (order_code)
) engine=innodb comment = 'ERP销售订单主表';

drop table if exists erp_sales_order_line;
create table erp_sales_order_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  order_id        bigint(20)      not null                   comment '订单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  qty             decimal(18,4)   default 0                  comment '订单数量',
  delivered_qty   decimal(18,4)   default 0                  comment '已发货数量',
  price           decimal(18,6)   default 0                  comment '单价',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  amount          decimal(18,2)   default 0                  comment '金额',
  delivery_date   date                                       comment '交期',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_so_line (order_id)
) engine=innodb comment = 'ERP销售订单明细表';

-- 发货单
drop table if exists erp_delivery;
create table erp_delivery (
  delivery_id     bigint(20)      not null auto_increment    comment '发货单ID',
  delivery_code   varchar(64)     not null                   comment '发货单号',
  order_id        bigint(20)      default 0                  comment '销售订单ID',
  customer_id     bigint(20)      not null                   comment '客户ID',
  warehouse_id    bigint(20)      default 0                  comment '发货仓库ID',
  delivery_date   date                                       comment '发货日期',
  logistics_no    varchar(100)    default ''                 comment '物流单号',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已出库 4已签收)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (delivery_id),
  unique key uk_delivery_code (delivery_code)
) engine=innodb comment = 'ERP销售发货单主表';

drop table if exists erp_delivery_line;
create table erp_delivery_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  delivery_id     bigint(20)      not null                   comment '发货单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  qty             decimal(18,4)   default 0                  comment '发货数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_delivery_line (delivery_id)
) engine=innodb comment = 'ERP销售发货单明细表';

-- 销售退货单
drop table if exists erp_sales_return;
create table erp_sales_return (
  return_id       bigint(20)      not null auto_increment    comment '退货单ID',
  return_code     varchar(64)     not null                   comment '退货单号',
  customer_id     bigint(20)      not null                   comment '客户ID',
  order_id        bigint(20)      default 0                  comment '原订单ID',
  warehouse_id    bigint(20)      default 0                  comment '退货仓库ID',
  return_date     date                                       comment '退货日期',
  return_reason   varchar(255)    default ''                 comment '退货原因',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已入库 4已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (return_id),
  unique key uk_sales_return_code (return_code)
) engine=innodb comment = 'ERP销售退货单主表';

drop table if exists erp_sales_return_line;
create table erp_sales_return_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  return_id       bigint(20)      not null                   comment '退货单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  qty             decimal(18,4)   default 0                  comment '退货数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_return_line (return_id)
) engine=innodb comment = 'ERP销售退货单明细表';

-- ============================================================================
-- 四、采购管理 (erp-purchase)
-- ============================================================================

-- 采购申请
drop table if exists erp_purchase_req;
create table erp_purchase_req (
  req_id          bigint(20)      not null auto_increment    comment '申请ID',
  req_code        varchar(64)     not null                   comment '申请单号',
  req_date        date                                       comment '申请日期',
  req_type        varchar(16)     default '1'                comment '来源(1手工 2MRP 3库存预警 4生产工单)',
  dept_id         bigint(20)      default 0                  comment '申请部门',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已转订单 4已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (req_id),
  unique key uk_req_code (req_code)
) engine=innodb comment = 'ERP采购申请主表';

drop table if exists erp_purchase_req_line;
create table erp_purchase_req_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  req_id          bigint(20)      not null                   comment '申请ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  qty             decimal(18,4)   default 0                  comment '申请数量',
  need_date       date                                       comment '需求日期',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_req_line (req_id)
) engine=innodb comment = 'ERP采购申请明细表';

-- 采购询价
drop table if exists erp_purchase_inquiry;
create table erp_purchase_inquiry (
  inquiry_id      bigint(20)      not null auto_increment    comment '询价ID',
  inquiry_code    varchar(64)     not null                   comment '询价单号',
  inquiry_date    date                                       comment '询价日期',
  status          char(1)         default '0'                comment '状态(0草稿 1询价中 2已定标 3已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (inquiry_id),
  unique key uk_inquiry_code (inquiry_code)
) engine=innodb comment = 'ERP采购询价单主表';

drop table if exists erp_purchase_inquiry_line;
create table erp_purchase_inquiry_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  inquiry_id      bigint(20)      not null                   comment '询价ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  qty             decimal(18,4)   default 0                  comment '询价数量',
  supplier_id     bigint(20)      default 0                  comment '报价供应商ID',
  quote_price     decimal(18,6)   default 0                  comment '报价单价',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  delivery_days   int(6)          default 0                  comment '交货天数',
  selected_flag   char(1)         default '0'                comment '是否中选(1是 0否)',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_inquiry_line (inquiry_id)
) engine=innodb comment = 'ERP采购询价明细表';

-- 采购订单
drop table if exists erp_purchase_order;
create table erp_purchase_order (
  po_id           bigint(20)      not null auto_increment    comment '采购订单ID',
  po_code         varchar(64)     not null                   comment '采购订单号',
  supplier_id     bigint(20)      not null                   comment '供应商ID',
  order_date      date                                       comment '订单日期',
  delivery_date   date                                       comment '交货日期',
  currency        varchar(16)     default 'CNY'              comment '币种',
  amount_tax      decimal(18,2)   default 0                  comment '价税合计',
  amount_notax    decimal(18,2)   default 0                  comment '未税金额',
  match_status    char(1)         default '0'                comment '三单匹配状态(0未匹配 1已匹配 2差异)',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3部分收货 4完全收货 5已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (po_id),
  unique key uk_po_code (po_code)
) engine=innodb comment = 'ERP采购订单主表';

drop table if exists erp_purchase_order_line;
create table erp_purchase_order_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  po_id           bigint(20)      not null                   comment '采购订单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  qty             decimal(18,4)   default 0                  comment '订购数量',
  received_qty    decimal(18,4)   default 0                  comment '已收货数量',
  price           decimal(18,6)   default 0                  comment '单价',
  tax_rate        decimal(8,4)    default 0                  comment '税率(%)',
  amount          decimal(18,2)   default 0                  comment '金额',
  delivery_date   date                                       comment '交期',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_po_line (po_id)
) engine=innodb comment = 'ERP采购订单明细表';

-- 收货单
drop table if exists erp_goods_receipt;
create table erp_goods_receipt (
  receipt_id      bigint(20)      not null auto_increment    comment '收货单ID',
  receipt_code    varchar(64)     not null                   comment '收货单号',
  po_id           bigint(20)      default 0                  comment '采购订单ID',
  supplier_id     bigint(20)      not null                   comment '供应商ID',
  warehouse_id    bigint(20)      default 0                  comment '收货仓库ID',
  receipt_date    date                                       comment '收货日期',
  inspect_status  char(1)         default '0'                comment '检验状态(0待检 1合格 2不合格 3让步接收)',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已入库 4已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (receipt_id),
  unique key uk_receipt_code (receipt_code)
) engine=innodb comment = 'ERP采购收货单主表';

drop table if exists erp_goods_receipt_line;
create table erp_goods_receipt_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  receipt_id      bigint(20)      not null                   comment '收货单ID',
  line_no         int(6)          default 1                  comment '行号',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  qty             decimal(18,4)   default 0                  comment '收货数量',
  qualified_qty   decimal(18,4)   default 0                  comment '合格数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_receipt_line (receipt_id)
) engine=innodb comment = 'ERP采购收货单明细表';

-- ============================================================================
-- 五、库存与仓储 (erp-inventory)
-- ============================================================================

-- 仓库
drop table if exists erp_warehouse;
create table erp_warehouse (
  warehouse_id    bigint(20)      not null auto_increment    comment '仓库ID',
  warehouse_code  varchar(64)     not null                   comment '仓库编码',
  warehouse_name  varchar(100)    not null                   comment '仓库名称',
  warehouse_type  varchar(16)     default '1'                comment '仓库类型(1原料仓 2成品仓 3半成品仓 4委外仓 5不良品仓)',
  dept_id         bigint(20)      default 0                  comment '所属部门',
  address         varchar(255)    default ''                 comment '仓库地址',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (warehouse_id),
  unique key uk_warehouse_code (warehouse_code)
) engine=innodb comment = 'ERP仓库表';

-- 库位
drop table if exists erp_location;
create table erp_location (
  location_id     bigint(20)      not null auto_increment    comment '库位ID',
  warehouse_id    bigint(20)      not null                   comment '仓库ID',
  location_code   varchar(64)     not null                   comment '库位编码',
  location_name   varchar(100)    default ''                 comment '库位名称',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (location_id),
  key idx_loc_wh (warehouse_id)
) engine=innodb comment = 'ERP库位表';

-- 库存余额
drop table if exists erp_inventory;
create table erp_inventory (
  inventory_id    bigint(20)      not null auto_increment    comment '库存ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  warehouse_id    bigint(20)      not null                   comment '仓库ID',
  location_id     bigint(20)      default 0                  comment '库位ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  stock_qty       decimal(18,4)   default 0                  comment '现存数量',
  available_qty   decimal(18,4)   default 0                  comment '可用数量',
  reserved_qty    decimal(18,4)   default 0                  comment '预留数量',
  inspect_qty     decimal(18,4)   default 0                  comment '质检数量',
  frozen_qty      decimal(18,4)   default 0                  comment '冻结数量',
  cost_price      decimal(18,6)   default 0                  comment '结存单价',
  create_time     datetime                                   comment '创建时间',
  update_time     datetime                                   comment '更新时间',
  primary key (inventory_id),
  unique key uk_inventory (material_id, warehouse_id, location_id, batch_no)
) engine=innodb comment = 'ERP库存余额表';

-- 库存流水
drop table if exists erp_inventory_txn;
create table erp_inventory_txn (
  txn_id          bigint(20)      not null auto_increment    comment '流水ID',
  txn_type        varchar(32)     not null                   comment '业务类型(采购入库/销售出库/生产领料/生产入库/调拨/其他)',
  material_id     bigint(20)      not null                   comment '物料ID',
  warehouse_id    bigint(20)      not null                   comment '仓库ID',
  location_id     bigint(20)      default 0                  comment '库位ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  direction       char(1)         not null                   comment '方向(1入库 -1出库)',
  qty             decimal(18,4)   default 0                  comment '数量',
  cost_price      decimal(18,6)   default 0                  comment '单价',
  amount          decimal(18,2)   default 0                  comment '金额',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  source_code     varchar(64)     default ''                 comment '来源单据号',
  oper_by         varchar(64)     default ''                 comment '操作人',
  txn_time        datetime                                   comment '业务时间',
  create_time     datetime                                   comment '创建时间',
  primary key (txn_id),
  key idx_txn_mat (material_id, warehouse_id),
  key idx_txn_source (source_type, source_id)
) engine=innodb comment = 'ERP库存流水表';

-- 库存预留
drop table if exists erp_inventory_reserve;
create table erp_inventory_reserve (
  reserve_id      bigint(20)      not null auto_increment    comment '预留ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  warehouse_id    bigint(20)      default 0                  comment '仓库ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  qty             decimal(18,4)   default 0                  comment '预留数量',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  source_code     varchar(64)     default ''                 comment '来源单据号',
  status          char(1)         default '0'                comment '状态(0预留中 1已释放 2已消耗)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_time     datetime                                   comment '更新时间',
  primary key (reserve_id),
  key idx_reserve_src (source_type, source_id)
) engine=innodb comment = 'ERP库存预留表';

-- 盘点单
drop table if exists erp_stocktake;
create table erp_stocktake (
  stocktake_id    bigint(20)      not null auto_increment    comment '盘点单ID',
  stocktake_code  varchar(64)     not null                   comment '盘点单号',
  warehouse_id    bigint(20)      not null                   comment '仓库ID',
  stocktake_type  varchar(16)     default '1'                comment '盘点类型(1全盘 2循环盘点 3盲盘)',
  stocktake_date  date                                       comment '盘点日期',
  status          char(1)         default '0'                comment '状态(0草稿 1盘点中 2已审核 3已调整 4已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (stocktake_id),
  unique key uk_stocktake_code (stocktake_code)
) engine=innodb comment = 'ERP盘点单主表';

drop table if exists erp_stocktake_line;
create table erp_stocktake_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  stocktake_id    bigint(20)      not null                   comment '盘点单ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  book_qty        decimal(18,4)   default 0                  comment '账面数量',
  actual_qty      decimal(18,4)   default 0                  comment '实盘数量',
  diff_qty        decimal(18,4)   default 0                  comment '差异数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_stocktake_line (stocktake_id)
) engine=innodb comment = 'ERP盘点单明细表';

-- ============================================================================
-- 六、计划管理 (erp-plan)
-- ============================================================================

-- 需求预测
drop table if exists erp_demand_forecast;
create table erp_demand_forecast (
  forecast_id     bigint(20)      not null auto_increment    comment '预测ID',
  forecast_code   varchar(64)     not null                   comment '预测单号',
  forecast_name   varchar(100)    default ''                 comment '预测名称',
  forecast_version varchar(32)    default 'V1'               comment '预测版本',
  period_start    date                                       comment '期间开始',
  period_end      date                                       comment '期间结束',
  status          char(1)         default '0'                comment '状态(0草稿 1已发布 2已冻结 3已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (forecast_id),
  unique key uk_forecast_code (forecast_code)
) engine=innodb comment = 'ERP需求预测主表';

drop table if exists erp_demand_forecast_line;
create table erp_demand_forecast_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  forecast_id     bigint(20)      not null                   comment '预测ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  period_month    varchar(16)     default ''                 comment '期间(YYYY-MM)',
  forecast_qty    decimal(18,4)   default 0                  comment '预测数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_forecast_line (forecast_id)
) engine=innodb comment = 'ERP需求预测明细表';

-- MRP 运算
drop table if exists erp_mrp_run;
create table erp_mrp_run (
  run_id          bigint(20)      not null auto_increment    comment '运算ID',
  run_code        varchar(64)     not null                   comment '运算单号',
  run_date        date                                       comment '运算日期',
  run_type        varchar(16)     default '1'                comment '运算类型(1全量 2净改变)',
  status          char(1)         default '0'                comment '状态(0运算中 1完成 2失败)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (run_id),
  unique key uk_mrp_run_code (run_code)
) engine=innodb comment = 'ERP MRP运算表';

-- MRP 结果
drop table if exists erp_mrp_result;
create table erp_mrp_result (
  result_id       bigint(20)      not null auto_increment    comment '结果ID',
  run_id          bigint(20)      not null                   comment '运算ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  gross_requirement decimal(18,4) default 0                  comment '毛需求',
  available_qty   decimal(18,4)   default 0                  comment '可用库存',
  on_hand_supply  decimal(18,4)   default 0                  comment '在途供应',
  safe_stock      decimal(18,4)   default 0                  comment '安全库存',
  net_requirement decimal(18,4)   default 0                  comment '净需求',
  suggest_type    varchar(16)     default ''                 comment '建议类型(采购/生产)',
  suggest_qty     decimal(18,4)   default 0                  comment '建议数量',
  need_date       date                                       comment '需求日期',
  create_time     datetime                                   comment '创建时间',
  primary key (result_id),
  key idx_mrp_result_run (run_id)
) engine=innodb comment = 'ERP MRP结果表';

-- ============================================================================
-- 七、生产制造 (erp-manufacturing)
-- ============================================================================

drop table if exists erp_production_order;
create table erp_production_order (
  mo_id           bigint(20)      not null auto_increment    comment '工单ID',
  mo_code         varchar(64)     not null                   comment '工单号',
  material_id     bigint(20)      not null                   comment '产品物料ID',
  bom_id          bigint(20)      default 0                  comment 'BOM ID',
  routing_id      bigint(20)      default 0                  comment '工艺路线ID',
  plan_qty        decimal(18,4)   default 0                  comment '计划数量',
  finish_qty      decimal(18,4)   default 0                  comment '完工数量',
  scrap_qty       decimal(18,4)   default 0                  comment '报废数量',
  plan_start_date date                                       comment '计划开工日期',
  plan_end_date   date                                       comment '计划完工日期',
  order_id        bigint(20)      default 0                  comment '关联销售订单ID',
  warehouse_id    bigint(20)      default 0                  comment '完工入库仓库',
  status          char(1)         default '0'                comment '状态(0计划 1已下达 2领料中 3生产中 4待检验 5完工入库 6已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (mo_id),
  unique key uk_mo_code (mo_code)
) engine=innodb comment = 'ERP生产工单表';

drop table if exists erp_production_material;
create table erp_production_material (
  material_req_id bigint(20)      not null auto_increment    comment '用料ID',
  mo_id           bigint(20)      not null                   comment '工单ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  require_qty     decimal(18,4)   default 0                  comment '应领数量',
  issued_qty      decimal(18,4)   default 0                  comment '已领数量',
  create_time     datetime                                   comment '创建时间',
  primary key (material_req_id),
  key idx_pm_mo (mo_id)
) engine=innodb comment = 'ERP工单用料表';

drop table if exists erp_production_picking;
create table erp_production_picking (
  picking_id      bigint(20)      not null auto_increment    comment '领料单ID',
  picking_code    varchar(64)     not null                   comment '领料单号',
  mo_id           bigint(20)      not null                   comment '工单ID',
  picking_type    char(1)         default '1'                comment '类型(1领料 2退料 3补料)',
  warehouse_id    bigint(20)      default 0                  comment '仓库ID',
  picking_date    date                                       comment '领料日期',
  status          char(1)         default '0'                comment '状态(0草稿 1待审核 2已审核 3已出库)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (picking_id),
  unique key uk_picking_code (picking_code)
) engine=innodb comment = 'ERP生产领料单表';

drop table if exists erp_production_picking_line;
create table erp_production_picking_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  picking_id      bigint(20)      not null                   comment '领料单ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  qty             decimal(18,4)   default 0                  comment '数量',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_pick_line (picking_id)
) engine=innodb comment = 'ERP生产领料明细表';

drop table if exists erp_production_report;
create table erp_production_report (
  report_id       bigint(20)      not null auto_increment    comment '报工ID',
  report_code     varchar(64)     not null                   comment '报工单号',
  mo_id           bigint(20)      not null                   comment '工单ID',
  operation_id    bigint(20)      default 0                  comment '工序ID',
  report_date     date                                       comment '报工日期',
  ok_qty          decimal(18,4)   default 0                  comment '合格数量',
  ng_qty          decimal(18,4)   default 0                  comment '不良数量',
  rework_qty      decimal(18,4)   default 0                  comment '返工数量',
  work_hours      decimal(18,2)   default 0                  comment '工时',
  worker          varchar(64)     default ''                 comment '报工人',
  status          char(1)         default '0'                comment '状态(0草稿 1已提交 2已审核)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (report_id),
  unique key uk_report_code (report_code)
) engine=innodb comment = 'ERP工序报工表';

drop table if exists erp_production_completion;
create table erp_production_completion (
  completion_id   bigint(20)      not null auto_increment    comment '完工入库ID',
  completion_code varchar(64)     not null                   comment '完工单号',
  mo_id           bigint(20)      not null                   comment '工单ID',
  warehouse_id    bigint(20)      default 0                  comment '入库仓库ID',
  batch_no        varchar(64)     default ''                 comment '成品批次号',
  qty             decimal(18,4)   default 0                  comment '入库数量',
  completion_date date                                       comment '完工日期',
  status          char(1)         default '0'                comment '状态(0草稿 1待检验 2已入库)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (completion_id),
  unique key uk_completion_code (completion_code)
) engine=innodb comment = 'ERP完工入库表';

drop table if exists erp_production_exception;
create table erp_production_exception (
  exception_id    bigint(20)      not null auto_increment    comment '异常ID',
  exception_code  varchar(64)     not null                   comment '异常单号',
  mo_id           bigint(20)      default 0                  comment '工单ID',
  exception_type  varchar(16)     default '1'                comment '类型(1缺料 2设备停机 3质量异常 4工艺变更 5返工 6报废 7计划延期)',
  exception_desc  varchar(500)    default ''                 comment '异常描述',
  handle_measure  varchar(500)    default ''                 comment '处理措施',
  handle_status   char(1)         default '0'                comment '处理状态(0待处理 1处理中 2已关闭)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (exception_id),
  unique key uk_exception_code (exception_code)
) engine=innodb comment = 'ERP生产异常表';

-- ============================================================================
-- 八、质量管理 (erp-quality)
-- ============================================================================

drop table if exists erp_inspection_plan;
create table erp_inspection_plan (
  plan_id         bigint(20)      not null auto_increment    comment '方案ID',
  plan_code       varchar(64)     not null                   comment '方案编码',
  plan_name       varchar(100)    not null                   comment '方案名称',
  inspect_type    varchar(16)     default 'IQC'              comment '检验类型(IQC/IPQC/FQC/OQC)',
  material_id     bigint(20)      default 0                  comment '物料ID',
  sample_rule     varchar(64)     default ''                 comment '抽样规则',
  status          char(1)         default '0'                comment '状态(0正常 1停用)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (plan_id),
  unique key uk_inspect_plan_code (plan_code)
) engine=innodb comment = 'ERP检验方案表';

drop table if exists erp_inspection_plan_line;
create table erp_inspection_plan_line (
  line_id         bigint(20)      not null auto_increment    comment '明细ID',
  plan_id         bigint(20)      not null                   comment '方案ID',
  item_name       varchar(100)    not null                   comment '检验项目',
  standard_value  varchar(100)    default ''                 comment '规格标准',
  judge_rule      varchar(100)    default ''                 comment '判定规则',
  create_time     datetime                                   comment '创建时间',
  primary key (line_id),
  key idx_ipl_plan (plan_id)
) engine=innodb comment = 'ERP检验方案明细表';

drop table if exists erp_inspection_order;
create table erp_inspection_order (
  inspect_id      bigint(20)      not null auto_increment    comment '检验单ID',
  inspect_code    varchar(64)     not null                   comment '检验单号',
  inspect_type    varchar(16)     default 'IQC'              comment '检验类型(IQC/IPQC/FQC/OQC)',
  plan_id         bigint(20)      default 0                  comment '检验方案ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  inspect_qty     decimal(18,4)   default 0                  comment '检验数量',
  qualified_qty   decimal(18,4)   default 0                  comment '合格数量',
  unqualified_qty decimal(18,4)   default 0                  comment '不合格数量',
  inspect_result  char(1)         default '0'                comment '检验结果(0待检验 1合格 2不合格)',
  inspector       varchar(64)     default ''                 comment '检验员',
  status          char(1)         default '0'                comment '状态(0待检验 1检验中 2已完成 3已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (inspect_id),
  unique key uk_inspect_code (inspect_code)
) engine=innodb comment = 'ERP检验单表';

drop table if exists erp_inspection_result;
create table erp_inspection_result (
  result_id       bigint(20)      not null auto_increment    comment '结果ID',
  inspect_id      bigint(20)      not null                   comment '检验单ID',
  item_name       varchar(100)    not null                   comment '检验项目',
  standard_value  varchar(100)    default ''                 comment '规格标准',
  inspect_value   varchar(100)    default ''                 comment '检验值',
  judge_result    char(1)         default '0'                comment '判定(0待定 1合格 2不合格)',
  create_time     datetime                                   comment '创建时间',
  primary key (result_id),
  key idx_ir_inspect (inspect_id)
) engine=innodb comment = 'ERP检验结果明细表';

drop table if exists erp_defect_disposal;
create table erp_defect_disposal (
  disposal_id     bigint(20)      not null auto_increment    comment '处理ID',
  disposal_code   varchar(64)     not null                   comment '处理单号',
  inspect_id      bigint(20)      default 0                  comment '检验单ID',
  material_id     bigint(20)      default 0                  comment '物料ID',
  batch_no        varchar(64)     default ''                 comment '批次号',
  defect_qty      decimal(18,4)   default 0                  comment '不合格数量',
  disposal_type   varchar(16)     default '1'                comment '处理方式(1隔离 2返工 3返修 4让步接收 5报废 6退货)',
  reason          varchar(500)    default ''                 comment '原因分析',
  handle_status   char(1)         default '0'                comment '状态(0待处理 1处理中 2已关闭)',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (disposal_id),
  unique key uk_disposal_code (disposal_code)
) engine=innodb comment = 'ERP不合格品处理表';

drop table if exists erp_capa;
create table erp_capa (
  capa_id         bigint(20)      not null auto_increment    comment 'CAPA ID',
  capa_code       varchar(64)     not null                   comment 'CAPA单号',
  capa_type       varchar(16)     default '1'                comment '类型(1质量问题 2客户投诉)',
  source_desc     varchar(500)    default ''                 comment '问题描述',
  dept_id         bigint(20)      default 0                  comment '责任部门',
  measure         varchar(500)    default ''                 comment '纠正预防措施',
  due_date        date                                       comment '截止日期',
  verify_result   varchar(255)    default ''                 comment '验证结果',
  status          char(1)         default '0'                comment '状态(0待处理 1处理中 2已验证 3已关闭)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (capa_id),
  unique key uk_capa_code (capa_code)
) engine=innodb comment = 'ERP CAPA与客诉表';

-- ============================================================================
-- 九、财务业务 (erp-finance)
-- ============================================================================

drop table if exists erp_receivable;
create table erp_receivable (
  ar_id           bigint(20)      not null auto_increment    comment '应收ID',
  ar_code         varchar(64)     not null                   comment '应收单号',
  customer_id     bigint(20)      not null                   comment '客户ID',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  biz_date        date                                       comment '业务日期',
  due_date        date                                       comment '到期日期',
  amount          decimal(18,2)   default 0                  comment '应收金额',
  settled_amount  decimal(18,2)   default 0                  comment '已核销金额',
  status          char(1)         default '0'                comment '状态(0未核销 1部分核销 2已核销 3已逾期)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (ar_id),
  unique key uk_ar_code (ar_code)
) engine=innodb comment = 'ERP应收单表';

drop table if exists erp_receipt;
create table erp_receipt (
  receipt_id      bigint(20)      not null auto_increment    comment '收款ID',
  receipt_code    varchar(64)     not null                   comment '收款单号',
  customer_id     bigint(20)      not null                   comment '客户ID',
  receipt_date    date                                       comment '收款日期',
  amount          decimal(18,2)   default 0                  comment '收款金额',
  writeoff_amount decimal(18,2)   default 0                  comment '已核销金额',
  pay_method      varchar(16)     default ''                 comment '收款方式',
  status          char(1)         default '0'                comment '状态(0待核销 1部分核销 2已核销)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (receipt_id),
  unique key uk_fin_receipt_code (receipt_code)
) engine=innodb comment = 'ERP收款单表';

drop table if exists erp_settlement;
create table erp_settlement (
  settle_id       bigint(20)      not null auto_increment    comment '核销ID',
  settle_type     char(1)         not null                   comment '核销类型(1应收 2应付)',
  source_id       bigint(20)      not null                   comment '应收/应付单ID',
  target_id       bigint(20)      not null                   comment '收款/付款单ID',
  amount          decimal(18,2)   default 0                  comment '核销金额',
  settle_date     date                                       comment '核销日期',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  primary key (settle_id),
  key idx_settle_src (settle_type, source_id)
) engine=innodb comment = 'ERP核销记录表';

drop table if exists erp_payable;
create table erp_payable (
  ap_id           bigint(20)      not null auto_increment    comment '应付ID',
  ap_code         varchar(64)     not null                   comment '应付单号',
  supplier_id     bigint(20)      not null                   comment '供应商ID',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  biz_date        date                                       comment '业务日期',
  due_date        date                                       comment '到期日期',
  amount          decimal(18,2)   default 0                  comment '应付金额',
  settled_amount  decimal(18,2)   default 0                  comment '已核销金额',
  status          char(1)         default '0'                comment '状态(0未核销 1部分核销 2已核销 3已逾期)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (ap_id),
  unique key uk_ap_code (ap_code)
) engine=innodb comment = 'ERP应付单表';

drop table if exists erp_payment;
create table erp_payment (
  payment_id      bigint(20)      not null auto_increment    comment '付款ID',
  payment_code    varchar(64)     not null                   comment '付款单号',
  supplier_id     bigint(20)      not null                   comment '供应商ID',
  payment_date    date                                       comment '付款日期',
  amount          decimal(18,2)   default 0                  comment '付款金额',
  writeoff_amount decimal(18,2)   default 0                  comment '已核销金额',
  pay_method      varchar(16)     default ''                 comment '付款方式',
  match_flag      char(1)         default '0'                comment '三单匹配(0未匹配 1已匹配 2授权豁免)',
  status          char(1)         default '0'                comment '状态(0待核销 1部分核销 2已核销)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (payment_id),
  unique key uk_payment_code (payment_code)
) engine=innodb comment = 'ERP付款单表';

drop table if exists erp_invoice;
create table erp_invoice (
  invoice_id      bigint(20)      not null auto_increment    comment '发票ID',
  invoice_code    varchar(64)     not null                   comment '发票单号',
  invoice_no      varchar(64)     default ''                 comment '发票号码',
  invoice_type    varchar(16)     default '1'                comment '类型(1销售发票 2采购发票)',
  partner_id      bigint(20)      default 0                  comment '客户/供应商ID',
  source_type     varchar(64)     default ''                 comment '来源单据类型',
  source_id       bigint(20)      default 0                  comment '来源单据ID',
  invoice_date    date                                       comment '开票日期',
  amount          decimal(18,2)   default 0                  comment '金额',
  tax_amount      decimal(18,2)   default 0                  comment '税额',
  status          char(1)         default '0'                comment '状态(0待开票 1已开具 2已入账 3已作废 4已红冲)',
  del_flag        char(1)         default '0'                comment '删除标志',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (invoice_id),
  unique key uk_invoice_code (invoice_code)
) engine=innodb comment = 'ERP发票表';

drop table if exists erp_voucher;
create table erp_voucher (
  voucher_id      bigint(20)      not null auto_increment    comment '凭证ID',
  voucher_code    varchar(64)     not null                   comment '凭证接口单号',
  biz_type        varchar(64)     default ''                 comment '业务单据类型',
  biz_id          bigint(20)      default 0                  comment '业务单据ID',
  event_type      varchar(64)     default ''                 comment '事件类型',
  amount          decimal(18,2)   default 0                  comment '金额',
  periods         varchar(16)     default ''                 comment '会计期间(YYYY-MM)',
  source_doc      varchar(64)     default ''                 comment '来源单号',
  gen_status      char(1)         default '0'                comment '生成状态(0待生成 1已生成 2失败 3已冲销)',
  retry_times     int(6)          default 0                  comment '重试次数',
  error_msg       varchar(500)    default ''                 comment '失败原因',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  remark          varchar(500)    default ''                 comment '备注',
  primary key (voucher_id),
  unique key uk_voucher_code (voucher_code)
) engine=innodb comment = 'ERP财务凭证接口表';

drop table if exists erp_inventory_cost;
create table erp_inventory_cost (
  cost_id         bigint(20)      not null auto_increment    comment '核算ID',
  material_id     bigint(20)      not null                   comment '物料ID',
  warehouse_id    bigint(20)      default 0                  comment '仓库ID',
  periods         varchar(16)     default ''                 comment '会计期间(YYYY-MM)',
  begin_qty       decimal(18,4)   default 0                  comment '期初数量',
  begin_amount    decimal(18,2)   default 0                  comment '期初金额',
  in_qty          decimal(18,4)   default 0                  comment '入库数量',
  in_amount       decimal(18,2)   default 0                  comment '入库金额',
  out_qty         decimal(18,4)   default 0                  comment '出库数量',
  out_amount      decimal(18,2)   default 0                  comment '出库金额',
  end_qty         decimal(18,4)   default 0                  comment '期末数量',
  end_amount      decimal(18,2)   default 0                  comment '期末金额',
  unit_cost       decimal(18,6)   default 0                  comment '结存单价',
  create_time     datetime                                   comment '创建时间',
  primary key (cost_id),
  key idx_ic_mat (material_id, periods)
) engine=innodb comment = 'ERP存货核算表';

drop table if exists erp_production_cost;
create table erp_production_cost (
  cost_id         bigint(20)      not null auto_increment    comment '成本ID',
  mo_id           bigint(20)      not null                   comment '工单ID',
  periods         varchar(16)     default ''                 comment '会计期间',
  material_cost   decimal(18,2)   default 0                  comment '材料成本',
  labor_cost      decimal(18,2)   default 0                  comment '人工成本',
  overhead_cost   decimal(18,2)   default 0                  comment '制造费用',
  outsource_cost  decimal(18,2)   default 0                  comment '委外加工费',
  total_cost      decimal(18,2)   default 0                  comment '成本合计',
  unit_cost       decimal(18,6)   default 0                  comment '单位成本',
  create_time     datetime                                   comment '创建时间',
  primary key (cost_id),
  key idx_pc_mo (mo_id)
) engine=innodb comment = 'ERP生产成本归集表';
