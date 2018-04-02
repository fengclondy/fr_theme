#sql("logDetail")
select * from hub_commerce_db_log t where 1=1
#if(id)
and t.id = #para(id) 
#end
#end

#sql("logIn")
--in语句
select * from hub_commerce_db_log t where 
t.id in (
	#for(id : map)
	#(id.value)
	#if(!for.last)
	    ,
	#end
	#end
)
#end

#sql("logIn2")
--in语句
select * from hub_commerce_db_log t where 
t.id in (
	#for(id : list)
	#(id)
	#if(!for.last)
	    ,
	#end
	#end
)
#end