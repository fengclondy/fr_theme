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

#sql("getByLoanCount")
select * from insight_xd_loan_count t limit 1
#end
#end

#sql("getByLoanAmount")
select * from insight_xd_fkamount t where 1=1
#if(cusType)
and t.cusType = #para(cusType) 
#end
#end

#sql("getByYeamount")
select * from insight_xd_yeamount t where 1=1
#if(cusType)
and t.cusType = #para(cusType) 
#end
#end

#sql("getByDBFS")
select * from insight_xd_defrate t where 1=1
#if(dbfs)
and t.dbfs = #para(dbfs) 
#end
#end