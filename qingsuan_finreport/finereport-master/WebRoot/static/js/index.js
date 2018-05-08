/**
 * Created by win0 on 2018/02/27.
 */
var windowW0 = $(window).width();
// var bodyScale = 1;
var bodyScale= $("html").css("font-size").slice(0,2)/16;
// console.log("~~~",bodyScale,$("html").css("font-size"));

$(function () {
    // 页面缩放
    windowResize();
    window.onresize = function () {
        windowResize()
    };

    //加载header
    // loadMainHeader();
    //加载主导航
    // loadMainNav();
    /*主导航点击切换*/
    mainNav();
    /*加载tab页面并切换*/
    tabNav();


    $("#mainNav").find(".dropdown-toggle:eq(0)").click()
        .next("ul").children("li:eq(1)").click();
});

function windowResize() {
    var windowW = $(window).width();
    if ($(".pageBox").width() > 960) {
        setTimeout(function () {
            currentPageChartsResize();
        }, 500)

    }
    //console.log("~~~~~~~~~bodyScale" , bodyScale)
}

/*主导航点击切换*/
function mainNav() {
    var $mainNavLi1A = $("#mainNav").find(".dropdown-toggle");
    $mainNavLi1A.click(function () {
        var $ul = $(this).next("ul");
        if ($ul.is(":visible")) {
            //console.log($(this)[0])
            $(this).children(".icon").css("background-image","url(../img/com/i_arrow_down.png)");
            $ul.slideUp(150);
        } else {
            //console.log($(this)[0])
            $(this).children(".icon").css("background-image","url(../img/com/i_arrow_up.png)")
                .parents("li").siblings().find("a .icon").css("background-image","url(../img/com/i_arrow_down.png)")
            $ul.slideDown(150).parent().siblings().children("ul").slideUp(150);
        }
    });
    var $mainNavLi2 = $mainNavLi1A.next("ul").find("li");
    $mainNavLi2.click(function () {
    	var pageName=$(this).attr("data");
        $mainNavLi2.removeClass("active");
        $(this).addClass("active");
        var pageTitle = $(this).text();
        var pageId = $(this).data("id");
        // tabNavActive(pageTitle);
        pageSwitch(pageName);
        windowResize();
        var pageAlreadyExists = false;       
        var navTabsWidth = $("#navTabs").width();
        var _width = 0;
        $("#navTabs").children("ul").children("li").each(function () {
        	//console.log(pageId,$(this).data("id"));
            if (pageId == $(this).data("id")) {
            	
                pageAlreadyExists = true;
            }
            else{
	        	_width += $(this).width();
        	}
        });
        if (pageAlreadyExists) {
            $("#navTabs").children().find("li[data-id='" + pageId + "']")
                .addClass("active").siblings().removeClass("active");
            pageSwitch($(this).data("pagename"));
        } else {
        	if(_width > navTabsWidth){
        		var obj = $("#navTabs li.collapse").children("ul");
        		var tabLiObj = $("#navTabs").children("ul").children("li:nth-child(3)");
        		var tabLiObjId = tabLiObj.data("id");
        		var tabLiObjTitle = tabLiObj.data("title");
        		var html = "<li data-id="+ tabLiObjId +">"+ tabLiObjTitle +"</li>";
        		obj.append(html);
        		var index = obj.children("li").length - 1;
        		setTimeout(function(){
        			navTabsCollapseClick(index);
        		},500);
        		tabLiObj.remove();
        		obj.children("li").each(function(){
        			if(pageId === $(this).data("id")){
        				$(this).remove();
        			}
        		})      		
        	}
            addTabNavBtn(pageId, pageTitle,pageName);
            addPageContent(pageId, pageTitle,pageName);
        }
    });
    $("#mainNav").next(".mainNavFoldCtr").click(function () {
        $("#mainNav").toggleClass("folded")
    })

}

// 添加tab按钮
function addTabNavBtn(pageId, pageTitle,pageName) {
    var $navTabsUl = $("#navTabs").children("ul");
    $navTabsUl.append($navTabsUl.children("li:eq(0)").clone());
    $navTabsUl.children("li").removeClass("active")
        .last().addClass("active")
        .attr({
            "data-id": pageId,
            "data-title": pageTitle,
            "data-pagename": pageName
        })
        .children("a").attr("title", pageTitle)
        .children(".text").text(pageTitle);
}
// 添加页面
function addPageContent(pageId, pageTitle,pageName) {
    var $pageBox = $(".pageBox");
    $pageBox.append($pageBox.children(".pageContent:eq(0)").clone());
    $(".pageBox>.pageContent").removeClass("current")
        .last().addClass("current")
        .attr({
            "id": pageId,
            "data-title": pageTitle,
            "data-pagename": pageName
        })
        .children("iframe").attr("src", pageId);
    windowResize();
    /*setTimeout(function () {
        $(".pageContent#"+pageId+">iframe").contents().find("html").css("font-size", bodyScale * 100 + "%");
        console.log(pageId,$(".pageContent#"+pageId+">iframe").contents().find("html")[0]);
    },400)*/
}

