-- select SYSDATE()
select c.ID '商品ID', c.`NAME` '商品名称', nr.TOTAL '浏览量',nr.IP,
nr.CREATE_TIME '开始时间', 
nr.LATELY_TIME '最新浏览时间' ,

REPLACE(
REPLACE(
REPLACE(
substring(nr.ADDRESS ,LOCATE( 'region',nr.ADDRESS)+9, LOCATE( 'area_id',nr.ADDRESS)-LOCATE( 'country_id',nr.ADDRESS)+23),
'","city":"','-'),
'","county":"XX","isp":"','-' ),
'",','') '区域'-- ,c.*
 

from NUMBER_COMMODITY_READINGS  nr,COMMODITY c
where nr.COMMODITY_ID=c.ID
  -- and ( c.`NAME` like '%建国%' )
   -- and ( nr.ADDRESS not like '%北京%' and  nr.ADDRESS not like '%内网%')
    and (    nr.ADDRESS not like '%内网%')
   -- and ( c.`NAME` like '%建国%' or c.`NAME` like '态立方%'  or c.`NAME` like '111%' )
ORDER BY  nr.LATELY_TIME DESC
-- ORDER BY c.ID DESC

-- -----------------------------------------------------------------------

/*


-- select SYSDATE()
select c.ID '商品ID', 
c.`NAME` '商品名称', 
nr.TOTAL '浏览量',
nr.IP,
-- nr.CREATE_TIME '开始时间', 
 DATE_FORMAT(nr.LATELY_TIME, '%Y-%m-%d')  '最新浏览时间' ,substring(nr.ADDRESS ,LOCATE( 'region',nr.ADDRESS)+8, LOCATE( 'area_id',nr.ADDRESS)-LOCATE( 'country_id',nr.ADDRESS)+23) '区域'-- ,c.*
 ,COUNT(*) 
 from NUMBER_COMMODITY_READINGS  nr,COMMODITY c
where nr.COMMODITY_ID=c.ID
  -- and ( c.`NAME` like '%建国%' )
   -- and ( nr.ADDRESS not like '%北京%' and  nr.ADDRESS not like '%内网%')
    and (    nr.ADDRESS not like '%内网%')
   -- and ( c.`NAME` like '%建国%' or c.`NAME` like '态立方%'  or c.`NAME` like '111%' )
GROUP BY  c.ID , c.`NAME` , nr.TOTAL ,nr.IP, DATE_FORMAT(nr.LATELY_TIME, '%Y-%m-%d') ,substring(nr.ADDRESS ,LOCATE( 'region',nr.ADDRESS)+8, LOCATE( 'area_id',nr.ADDRESS)-LOCATE( 'country_id',nr.ADDRESS)+23) 

ORDER BY   DATE_FORMAT(nr.LATELY_TIME, '%Y-%m-%d')  DESC
-- ORDER BY c.ID DESC









-- 检测     （可单独查询）
-- 商品     （再优化）

-- 企业查询 （爬虫技术实现）



*/














-- 检测     （可单独查询）
-- 商品     （再优化）

-- 企业查询 （爬虫技术实现）



