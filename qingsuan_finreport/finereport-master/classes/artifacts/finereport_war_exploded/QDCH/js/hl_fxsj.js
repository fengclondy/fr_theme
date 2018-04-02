/**
 * 风险事件的相关操作
 */
/**
 * 获取风险事件的未读数
 * @param fr_path 系统路径
 */
function getUnReadFxsjCount(fr_path) {
	var result=new Object();
	//fr_path为空使用默认地址
    var domain = fr_path + '/notReadMsg';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (parseInt(signResult.unReadCount)>=0) {
					result.success = true;
					result.count = signResult.unReadCount;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 风险事件的未读变已读
 * @param fr_path 系统路径
 */
function setUnReadFxsjToRead(fr_path) {
	var result=new Object();
    var domain = fr_path + '/readMsg';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 获取所有事件的未读数
 * @param fr_path 系统路径
 */
function getAllUnReadCount(fr_path) {
	var result=new Object();
    var domain = fr_path + '/getAllUnReadMsg';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				console.log(signResult.unReadAllCount);
				if (parseInt(signResult.unReadAllCount)>=0) {
					result.success = true;
					result.count = signResult.unReadAllCount;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 所有事件的未读变已读
 * @param fr_path 系统路径
 */
function setAllUnReadToRead(fr_path) {
	var result=new Object();
    var domain = fr_path + '/readAllUnReadMsg';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 发送短信
 * 处理人操作
 * @param fxsj_id 风险事件id
 * @param fr_path 系统路径
 * 
 */
function sendMsg(fxsj_id,fr_path) {
	var result=new Object();
    var domain = fr_path + '/sendMsg';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		data: {fxsj_id:fxsj_id},
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 审批人操作
 * @param fxsj_id 风险事件id
 * @param fr_path 系统路径
 * 
 */
function doAudit(fxsj_id,fr_path,status) {
	var result=new Object();
    var domain = fr_path + '/doAudit';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		data: {fxsj_id:fxsj_id,status:status},
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 通过username获取角色名
 * @param fr_path 系统路径
 * 
 */
function getRoleNameByUserName(fr_path) {
	var result=new Object();
    var domain = fr_path + '/getRole';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
					result.roleName = signResult.roleName
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
/**
 * 同步状态
 * 仅处理人使用，审核人方法已经同步状态
 * @param fr_path 系统路径
 * @param fxsj_id 
 * 
 */
function synchStatus(fr_path,fxsj_id) {
	var result=new Object();
    var domain = fr_path + '/synchStatus';
    console.log(domain);
	FR.ajax({
		url: domain,
		type: 'POST',
		data: {fxsj_id:fxsj_id},
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.success) {
					result.success = true;
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};
function getNewsByTitle(fr_path,title,type) {
	var result=new Object();
	//fr_path为空使用默认地址
    var domain = fr_path + '/getNewsByTitle';
	FR.ajax({
		url: domain,
		type: 'POST',
		 data: FR.cjkEncodeDO({
			 title: encodeURIComponent(title),
			 type:encodeURIComponent(type)
	        }),
		async: false,
		error: function () {
			result.fail = true;
			result.msg = "服务器异常！";
		},
		complete: function (res, status) {
			if (res.responseText != "") {
				var signResult = FR.jsonDecode(res.responseText);
				if (signResult.url!="") {
					result.success = true;
					if(type==0){
						result.url = signResult.url;
					}else{
						result.company_id = signResult.company_id;
					}
					
				} else {
					result.success = false;
				}
			}
		}
	});
	return result;
};