/*tab页面切换和关闭*/
function tabNav() {
    var $navTabUl = $("#navTabs").find("ul");
    $navTabUl.on("click", "li>a", function () {
        $navTabUl.children("li").removeClass("active");
        $(this).parent().addClass("active").mouseleave();
        var selectedId = $navTabUl.find("li.active").data("pagename");
        pageSwitch(selectedId);
        windowResize();
        //切换左侧菜单样式
        $("#mainNav").find("li").removeClass("active");
        $("li[data-id='"+$(this).parent().attr("data-id")+"']").addClass("active");
    });
    $navTabUl.on("click", ".refresh", function () {
        var pageName = $(this).parents("li").attr("data-pagename");
        $("div[data-pagename="+ pageName +"]").children("iframe").attr("src", function () {
            var d = new Date();
            var _src = $(this).attr("src");  
            return _src;
        });
        windowResize();
    })


    $navTabUl.on("click", "li>a>.close", function (e) {
        var selectedId = "";
        if ($(this).parents("li").attr("class").indexOf("active") > -1) {
            $(this).parents("li").removeClass("active");
            // console.log("------",$(this).parents("li").prevAll().length)
            if ($(this).parents("li").prevAll().length > 1) {
                $(this).parents("li").prev().addClass("active");
            } else if ($(this).parents("li").nextAll().length > 0) {
                $(this).parents("li").next().addClass("active");
            } else if ($(this).parents("li").prevAll().length === 1 && $(this).parents("li").nextAll().length === 0) {
                $(this).parents("li").siblings().addClass("active");//只剩主页按钮
            } else {
                $(this).parents("li").addClass("active");
            }
            selectedId = $navTabUl.find("li.active").data("pagename");

            pageSwitch(selectedId);
        }
        if ($(this).parents("li").siblings().length>2) {//如果只剩一个则不能关闭
        	$("div[data-pagename='" + $(this).parents("li").data("pagename")+"']").remove();
        	$(this).parents("li").remove();
        }
        e.stopPropagation();//阻止冒泡
    });
    $(".nav-tabs > ul > li.collapse>button").click(function () {
        $(this).next().slideToggle(200);
    });
    $(".nav-tabs > ul > li.collapse>ul>li").click(function () {
        $(this).parent().slideUp(100);
    });
}

function pageSwitch(selectedId) {
    $("div[data-pagename='" + selectedId+"']").addClass("current").siblings().removeClass("current");
    currentPageChartsResize();
}

function currentPageChartsResize() {
    try {
    	var currentPageCharts = $(".current.pageContent>iframe")[0].contentWindow.allCharts;
        if (currentPageCharts) {
            currentPageCharts.forEach(function (item) {
                item.resize();
            })
        }
	} catch (e) {
	}
}
function navTabsCollapseClick(index){//事件单独绑定
	$(".nav-tabs > ul > li.collapse>ul>li").eq(index).click(function () {
        $(this).parent().slideUp(100);
        var liId = $(this).data("id");
        var liTitle = $(this).text(); 
        if(liId != undefined){
        	addTabNavBtn(liId, liTitle);
            addPageContent(liId, liTitle);
            $(this).remove();
            
            var navTabsWidth = $("#navTabs").width();
	        var _width = 0;
	        $("#navTabs").children("ul").children("li").each(function () {
	        	_width += $(this).width();
	        });
	        console.log(_width);
	        console.log(navTabsWidth);
            if(_width > navTabsWidth){
        		var obj = $("#navTabs li.collapse").children("ul");
        		var tabLiObj = $("#navTabs").children("ul").children("li:nth-child(3)");
        		var tabLiObjId = tabLiObj.data("id");
        		var tabLiObjTitle = tabLiObj.data("title");
        		var html = "<li data-id="+ tabLiObjId +">"+ tabLiObjTitle +"</li>";
        		obj.append(html);
        		var index = obj.children("li").length - 1;
        		setTimeout(function(){
        			navTabsCollapseClick(index);
        		},500);
        		tabLiObj.remove();    		
        	}
            
        }
    });
}




