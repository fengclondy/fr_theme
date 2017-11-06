var topMenuImage=["fxhx_icon.png","zbdb_icon.png","zjyd_icon.png","kyjy_icon.png","scyq_icon.png","jysp_icon.png","ywyc_icon.png","gsxx_icon.png"];//菜单对应图标名称 具体文件放到com.fr.solution.theme.sky.files.image包下面
(function ($) {
   FS.THEME = $.extend(true, FS.THEME, {
   			config4MenuTree: {
	   			onAfterNodeExpand: function(node, $node, $li){
			   			if(node.level ==1){
			   				var $treeIcon=$li.children('ul').find(".tree-icon");
			   				$.each($treeIcon,function(index,item){
			   					$(item).removeClass("icon-tree-leaf icon-tree-frm icon-tree-cpt");
			   					//放图片
			   					$(item).css({"width":"1.2em","height":"1.2em","background":"url(${servletURL}?op=resource&resource=/com/fr/solution/theme/sky/files/image/"+topMenuImage[index]+")"});
			   					$(item).css({"position": "absolute","left": "2em","top":".5em","background-size":"100% 100%","background-repeat":"no-repeat"});
			   					$(item).html('');
			   					$(item).next().css("padding-left","25px");
			   				});
			   			}
			   			
		   			$(".tree-icon").html('');
	   			},
	   			onAfterNodeCollapse: function(node, $node, $li){
		   			if(node.level >= 1){
			   			$li.children('ul').css({
				   			'background': 'none'	
			   			});
			   			if(node.level ==1){
			   				var $treeIcon=$li.children('ul').find(".tree-icon");
			   				$.each($treeIcon,function(index,item){
			   					$(item).html('');
			   				});
			   			}
		   			};
		   			$(".tree-icon").html('');
	   			},
	   			/*onAfterNodeCreate: function(node, $node, $li){
		   			node.level>1&&$node.css({'border-bottom': 'solid 1px rgb(59,79,98)'});
		   			
	   			}*/
   			},
   			
   		/* config4MenuTree: {
             onAfterNodeCreate:function(node, $node, $li){
            	 node.level>1&&$node.css({'border-bottom': 'solid 1px rgb(59,79,98)'});
                 if(node.isModule){
                     //console.log($node.html());
                     var icon = !node.nodeicon ? 'leaf' : node.nodeicon;
                     var $icon = $node.find(".fs-menu-icon");
                     $icon.empty();
                     $('<i class="tree-icon" style="width:1.3em;height:1.3em;background:url(${servletURL}?op=resource&resource=/com/fr/solution/theme/sky/files/image/'+topMenuImage[0]+')"/>').appendTo($icon);//.addClass('icon-tree-'+icon);
                 }
                 $node.find('.icon-menu-b').removeClass('fui-fhc');
                 var hasChildren = node.hasChildren && node.ChildNodes && node.ChildNodes.length>0;
                 $node.attr('data-has-children',hasChildren);
                 $node.find('span').not(".fs-menu-icon").not(".menutree-text").css("font-size","15px");
                 if(hasChildren){
                     $node.find('.fs-menu-icon').empty().html('<i class="icon-tree-fork"></i>');
                 }
             },
          
             onAfterInit:function(element){ // add menu collapse button
                 var collapseBtn = $('<div id="fs-menu-collapse-btn" class="fs-menu-hide"/>')
                     .click(function () {
                         FS.CONSTS.Regions["west"].css("width", 0);
                         FS._doResize();
                         $(this).hide();
                         $('#fs-menu-expend-btn').show();
                     }).appendTo(FS.CONSTS.Regions["west"]);
                 var collapseBtn = $('<div id="fs-menu-expend-btn" class="fs-menu-show"/>')
                     .click(function () {
                         FS.CONSTS.Regions["west"].css("width", FS.THEME.config4frame.west.width);
                         FS._doResize();
                         $(this).hide();
                         $('#fs-menu-collapse-btn').show();
                     })
                     .appendTo(FS.CONSTS.Regions["east"]).hide();

                 $('iframe').attr({allowtransparency:true,frameborder:'0',border:'0',marginheight:'0',marginwidth:'0'});
             },
             onAfterNodeExpand : function(node, $node, $li){
                 //console.log("[onAfterNodeExpand]"+$node);
                 if($node){
                     $node.addClass('on');
                     $node.find('.tree-icon').html('');
                     //console.log($node.html());
                 }
                 if(node.level >= 1){
			   			$li.children('ul').css({
				   			'background': 'rgb(45,63,78)'	
			   			});
		   			}
             },
             onAfterNodeCollapse : function(node, $node, $li){
                 //console.log("[onAfterNodeCollapse]"+$node);
                 if($node){
                     $node.find('.tree-icon').html('');
                     $node.removeClass('on');
                     //console.log($node.html());
                 }
                 if(node.level >= 1){
			   			$li.children('ul').css({
				   			'background': 'none'	
			   			});
		   			}
             }
         },*/
   			
            config4tabPane: {
                style: 'alpha',
                isCollapsible: false,
                hasHomepageBtn: true
            },
            config4navigation: {
            	onAfterInit: function () {
                $("#fs-navi-message").remove();
                $("#fs-frame-search").remove();
                $("#fs-navi-favorite").remove();
                $('#fs-frame-menu').css('background-color','transparent');
                $('.fui-bsb').css('background-color','rgba(13,23,80,0.5)');
                $('.fui-fhc').css('color','transparent');
                $('.fui-fht').css('text-shadow','0');  
               /* 添加风险预警按钮*/
                var html='<li class="fs-navibar-item" id="monitor"><i class="icon-navi-message"></i><span class="fs-navi-msgnum fui-bsc">0</span></li>'
                $("#fs-navi-admin").before(html);
              /*  添加风险预警弹出框
                var monitor_box='<div id="monitor_box" style="position:absolute;margin:auto;width:60%;height:60%;top:20%;left:20%;background:url(${servletURL}?op=resource&resource=/com/fr/solution/theme/sky/files/image/tanchuangbg.png);background-repeat:no-repeat;z-index:99999;display:block;overflow-y:scroll"></div>'
                $('#fs-frame-body').after(monitor_box);
             	创建弹出框内的表格
                var monitor_box_table=$('<table id="monitor_box_table"><thead><tr><th>风险类别</th><th>风险指标</th><th>风险指标值</th><th>阀值</th><th>超出额</th></tr></thead><tbody id="J_TbData"></tbody></table>');
                monitor_box_table.appendTo("#monitor_box");
                var $trTemp = $("<tr></tr>");
                //往行里面追加 td单元格
                $trTemp.append("<td>"+ 资金风险 +"</td>");
                $trTemp.append("<td>"+ 大额出金 +"</td>");
                $trTemp.append("<td>"+ 30 +"</td>");
                $trTemp.append("<td>"+ 20 +"</td>");
                $trTemp.append("<td>"+ 50% +"</td>");
          
                $trTemp.appendTo("#J_TbData");
                $("#J_TbData").append($trTemp);
                
                $('#monitor').click(function(){
                	$('#monitor_box').toggle()
                })*/
            }
           },
          //框架布局配置属性  
           config4frame: {
		        resizable: false,
		        west: {
		            width: '12%' 
		        },
		        east: {
			        width: '88%'
		        }
		    },
   			
        });
})(jQuery);
