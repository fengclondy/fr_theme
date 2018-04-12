(function ($) {
	var topHeight=60;
    FS.TMP = {
        mwidth: 230,
        cheight: 0,
        initSize: function () {
            $("#fs-frame-body").css({ "z-index": "20px", "top": "0","background-color":"#323a6e" });
            $("#fs-frame-header").css({ "z-index": "25px" ,"position":"relitive","float":"right"});
            $("#fs-frame-search").css({ "top": "15px", "right": "8px", "width": "200px" });
            //$(".fs-favorite-combo,.fs-admin-combo").css({ "left": "0", "top": "0" });
            $(".fs-navibar-item").css({ "height": topHeight+"px", "line-height": topHeight+"px" });
            FS.TMP.cheight = $("#fs-frame-body").height();
            //$("#fs-frame-menu").append('<div class="fs-menu-hide"></div>');
           // $("#fs-frame-content").append('<div class="fs-menu-show"></div>');
            $(".fs-menu-show").hide();
            //$("#fs-frame-banner,#fs-navi-message").remove();
            $("#fs-frame-body").css("top", "0px").height(FS.TMP.cheight + topHeight);
            $("#fs-frame-menu").height(FS.TMP.cheight).css({"background-color":"#323a6e"});
           // $("#fs-frame-content").height(FS.TMP.cheight + topHeight);
            $(".fs-tab-content").height(FS.TMP.cheight + topHeight/2);
            $(".fs-tab-content").css({"top":"0px"});
            $("#fs-frame-content").css("top",topHeight+"px");
            $("#fs-navi-admin").css({"background-color":"#323a6e"});
            $("#fs-navi-favorite").css({"background-color":"#323a6e"});
            $(".fs-tag-pane").remove();
            $("#fs-frame-search").remove();
            $("#fs-frame-reg").remove();
        }
    };
    var _initFrameNavigationBar = function(){
        var nav = {
            report:{res:[],url:FR.servletURL + "?op=fs_main&cmd=module_getrootreports",res:[]},
            module:{res:[],url:FR.servletURL + "?op=fs_main&cmd=getmoduleitems",res:[]}
        };
        var ajax1 = $.post(nav.report.url,{id: -1}).done(function(data){
            nav.report.res = $.parseJSON(data);
            if(FS.THEME.config4MenuTree.insertNodes) {
                $.each(FS.THEME.config4MenuTree.insertNodes, function(p,q){
                    if($.isFunction(q)){
                        var o=q.apply();
                        o&&nav.report.res.push(o);
                    }else{
                        nav.report.res.push(q);
                    }
                });
            }
        });
        var ajax2 = $.post(nav.module.url,{id: 1}).done(function(data){
            var module = $.parseJSON(data);
            if(module && module.hasChildren){
                nav.module.res.push(module);
            }
        });
        $.when(ajax1,ajax2).done(function(){
            var nodes = nav.report.res.concat(nav.module.res);
            _createFrameNavigationBar(nodes);//这个是关键
        });
    };
    //生成菜单
    var _createFrameNavigationBar = function(nodes){
    	var htmls='';
    	var firstName;
    	//遍历一级父类
    	htmls+='<ul class="ful">';
    	$.each(nodes,function(index,node){
    		if(index==0){
    			firstName=node.text;
    		}
    		htmls+='<li id="first_menu_id_'+node.id+'" haschildren="'+node.hasChildren+'"  data="'+node.id+'" pid="first_menu_id_'+node.parentId+'"><i class="iconfont"></i><span>'+node.text+'</span></li>';
    	});
    	htmls+='</ul>';
    	//遍历二级三级
    	var thirdArr=[];
    	$.each(nodes,function(index,node){
    		htmls+='<ul class="secondM" id="senond_ul_'+node.id+'">';
    		htmls+=' <li class="resBox"><i class="iconfont"></i><span>'+node.text+'</span></li>';
    		if(node.hasChildren||node.hasChildren=="true"){
    			//二级
    			$.each(node.ChildNodes,function(ind2,it2){
    				if(ind2==0){
    					htmls+='<li  id="senond_menu_id_'+it2.id+'" haschildren="'+it2.hasChildren+'" data="'+it2.id+'" pid="senond_menu_id_'+it2.parentId+'" class="activeLi"><i class="iconfont"></i><span>'+it2.text+'</span></li>';
    				}else{
    					htmls+='<li  id="senond_menu_id_'+it2.id+'" haschildren="'+it2.hasChildren+'" data="'+it2.id+'"  pid="senond_menu_id_'+it2.parentId+'"><i class="iconfont"></i><span>'+it2.text+'</span></li>';
    				}
    				//三级
    				if(it2.hasChildren||it2.hasChildren=="true"){
    					var third='<li class="threeM" style="display:none" id="third_ul_'+it2.id+'"><ul>';
    					$.each(it2.ChildNodes,function(ind3,it3){
    						//菜单明细
    						_menu_detail(it3,"third_menu_id_"+it3.id);
    						third+='<li   id="third_menu_id_'+it3.id+'" haschildren="'+it3.hasChildren+'" data="'+it3.id+'" pid="third_menu_id_'+it3.parentId+'">'+it3.text+'</li>';
    					});
    					third+='</ul></li>';
    					thirdArr.push(third);
    				}else{
    					//二级不需要查询访问路径
    					//_menu_detail(it2,"senond_menu_id_"+it2.id);
    				}
    			});
    			//拼接三级
    			//htmls=htmls+' <li class="threeM" >'+thirdArr.join('')+'</li>';
    		}else{
    			_menu_detail(node,"first_menu_id_"+node.id);
    		}
    		htmls+='</ul>';
    	});
    	$(".slimScrollDiv").addClass("leftNav");
    	$(".fs-frame-scroll").prepend('<div class="logo"><a href=""></a></div>');//图标logo位置
    	$(".fs-menutree").css("width","100%").addClass("mine_menu").append(htmls);
    	//生成上方菜单
    	
    	var topHtml='';
    	topHtml+='<div id="mine_title">';
    	topHtml+=' <span class="myTitle" style="position: absolute;top: 1em;left: 55%;line-height: 0.5rem;font-size: 2em;">'+firstName+'</span><nav aria-label="breadcrumb" style="left: 22%;">';
    	topHtml+=' <ol class="breadcrumb"><li class="breadcrumb-item">'+firstName+'</li>';
    	topHtml+='<li class="breadcrumb-item"></li><li class="breadcrumb-item"></li><li class="breadcrumb-item"></li></ol>';
    	topHtml+='</nav>';
    	topHtml+='</div>';
    	
    	$("#fs-frame-header").append(topHtml);
    	//加入三级目录
    	$("#fs-frame-body").append(thirdArr.join(''));
    	//生成钟表
    	_create_clock();
    	Index.init();
    	
    };
    //获取菜单详情
    var _menu_detail=function(node,ids){
    	if(node.hasChildren=="false"||node.hasChildren==undefined){
    		//没有子节点
    		var menuId=node.id.substr(1);
    		$.post("/WebReport/getMenuDetail",{menuId: menuId},function(data){
    			var m=eval("("+data.menu+")");
    			$("#"+ids).attr("reportletpath",m.reportletpath);
    		});
    	}
    }
    var _create_clock=function(){
    	var htmls='<div class="timer" id="clock_box"><canvas id="clock" width="120" height="120">您的浏览器不支持canvas标签，无法看到时钟';
    	htmls+='</canvas><span class="rq"></span><span class="xq"></span></div>';
    	$(".fs-menutree").parent().append(htmls) ;
    	clock=document.getElementById('clock');
    	cxt=clock.getContext('2d');
    	console.log(clock,cxt)
    	drawClock();    //1000毫秒前要显示
        //使用setInterval(代码,毫秒时间)  让时钟动起来
        setInterval(drawClock,1000);
    }
    FS.THEME = $.extend(true, FS.THEME, {
    	  config4navigation: {
              onBeforeInit:function(){
                  //FR.i18n['FS-Frame-Enter_Entry_Name']=FR.i18n['FS-FRAME-SEARCH-PLACEHOLDER'];
              },
              onAfterInit: function () {
            	 // $(".fs-menutree").html('');
              }
          },
        config4MenuTree: {
            onAfterNodeExpand: function (node, $node, $li) {
                if (node.level >= 1) {
                }
            },
            onAfterNodeCollapse: function (node, $node, $li) {
                if (node.level >= 1) {
                    $li.children('ul').css({
                        'background': 'none'
                    });
                }
                $node.css('display',"none");
            },
            onAfterNodeCreate: function (node, $node, $li) {
            	$node.addClass('data-id-'+node.id);
            	$(".fs-menutree>ul:not(.secondM)").hide();
            },
            onAfterInit: function (element) {
            	$(".data-id-1").trigger("click");
            	_initFrameNavigationBar();
                FS.TMP.initSize();
                $(window).resize(function () { setTimeout(function () { FS.TMP.initSize(); }, 800) });
            }
        },
        config4frame: {
            resizable: false,
            west: {
                width: FS.TMP.mwidth //瀹藉害
            }
        },
        config4tabPane: {
            style: 'alpha',
            isCollapsible: false,
            hasHomepageBtn: true
        }
    });
    
    
    
    
    
    
    /**
     * 自定义
     */
    var Index = {
    	    init: function () {
    	        this.time();
    	        this.menuAction();
    	        this.repairStyle();
    	    },
    	    repairStyle:function(){
    	    	/*$(".fs_ltabpane_content_selected.fr-absolutelayout.ui-state-enabled").children()
    	    	.eq(0).css({
    	    		width: "30%",
    	        	height: "100%"
    	    		
	    		}).next().css({
	    			width: "66%",
		    	    height: "100%",
		    	    left: "33%"
	    		});*/
    	    	$("#fs-frame-header").css("background","#323a6e");
    	    	$("#mine_title nav").css("left","12%");
    	    	//目录管理中让滚动条恢复到顶部
    	    	$("[id^=senond_menu_id]").click(function(){
	    			setTimeout(function(){
	    				$("body").scrollTop(0);
	    			},100)
	    		})
    	    		
    	    },
    	    clock: function () {
    	        var clock = new Clock('#clock', {
    	            size: '104',
//    				bgColor:'#333'
    	        })
    	    },
    	    time: function () {
    	        var month = Number(new Date().getMonth()) + 1;
    	        var weekday = ["日", "一", "二", "三", "四", "五", "六"];
    	        var FormatRQ = new Date().getFullYear() + "-" + month + "-" + new Date().getDate();
    	        var FormatXQ = "星期"+weekday[new Date().getDay()];
    	        $(".rq").html(FormatRQ);
    	        $(".xq").html(FormatXQ);
    	    },

    	    menuAction: function () {//左侧目录和顶部面包屑导航
    	        var $breadcrumbItem = $("ol.breadcrumb>li");
    	        //一级菜单样式
    	        $(".ful>li").on('mouseover',function () {
    	            $(this).addClass('activeLi').siblings('li').removeClass('activeLi');
    	        });
    	        //打开二级菜单
    	        $(".ful>li").click(function () {
    	           // console.log($(this).attr("id"));
    	            $(".ful").hide();
    	            //有下一级
    	            $("#senond_ul_"+$(this).attr("data")).show().siblings().hide();
    	            //钟表位置
    	            if($("#senond_ul_"+$(this).attr("data")).children().length>8){
    	            	$(".timer").addClass("relative")
    	            }else{
    	            	$(".timer").removeClass("relative")
    	            }
    	           // $(".leftNav>.mine_menu").children("ul").eq($(this).index() + 1).show().siblings().hide();
    	            $breadcrumbItem.eq(1).text($(this).children().text());
    	            if($(this).attr("haschildren")=="false"||$(this).attr("haschildren")=="undefined"){
    	            	$("iframe").attr("src","/WebReport/ReportServer?formlet="+encodeURIComponent($(this).attr("reportletpath")));
    	            }
    	        });
    	    
    	        //返回一级菜单
    	        $(".resBox").click(function () {
    	            $(".ful").show().siblings().hide();
    	            $(".threeM").removeClass("activeLi").hide();
    	            $breadcrumbItem.not(":first").empty();
    	        });
    	        //撑开三级菜单的背景
    	        $(".threeM>ul").css("height", function () {
    	            var n = $(this).children().length;
    	            return "auto";
    	            //return n <= 5 ? $(this).children().length * 4.75 + .2 + "rem": "24rem";
    	        });
    	        //打开三级菜单
    	        $(".secondM>li:not(.resBox,.threeM)").on('mouseenter',function () {
    	            //$(this).addClass("activeLi").nextAll(".threeM").css("top", +4.5 * $(this).index() + .2 + "rem");
    	            $(".threeM").removeClass("activeLi").hide();
    	            $("#third_ul_"+$(this).attr("data")).addClass("activeLi").css("top", +4.5 * $(this).index() + 8.2 + "rem").show();
    	            $("#third_ul_"+$(this).attr("data")).find("ul").show();
    	            $(this).siblings().removeClass("activeLi");
    	        });
    	        //隐藏三级菜单
    	        $(".threeM>ul").mouseleave(function () {
    	        	$(".threeM").removeClass("activeLi").hide();
    	            $(this).hide()
    	        });
    	        //点击三级菜单
    	        $(".threeM>ul>li").click(function () {
    	            var textMenu2 = $(this).parents(".secondM").children().eq($(this).parent().index() + 1).text();
    	            $breadcrumbItem.eq(2).text(textMenu2)
    	                .end().eq(3).text($(this).text());
    	            $(".myTitle").text($(this).text());
    	           // console.log($(this).attr("reportletpath"));
    	            //系统设置切换后会多出额外代码 并且iframe隐藏 在切换其他菜单需要重置
    	            $(".fs_design_container").remove();
    	            $("iframe").show();
    	            $("iframe").attr("src","/WebReport/ReportServer?formlet="+encodeURIComponent($(this).attr("reportletpath")));
    	            $(".threeM").removeClass("activeLi").hide();
    	        });
    	        
    	        //点击二级
    	        $(".secondM>li").click(function () {
    	        	 if($(this).attr("haschildren")=="false"||$(this).attr("haschildren")=="undefined"){
    	        		 $(".data-id-"+$(this).attr("data")).trigger("click");
     	            	//$("iframe").attr("src","/WebReport/ReportServer?formlet="+encodeURIComponent($(this).attr("reportletpath")));
    	        		/* setTimeout(function () {
    	        			 $("#fs-frame-content").css("top","0").css("top","60px"); 
    	        			 var x=$("#fs-frame-banner").html();
    	        			 $("#fs-frame-banner").html('').html(x);
    	        			 $("#fs-frame-menu").height(FS.TMP.cheight).css({"background-color":"#323a6e"});
    	        			 FS.TMP.initSize();
    	        		}, 800) ;*/
    	        		 FS.TMP.initSize();
     	            }
    	        });
    	        
    	        //iframe切换
    	       /* $("li[haschildren='undefined']").click(function(){
      	        	 $("iframe").attr("src",$(this).attr("reportletpath"));
       	        });*/
    	    }
    	};



    	//钟表
   
    var clock;
    var cxt;
    function drawClock(){
        cxt.clearRect(0,0,120,120); //清除画布区域
        var now =new Date();
        var sec=now.getSeconds();
        var min=now.getMinutes();
        var hour=now.getHours();

        hour=hour+min/60;   //小时必须获取浮点类型(小时+分数转化成的小时)
        //问题 19:23:30
        //将24小时进制转换为12小时
        hour=hour>12?hour-12:hour;


        cxt.lineWidth=2;
        cxt.strokeStyle="white";  //表盘(蓝色)
        cxt.beginPath();
        cxt.arc(60,60,57,0,2*Math.PI,false);  //表盘的边框
        cxt.closePath();
        cxt.stroke();

//            //时刻度
//            for(var i=0;i<12;i++){
//                cxt.save();
//                cxt.lineWidth=5;    //设置时针刻度的粗细
//                cxt.strokeStyle="transparent";  //设置时针刻度的颜色
//                cxt.translate(250,250);
//                cxt.rotate(i*30*Math.PI/180);//角度*Math.PI/180=弧度
//                cxt.beginPath();
//                cxt.moveTo(0,-170);
//                cxt.lineTo(0,-190);
//                cxt.closePath();
//                cxt.stroke();
//                cxt.restore();
//            }

//            //分刻度
//            for(var i=0;i<60;i++){
//                cxt.save();
//                cxt.lineWidth=5;
//                cxt.strokeStyle="#04562E";
//                cxt.translate(250,250);
//                cxt.rotate(i*6*Math.PI/180);
//                cxt.beginPath();
//                cxt.moveTo(0,-180);
//                cxt.lineTo(0,-190);
//                cxt.closePath();
//                cxt.stroke();
//                cxt.restore();
//            }

        //时针
        cxt.save();
        cxt.lineWidth=2;
        cxt.strokeStyle="white";
        cxt.translate(60,60);//设置异次元空间的0，0点，画布的圆心
        cxt.rotate(hour*30*Math.PI/180);
        cxt.beginPath();
        cxt.moveTo(0,-30);     //针长
        cxt.lineTo(0,8);
        cxt.closePath();
        cxt.stroke();
        cxt.restore();


        //分针
        cxt.save();
        cxt.lineWidth=2;
        cxt.strokeStyle="white";
        cxt.translate(60,60);
        cxt.rotate(min*6*Math.PI/180);
        cxt.beginPath();
        cxt.moveTo(0,-45);
        cxt.lineTo(0,8);
        cxt.closePath();
        cxt.stroke();
        cxt.restore();


//            //秒针
//            cxt.save();
//            cxt.strokeStyle="#611123";
//            cxt.lineWidth=3;
//            cxt.translate(250,250);
//            cxt.rotate(sec*6*Math.PI/180);//设置旋转角度
//            cxt.beginPath();    //画图
//            cxt.moveTo(0,-170);
//            cxt.lineTo(0,20);
//            cxt.closePath();
//            cxt.stroke();
//            cxt.beginPath();    //画出时针、分针、秒针的交叉点、
//            cxt.arc(0,0,5,0,360,false);
//            cxt.closePath();
//            cxt.fillStyle="gray";   //设置填充样式
//            cxt.fill();
//            cxt.stroke();

//            //设置秒针前段的小圆点
//            cxt.beginPath();
//            cxt.arc(0,-150,5,0,360,false);
//            cxt.closePath();
//            cxt.fillStyle="#FFF";
//            cxt.fill();
//            cxt.stroke();//设置笔触样式(秒针已设置)
//            cxt.restore();
    }


 
   
})(jQuery);




