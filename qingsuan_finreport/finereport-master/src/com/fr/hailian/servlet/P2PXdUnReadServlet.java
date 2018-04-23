package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.NotReadAllMsgService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;

/**
 * 
 * @todo  P2P、小贷调用）全部未读数量
 * @time   2018年4月8日 下午2:55:03
 * @author zuoqb
 */
public class P2PXdUnReadServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public P2PXdUnReadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getP2PXdUnReadMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getP2PXdUnReadMsg(request, response);
	}
	/**
	 * 
	 * @todo   获取用户相关信息
	 * @time   2018年4月8日 下午6:17:39
	 * @author zuoqb
	 * @return_type   void
	 */
	private void getP2PXdUnReadMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		NotReadAllMsgService notReadAllMsg = new NotReadAllMsgService();
		UserDataFromRoleService user = new UserDataFromRoleService();
		try {
			String uid=hrequest.getParameter("uid");
			String type = hrequest.getParameter("type");
			/*long superManagerID=UserControl.getInstance().getSuperManagerID();
			boolean isSuperAdmin=false;
			if((superManagerID+"").equals(uid)){
				//超级管理员
				isSuperAdmin=true;
			}
			 //查询数据权限 交易所
	        String jysStr=UserDataFromRoleService.getJysForP2pXd(userName,isSuperAdmin);*/
			String jysStr=hrequest.getParameter("jysStr");
			jysStr = "("+jysStr+")";
			String roleName = user.getRoleNameByUserId(uid);
			//获取角色具有查看信息的交易所
			//System.out.println(jysStr);
			long count = 0L;
			//判断当前用户为何种角色，通过角色去寻找当前的用户
			//如果是处理人，总的未读数应该加上风险事件数
			if(roleName.contains(KeyUtil.getKeyValue("P2PCLR"))){//P2P处理人
				count = notReadAllMsg.getUnReadAllMsgCount("DEAL",type,jysStr);
			}else if(roleName.contains(KeyUtil.getKeyValue("XDCLR"))){//小贷处理人
				count = notReadAllMsg.getUnReadAllMsgCount("DEAL",type,jysStr);
			}else if(roleName.contains(KeyUtil.getKeyValue("P2PSHR"))){//审核人
				count = notReadAllMsg.getUnReadAllMsgCount("AUDIT",type,jysStr);
			}else if(roleName.contains(KeyUtil.getKeyValue("XDSHR"))){
				count = notReadAllMsg.getUnReadAllMsgCount("AUDIT",type,jysStr);
			}else if(roleName.contains(KeyUtil.getKeyValue("P2PJCR"))){//政府
				count = notReadAllMsg.getUnReadAllMsgCount("JUDGE",type,jysStr);
			}else if(roleName.contains(KeyUtil.getKeyValue("P2PJCR"))){
				count = notReadAllMsg.getUnReadAllMsgCount("JUDGE",type,jysStr);
			}
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.append(com.alibaba.fastjson.JSONObject.toJSONString(count));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
	}

}
