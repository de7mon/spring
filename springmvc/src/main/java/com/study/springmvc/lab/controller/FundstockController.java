package com.study.springmvc.lab.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.ls.LSOutput;

import com.study.springmvc.case04.entity.Stock;
import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundDao;
import com.study.springmvc.lab.repository.FundstockDao;

import yahoofinance.YahooFinance;
@Controller
@RequestMapping("/lab/fundstock")
public class FundstockController {
	@Autowired
	private FundstockDao fundstockDao;
	
	@Autowired
	private FundDao fundDao;
	

	
	private int pageNumber = -1;
	
	@GetMapping("/")
	public String index(@ModelAttribute Fundstock fundstock, Model model) {
		return "redirect:./page/" + pageNumber;
	}
	
	@GetMapping("/page/{pageNumber}")
	public String page(@PathVariable("pageNumber") int pageNumber, @ModelAttribute Fundstock fundstock, Model model) {
		this.pageNumber = pageNumber;
		int offset = (pageNumber-1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks =  fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount = fundstockDao.count() / FundstockDao.LIMIT;
		model.addAttribute("_method", "POST");		
		model.addAttribute("fundstocks", fundstocks);
		model.addAttribute("funds", funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("groupMap", getGroupMap());
		model.addAttribute("stockMap", getStockMap());	
		return "lab/fundstock";
	}
	
//	@GetMapping("/{sid}")
//	@ResponseBody
//	public Fundstock get(@PathVariable("sid") Integer sid) {
//		
//		return fundstockDao.get(sid);
//	}
	@PostMapping("/")
	public String add(@Valid Fundstock fundstock, BindingResult result, Model model) {
		if(result.hasErrors()) { // 是否有錯誤發生
			model.addAttribute("_method", "POST");			
			return "lab/fundstock";
		}
		fundstockDao.add(fundstock);
		return "redirect:./";
	}
	@GetMapping("/{sid}")
	public String get2(@PathVariable("sid") int sid, Model model) {
		int offset = (pageNumber-1) * FundstockDao.LIMIT;
		List<Fundstock> fundstocks =  fundstockDao.queryPage(offset);
		List<Fund> funds = fundDao.queryAll();
		int pageTotalCount = fundstockDao.count() / FundstockDao.LIMIT;
		model.addAttribute("_method", "PUT");		
		model.addAttribute("fundstocks", fundstocks);
		model.addAttribute("funds", funds);
		model.addAttribute("pageTotalCount", pageTotalCount);
		model.addAttribute("groupMap", getGroupMap());
		model.addAttribute("stockMap", getStockMap());		
		model.addAttribute("fundstock", fundstockDao.get(sid));
		return "lab/fundstock";
	}
	@PutMapping("/{sid}")
	public String update(@Valid Fundstock fundstock, BindingResult result, Model model) {
		if(result.hasErrors()) { // 是否有錯誤發生
			model.addAttribute("_method", "PUT");			
			return "lab/fundstock";
		}
		fundstockDao.update(fundstock);
		return "redirect:./";
	}
	@DeleteMapping("/{sid}")
	public String delete(@PathVariable("sid") int sid) {
		fundstockDao.delete(sid);
		return "redirect:./";
	}
	
	private Map<String, Integer> getGroupMap() {
		// select s.symbol, sum(s.share) as share
		// from fundstock s
		// group by s.symbol
		List<Fundstock> fundstocks = fundstockDao.queryAll();
		//LinkedHashMap
		return  fundstocks.stream()
						 .collect(groupingBy(Fundstock::getSymbol,TreeMap::new,
											 summingInt(Fundstock::getShare)));
	}
	
	private Map<String,  Long> getStockMap()  {
		List<Fundstock> fundstocks = fundstockDao.queryAll();
		Map<String,  Long> stocMap=new TreeMap();
		yahoofinance.Stock yStock = null;
		Map<String, Map<String, Integer>> stocks= fundstocks.stream()
		 .collect(groupingBy(f->f.getFund().getFname(),
				 Collectors.groupingBy(Fundstock::getSymbol,summingInt(Fundstock::getShare))));
//		A {2330.tw=50000, 1101.TW=50000} 
//		B {2330.tw=60000, 2303.tw=70000}
//		C {2317.tw=80000, 1101.tw=90000, 2376.tw=80000}
//		D {2880.tw=50000, 3231.tw=70000, 2891.tw=40000, 2356.tw=60000} 
//		E {2317.tw=10000, 2330.tw=30000, 2886.tw=20000, 1101.tw=20000, 2002.tw=30000} 
//		F {1201.TW=30000} 
//		sssss {1101.TW=23444}				
		   for(String K2:stocks.keySet()) {
		   long money=0;
		   for(String K: stocks.get(K2).keySet() ) {

			   try {
					yStock = YahooFinance.get(K);
					long previousClose = yStock.getQuote().getPreviousClose().longValue();
				    long money2=0;
				    money2=stocks.get(K2).get(K)*previousClose;
				    money+=money2;

				} catch (IOException e) {
				}
			   stocMap.put(K2, money);
		   }	   
	   }
		return stocMap;
		}
	
	
	
}