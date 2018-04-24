package com.qdch.p2p.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.util.JDBCUtil;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import com.qdch.core.BaseController;

public class FileUpLoadControllerp2p extends BaseController {
	
	public void index(){
		render("p2p/pages/06_02yuebaoshangchuan.html");
	}
	//文件上传
	public void upload(){  
        //存储路径  
        String path =PropKit.get("uploadPath");  
        UploadFile file = getFile("file");  
        String fileName = "";  
        String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀  
        if(!type.equals(".pdf")) {  //只能上传pdf格式文件
           return; 
        }else{  
            //上传文件  
            fileName = file.getFileName();
            String dest = path + "/" + fileName;  
            file.getFile().renameTo(new File(dest));  
        }  
        String fname = file.getFileName();
        String title = fname;
        fname = title;
        String jyscmc = getPara("jyscmc");
        //int jyscfl = 3;
        String bglx = getPara("bglx");
        int jyscfl = 0;
		try {
			jyscfl = Integer.parseInt(getPara("jyscfl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String loginUser = getLoginUser().getUsername();
		String sql="insert into insight_regulatory_report "
				+ "(order_number,title,upload_time,upload_user,jys,type,file_name,jysfl) "
				+ "values(?,?,?,?,?,?,?,?)";
		Object[] params ={null,title,date,loginUser,jyscmc,bglx,fname,jyscfl};
		JDBCUtil.executeUpdate(sql, params, "insight");
		renderJson();  
		
    }  
	
	//文件下载
	public void downLoad(){ 
		String fileName = getPara("fileName");
        String downPath = PropKit.get("uploadPath"); 
        File file=new File(downPath+"/"+fileName);  
        if(file.exists()){  
        	renderFile(file);
        }  
        else{  
            renderJson();  
        }  
    } 
	
	//文件预览
	public void fileView() {
		try {
			HttpServletResponse response = getResponse();
			boolean boo = true;
			String fileName = getPara("fileName");
			String filePath = PropKit.get("uploadPath")+"/"+fileName;
			File f = new File(filePath);
			if (!f.exists()) {
				response.sendError(404, "File not found!");
				return;
			}
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset(); // 非常重要
			if (boo) { // 在线打开（预览）
				URL u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; fileName=" + f.getName());
			} 
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();
			renderNull();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	

}
