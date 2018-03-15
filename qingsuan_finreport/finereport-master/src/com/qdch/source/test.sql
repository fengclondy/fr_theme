#sql("test")
--测试代码
select * from hub_commerce_ref_jys t limit 1
#end

#sql("getByJys")
--通过交易所获取信息
select * from hub_commerce_ref_jys t where 1=1 
#if(jys)
and t.jys=#para(jys)
#end
#end