# 制造企业 ERP 系统（基于若依 RuoYi-Vue 3.9.2）

依据《制造企业 ERP 系统需求规格说明书（SRS V1.0）》实现的多模块 ERP 后端与前端页面，基于若依 RuoYi-Vue 框架
（Spring Boot 4.1.0 / Java 17 / MyBatis / MySQL / Redis，前端 Vue2 + Element UI）。

## 一、技术栈

| 层 | 技术 |
|---|---|
| 后端 | Spring Boot 4.1.0、Java 17、MyBatis、Druid、Redis、JWT、Quartz |
| 数据库 | MySQL 8.x |
| 前端 | Vue2 + Element UI（若依 ruoyi-ui 规范） |

## 二、工程结构（Maven 多模块）

```
erp (父工程 com.ruoyi:erp)
├── erp-common        通用工具（来自若依）
├── erp-framework     框架核心（安全、数据源、AOP，来自若依）
├── erp-system        系统模块（组织/用户/角色/菜单/字典，来自若依）
├── erp-quartz        定时任务（来自若依）
├── erp-generator     代码生成（来自若依）
├── erp-platform      基础平台扩展：编码规则、工作流、附件、字段审计
├── erp-mdm           主数据：物料/分类/单位/客户/供应商/工作中心/BOM/工艺路线/价格
├── erp-sales         销售：报价、订单、发货、退货、信用、交期
├── erp-purchase      采购：申请、询价、订单、收货、三单匹配
├── erp-inventory     库存：仓库、库位、余额、流水、预留、盘点、批次追溯
├── erp-plan          计划：需求预测、MPS/MRP、产能
├── erp-manufacturing 生产：工单、用料、领料、报工、完工、异常
├── erp-quality       质量：检验方案、检验单、不合格品、CAPA
├── erp-finance       财务：应收、应付、收款、付款、发票、凭证接口、存货/生产成本
├── erp-bi            报表：经营驾驶舱、订单交付、库存库龄、供应商交付、生产达成
├── erp-admin         启动模块（入口 ErpApplication）
├── erp-ui            前端页面（api + views，需挂载到若依 ruoyi-ui 使用，见第五节）
├── sql               数据库脚本
└── bin               启动脚本（来自若依）
```

## 三、环境要求

- **JDK 17**（注意：若依 3.9.2 要求 Java 17，JDK 8 无法编译）
- Maven 3.6.3+
- MySQL 8.x
- Redis 6+
- Node.js 18+（前端）

## 四、数据库初始化

依次执行 `sql` 目录下脚本（同一数据库，默认库名 `erp`）：

```sql
CREATE DATABASE IF NOT EXISTS `erp` DEFAULT CHARACTER SET utf8mb4;
-- 1) 若依基础表（系统/权限/菜单/字典/定时任务基础数据）
--    sql/ry_20260417.sql
-- 2) ERP 业务表（本系统新增，约 60 张）
--    sql/erp_schema.sql
-- 3) ERP 菜单与权限（目录/菜单/按钮，ID 3000-3333）
--    sql/erp_menu.sql
-- 4) 若依定时任务表（如启用 Quartz）
--    sql/quartz.sql
```

数据库连接配置见 `erp-admin/src/main/resources/application-druid.yml`（默认 `localhost:3306/erp`，用户名 `root`）。
Redis 配置见 `erp-admin/src/main/resources/application.yml`。

## 五、启动

### 后端
```bash
# 在 erp 根目录
mvn clean package -DskipTests
# 运行
java -jar erp-admin/target/erp-admin.jar
# 或使用 bin/run.bat（Windows）
```
默认端口 8080，接口文档 `/swagger-ui.html`，默认账号 `admin / admin123`。

### 前端
`erp-ui` 目录仅包含本系统新增的 `src/api/**` 与 `src/views/**` 页面文件。请将其合并进标准若依 ruoyi-ui 工程后运行：

```bash
# 在 ruoyi-ui 工程中，把本仓库 erp-ui/src 下的 api、views 目录合并进去
npm install --registry=https://registry.npmmirror.com
npm run dev            # 开发
npm run build:prod     # 打包，产物 dist 由 nginx 托管
```
页面路由由后端菜单动态生成（`sql/erp_menu.sql` 已写入 10 个目录 / 54 个菜单 / 270 个按钮权限），
前端组件路径与菜单 `component` 字段一一对应（如 `mdm/material/index`）。

## 六、SRS 需求与模块对应

| SRS 章节 | 需求编号 | 对应模块 | 状态 |
|---|---|---|---|
| 5.1 基础平台 | FR-PLT-001~005 | erp-system + erp-platform（编码规则/工作流/附件/审计） | 已实现（工作流为简化版） |
| 5.2 主数据 | FR-MDM-001~005 | erp-mdm | 已实现 |
| 5.3 销售 | FR-SAL-001~006 | erp-sales（报价/订单/发货/退货）；信用与交期检查留待业务规则补充 | 已实现（CRUD 主链路） |
| 5.4 采购 | FR-PUR-001~005 | erp-purchase（申请/询价/订单/收货）；三单匹配字段已预留 | 已实现（CRUD 主链路） |
| 5.5 计划 | FR-PLN-001~005 | erp-plan（需求预测/MRP 运算与结果） | 已实现（算法待增强） |
| 5.6 生产 | FR-MFG-001~005 | erp-manufacturing（工单/用料/领料/报工/完工/异常） | 已实现（CRUD 主链路） |
| 5.7 库存 | FR-INV-001~005 | erp-inventory（余额/流水/预留/盘点/批次） | 已实现 |
| 5.8 质量 | FR-QA-001~004 | erp-quality（方案/检验单/不合格/CAPA） | 已实现 |
| 5.9 财务 | FR-FIN-001~005 | erp-finance（应收/应付/收款/付款/发票/凭证接口/存货与生产成本） | 已实现（核算逻辑待增强） |
| 5.10 报表 | FR-BI-001~003 | erp-bi（驾驶舱与专题报表） | 已实现（基础版） |

## 七、说明与后续

- 每个业务单据均已按若依规范实现：domain / mapper(接口+XML) / service(接口+实现) / controller，
  并配套前端 api 与 views 页面、菜单与按钮权限。主从单据（订单/报价/领料等）支持表头+明细级联保存。
- 以下属于后续增强项（已在库表中预留字段）：
  - MRP 净需求运算的完整展开算法、产能负荷模拟；
  - 库存扣减/预留/成本核算的自动过账与库存流水联动；
  - 信用检查、可承诺交期（ATP）计算；
  - 工作流引擎对接（当前为审批实例/节点简化模型）；
  - 与 CRM/MES/WMS/OA/银行/税务/BI 的接口（接口表与字段已预留：见 `erp_*` 的 source_type/source_id/source_code 及 `erp_voucher`）。
- 采用软删除：表头单据 `del_flag='2'`，明细行物理删除；无 del_flag 的流水/核算类表为物理删除。
