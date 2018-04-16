package com.qdch.xd.controller;

import com.fr.hailian.core.QdchUser;
import com.fr.hailian.model.RoleModel;
import com.qdch.core.BaseController;

import java.util.ArrayList;
import java.util.List;
//@ControllerBind(controllerKey = "/jfinal/demo", viewPath = "/p2p")
/***
 * demo controller
 * @author Tom
 *
 */
public class XiaoDaiController extends BaseController {
    
    public void p2p() {
    	QdchUser user=getLoginUser();
    	System.out.println(user);
        render("p2p/pages/index.html");
     }
    
    public void xd() {
    	QdchUser user=getLoginUser();

        String checkstatus = ""; // 处理状态
        List<RoleModel> roles = getLoginUser().getRoles();
        if(roles!=null && roles.size()>0){
            List<String> rr = new ArrayList<>();
            for(RoleModel roleModel:roles){
                rr.add(roleModel.getRoleName());
            }

            if(rr.contains("处理人")){
                checkstatus = "处理人";
            }else if (rr.contains("审核人")){
                checkstatus = "审核人";
            }else if (rr.contains("决策人")){
                checkstatus = "决策人";
            }
        }else{
           checkstatus="处理人";
        }
        setAttr("userrole",checkstatus);
    	System.out.println(user);
        render("xd/pages/index.html");
     }
}
