package com.qdch.xd.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.fr.hailian.util.JDBCUtil;
import com.jfinal.upload.UploadFile;
import com.qdch.core.BaseController;
import com.qdch.util.Preference;
import com.qdch.xd.model.MonthlyReportModel;


/*
* 文件上传
* @author ljm  
* @date 2018年4月16日
* 
*/
public class FileUpLoadController extends BaseController {
	public void index(){
		setAttr("jyslist", MonthlyReportModel.dao.getJys(getDataScopeByUserName()));//在进入页面时就获取权限内表为条件下拉框做准备
		render("xd/pages/06_02yuebaoshangchuan.html");
	}
	public void upload(){  
        HttpServletRequest request = getRequest();  
        String basePath = request.getContextPath();   
        //存储路径  
        String path1 = getSession().getServletContext().getRealPath(Preference._PATH);  
        String path ="D:/loadup";  
        System.out.println(basePath);
        System.out.println(path1);
        UploadFile file = getFile("file");  
        String fileName = "";  
        if(file.getFile().length() > 3*1024*1024) {  
            System.err.println("文件长度超过限制，必须小于3M");  
        }else{  
            //上传文件  
            String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀  
            fileName = System.currentTimeMillis()+type; // 对文件重命名取得的文件名+后缀  
            String dest = path + "/" + fileName;  
            file.getFile().renameTo(new File(dest));  
           // String realFile = basePath + "/" + Preference._PATH +  fileName;          
            String fname="/"+fileName;  
            setAttr("fname", fname);  
            //setAttr("url", realFile);  
              
        }  
        String fname = file.getFileName();
        String title = fname;
        fname = fileName;

        String jys = getPara("jys");
        int jysfl = 0;
        List<MonthlyReportModel> list = MonthlyReportModel.dao.getJyscfl(jys);
        for (MonthlyReportModel m : list) {
        	jysfl=Integer.parseInt(m.getStr("jysfl"));
		}
        String bglx = getPara("bglx");
        
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String loginUser = getLoginUser().getUsername();
        
        
		String sql="insert into insight_regulatory_report "
				+ "(order_number,title,upload_time,upload_user,jys,type,file_name,jysfl) "
				+ "values(?,?,?,?,?,?,?,?)";
		
		Object[] params ={null,title,date,loginUser,jys,bglx,fname,jysfl};
		
		JDBCUtil.executeUpdate(sql, params, "insight");
		
		renderJson();  
		
    }  
	
}
