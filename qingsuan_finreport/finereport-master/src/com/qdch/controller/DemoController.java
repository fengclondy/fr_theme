package com.qdch.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.jfinal.core.Controller;
import com.qdch.model.DemoModel;

public class DemoController extends Controller {
    public void index() {
        renderText("This is a demo.");
     }
    public void json(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("test", "test");
    	renderJson(DemoModel.dao.getDemo());
    }

}
