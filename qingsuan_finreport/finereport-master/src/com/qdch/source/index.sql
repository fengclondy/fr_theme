#sql("test")
--测试代码
select * from hub_commerce_ref_jys t limit 1
#end

#sql("getByLoanCount")
--通过交易所获取信息
select t.jyscmc,sum(t.fvalue) from insight_xd_loan_count t group by jyscmc order by t.jyscmc
#end

#sql("getByDBFS")
select substr(t.vday,0,7) as vmonth,t.dbfs,sum(t.loantotal) from insight_xd_defrate_test t where substr(t.vday,0,7) is not null GROUP BY vmonth,dbfs ORDER BY dbfs,substr(t.vday,0,7)
#end