var totalPage,totalNum,pageNumber;

//第一页
$('#pagehome').on('click',function(){
    getTableBody()
})
//最后一页
$('#pageend').on('click',function(){
    getTableBody(totalPage)
})
//上一页
$('#pageprev').on('click',function(){
    if(pageNumber==1){
        alert("已经是第一页了")
    }else{
        getTableBody(pageNumber-1)
    }

})
//下一页
$('#pagenext').on('click',function(){
    console.log(pageNumber,totalPage)
    if(pageNumber== totalPage){
        alert("已经是最后一页了")
    }else{
        getTableBody(pageNumber+1)
    }

})
$('#pageCurrent').bind('keypress',function(event){
    console.log(11,event.keyCode)
    if(event.keyCode == 13){
        getTableBody($(this).val(),30)
    }
});

