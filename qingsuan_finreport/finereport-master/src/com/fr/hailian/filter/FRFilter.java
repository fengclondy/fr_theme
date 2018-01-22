package com.fr.hailian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.fr.hailian.service.KeyWordsCountService;
import com.fr.hailian.service.SplitWordsService;

/**
 * 
 * @className FRFilter.java
 * @time   2017年8月7日 下午2:15:22
 * @author zuoqb
 * @todo   拦截器例子
 */
public class FRFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain) throws IOException,
			ServletException {

	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("拦截器例子初始化.....");
		
		//SplitWordsService split = new SplitWordsService();
		//split.doSplitWordsByMultitaskNew();
		//KeyWordsCountService key = new KeyWordsCountService();
		//key.doCountNew();
		// run in a second  
        /*final long timeInterval = 2000;  
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    // ------- code for task to run  
                    //System.out.println("Hello !!");  
                    // ------- ends here  
                	SplitWordsService split = new SplitWordsService();
            		split.doSplitWordsByMultitaskNew();
                    try {  
                        Thread.sleep(timeInterval);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        final long timeInterval2 = 1000*60*60*6; 
        Thread thread = new Thread(runnable);  
        thread.start();  
        Runnable runnable2 = new Runnable() {  
            public void run() {  
                while (true) {  
                    // ------- code for task to run  
                    //System.out.println("Hello !!");  
                    // ------- ends here  
                	//分词统计功能
                	//KeyWordsCountService key = new KeyWordsCountService();
            		//key.doCountNew();
                    try {  
                        Thread.sleep(timeInterval2);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread2 = new Thread(runnable2);  
        thread2.start();  */
	}

}
