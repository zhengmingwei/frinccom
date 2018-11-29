-- select SYSDATE()
select c.ID '商品ID', c.`NAME` '商品名称', nr.TOTAL '浏览量',nr.IP,
nr.CREATE_TIME '开始时间', 
nr.LATELY_TIME '最新浏览时间' ,substring(nr.ADDRESS ,LOCATE( 'region',nr.ADDRESS)+8, LOCATE( 'area_id',nr.ADDRESS)-LOCATE( 'country_id',nr.ADDRESS)+23) '区域'-- ,c.*
 from NUMBER_COMMODITY_READINGS  nr,COMMODITY c
where nr.COMMODITY_ID=c.ID
  -- and ( c.`NAME` like '%建国%' )
   -- and ( nr.ADDRESS not like '%北京%' and  nr.ADDRESS not like '%内网%')
    and (    nr.ADDRESS not like '%内网%')
   -- and ( c.`NAME` like '%建国%' or c.`NAME` like '态立方%'  or c.`NAME` like '111%' )
ORDER BY  nr.LATELY_TIME DESC
-- ORDER BY c.ID DESC


-- 检测     （可单独查询）
-- 商品     （再优化）

-- 企业查询 （爬虫技术实现）



