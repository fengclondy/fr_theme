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


    $("#mainNav").find(".dropdown-toggle:eq(1)").click()
        .next("ul").children("li:eq(3)").click();
});

function windowResize() {
    var windowW = $(window).width();
    if ($(".pageBox").width() > 960) {
        setTimeout(function () {
            currentPageChartsResize();
        }, 500)

    }
    console.log("~~~~~~~~~bodyScale" , bodyScale)
}

/*主导航点击切换*/
function mainNav() {
    var $mainNavLi1A = $("#mainNav").find(".dropdown-toggle");
    $mainNavLi1A.click(function () {
        var $ul = $(this).next("ul");
        if ($ul.is(":visible")) {
            console.log($(this)[0])
            $(this).children(".icon").css("background-image","url(../img/com/i_arrow_down.png)");
            $ul.slideUp(150);
        } else {
            console.log($(this)[0])
            $(this).children(".icon").css("background-image","url(../img/com/i_arrow_up.png)")
                .parents("li").siblings().find("a .icon").css("background-image","url(../img/com/i_arrow_down.png)")
            $ul.slideDown(150).parent().siblings().children("ul").slideUp(150);
        }
    });
    var $mainNavLi2 = $mainNavLi1A.next("ul").find("li");
    $mainNavLi2.click(function () {
        $mainNavLi2.removeClass("active");
        $(this).addClass("active");
        var pageTitle = $(this).text();
        var pageId = $(this).data("id");
        // tabNavActive(pageTitle);
        var pageAlreadyExists = false;
        $("#navTabs").children("ul").children("li").each(function () {
            if (pageId === $(this).data("id")) {
                pageAlreadyExists = true;
            }
        });
        if (pageAlreadyExists) {
            $("#navTabs").children().find("li[data-id='" + pageId + "']")
                .addClass("active").siblings().removeClass("active");
            pageSwitch(pageId);
        } else {
            addTabNavBtn(pageId, pageTitle);
            addPageContent(pageId, pageTitle);
        }
    });
    function addTabBtnAndPage() {

    }
    $("#mainNav").next(".mainNavFoldCtr").click(function () {
        $("#mainNav").toggleClass("folded")
    })
}


//添加tab按钮
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

function addTabNavBtnFromPage(pageId, pageTitle) {
    var $navTabsUl = $("#navTabs").children("ul");
    $navTabsUl.append($navTabsUl.children("li:eq(0)").clone());
    $navTabsUl.children("li").removeClass("active")
        .last().addClass("active")
        .attr({
            "data-id": pageId,
            "data-title": pageTitle
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
        var id = $(this).parents("li").data("id");
        $("#" + id).children("iframe").attr("src", function () {
            var d = new Date();
            console.log($(this).attr("src") + "?_update=" + d.getTime())
            return id + ".html?_update=" + d.getTime();
        });
        windowResize();
    })


    $navTabUl.on("click", "li>a>.close", function (e) {
        var selectedId = "00_01home";
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
        $("div[data-pagename='" + $(this).parents("li").data("pagename")+"']").remove();
        // if ($navTabUl.find("li").length !== 1) {//如果只剩一个则不能关闭
        $(this).parents("li").remove();
        // }
        e.stopPropagation();//阻止冒泡
    });
    $(".nav-tabs > ul > li.collapse>button").click(function () {
        $(this).next().slideToggle(200)
    });
    $(".nav-tabs > ul > li.collapse>ul>li").click(function () {
        $(this).parent().slideUp(100);
    });
}

function pageSwitch(selectedId) {
    $("#" + selectedId).addClass("current").siblings().removeClass("current");
    currentPageChartsResize();
}

function currentPageChartsResize() {
    var currentPageCharts = $(".current.pageContent>iframe")[0].contentWindow.allCharts;
    if (currentPageCharts) {
        currentPageCharts.forEach(function (item) {
            item.resize();
        })
    }
}
/* 全屏按钮*/
function FullScreen(el){
    var isFullscreen=document.fullScreen||document.mozFullScreen||document.webkitIsFullScreen;
    if(!isFullscreen){//进入全屏,多重短路表达式
        (el.requestFullscreen&&el.requestFullscreen())||
        (el.mozRequestFullScreen&&el.mozRequestFullScreen())||
        (el.webkitRequestFullscreen&&el.webkitRequestFullscreen())||(el.msRequestFullscreen&&el.msRequestFullscreen());

    }else{	//退出全屏,三目运算符
        document.exitFullscreen?document.exitFullscreen():
            document.mozCancelFullScreen?document.mozCancelFullScreen():
                document.webkitExitFullscreen?document.webkitExitFullscreen():'';
    }
}
function toggleFullScreen(e){
    var el=e.srcElement||e.target;//target兼容Firefox
    el.innerHTML=='全屏'?el.innerHTML='退出全屏':el.innerHTML='全屏';
    FullScreen(el);
}

