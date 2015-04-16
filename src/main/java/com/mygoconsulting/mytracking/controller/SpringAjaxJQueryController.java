package com.mygoconsulting.mytracking.controller;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.ardverk.collection.PatriciaTrie;
import org.ardverk.collection.StringKeyAnalyzer;
import org.ardverk.collection.Trie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.CompanyUser;
import com.mygoconsulting.mytracking.model.CustomerUser;
import com.mygoconsulting.mytracking.model.IUser;
import com.mygoconsulting.mytracking.model.SearchForm;

@Controller
public class SpringAjaxJQueryController {

	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@RequestMapping("/Home")
	public ModelAndView helloAjax(){
		return new ModelAndView("sampleAjax","message","Demo...");
	}
	
	@RequestMapping(value="/ajaxSearchTest", method=RequestMethod.GET)
	public @ResponseBody String getTime(){
		Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET,headers="Accept=*/*") 
	public @ResponseBody String[] searchAutoComplete(String data){
		logger.debug("BEGIN");
		logger.debug("Search parameter is: "+data);
		Trie<String, IUser> trie = new PatriciaTrie<String, IUser>(StringKeyAnalyzer.BYTE);
		trie.put("Apple", new CustomerUser());
		trie.put("Banana", new CompanyUser());
		trie.put("Something", new CustomerUser());
		/*Map.Entry<String, IUser> entry = trie.select(searchParam);
		entry.*/
		String [] result = {"Apple", "Banana", "Orange"};
		result[0] = "" ;
        System.out.println("Debug Message from SpringAjaxJQueryController.." + result.toString());
        return result;
	}
}
