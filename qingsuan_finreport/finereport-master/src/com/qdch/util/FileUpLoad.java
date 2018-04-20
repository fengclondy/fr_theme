package com.qdch.util;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;


/*
* 文件上传
* @author ljm  
* @date 2018年4月16日
* 
*/
public class FileUpLoad extends Controller {
	/*
		<form method="post" action="/blog/upload" <span style="color:#ff0000;">enctype="multipart/form-data"</span>>  
		    <input type="file" name="file">  
		    <input type="submit" value="上传">  
		</form>  
	 */
/*	public  String upload(){  
        HttpServletRequest request = getRequest();  
        String basePath = request.getContextPath();   
       // request.getParameter("");
        
        //存储路径  
        String path = getSession().getServletContext().getRealPath(Preference._PATH);  
        UploadFile file = getFile("file");  
        
        String fileName = "";  
        String fname="";
        if(file.getFile().length() > 50*1024*1024) {  
            System.err.println("文件大小超过限制，必须小于50M");  
        }else{  
            //上传文件  
            String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀  
            fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀  
            String dest = path + "/" + fileName;  
            file.getFile().renameTo(new File(dest));  
            String realFile = basePath + "/" + Preference._PATH +  fileName;          
            fname="/"+fileName;  
            setAttr("fname", fname);  
            setAttr("url", realFile);  
              
        }  
        return fname;
    }*/  
}
