package com.fr.hailian.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.fr.hailian.core.Constants;
/***
 * 通过FTP读取文件
 * @author Tom
 *
 */
public class FTPUtil {
	
	public static String read(String path,String ftpuser,String ftppwd) {  
        if(path.contains("ftp:") || path.contains("FTP:")){
            String ftppath = path.substring(19,43);            //暂时写死  
            String localPath="C:\\soft\\";  
            int reply;  
            FTPClient ftp = new FTPClient();  
            try {  
                ftp.connect(path.substring(6,18));             //暂时写死  
                //2.登录服务器 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器    
                ftp.login(ftpuser, ftppwd);    
                //3.判断登陆是否成功    
                reply = ftp.getReplyCode();    
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftp.disconnect();  
                }  
                  
                ftp.changeWorkingDirectory(ftppath);// 转移到FTP服务器目录    
                //5.遍历下载的目录    
                FTPFile[] fs = ftp.listFiles();    
                for (FTPFile ff : fs) {    
                    //解决中文乱码问题，两次解码    
                    byte[] bytes=ff.getName().getBytes("iso-8859-1");    
                    String fn=new String(bytes,"utf8");    
                    if(fn.contains("json")){  
                        //6.写操作，将其写入到本地文件中    
                        File localFile = new File(localPath + "temp.json");    
                        OutputStream is = new FileOutputStream(localFile);    
                        ftp.retrieveFile(ff.getName(), is);    
                        is.close();  
                    }  
                }   
                ftp.logout();  
                path = localPath + "temp.json";  
                  
            } catch (SocketException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }finally{  
                if (ftp.isConnected()){  
                    try {  
                        ftp.disconnect();  
                    } catch (IOException e) {  
                          
                    }  
                }  
            }     
        }  
        BufferedReader reader = null;  
        String laststr = "";  
        try {  
            FileInputStream fileInputStream = new FileInputStream(path);  
            InputStreamReader inputStreamReader = new InputStreamReader(  
                    fileInputStream, "utf-8");  
            reader = new BufferedReader(inputStreamReader);  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr += tempString;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return laststr;  
    }
	
	public static void main(String[] args){
		read("sftp://144.123.36.214:20202/upload",Constants.FTP_USER,Constants.FTP_PWD);
	}

}
