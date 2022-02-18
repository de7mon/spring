package com.study.springmvc.case02.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.case02.service.LottoService;

@Controller
@RequestMapping("/case02/lotto")
public class lottoController {
	@Autowired
	private LottoService lottoService;
	//lotto 主畫面
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("lottos",lottoService.getLottos());		
		model.addAttribute("gr",lottoService.groupbyLotto());
		return"case02/show_lotto";
	}
	//電腦選號
	@RequestMapping("/add")
	public String add() {
		lottoService.addLotto();
		return"redirect:./";
	}
	//修改記錄
	@RequestMapping("/update/{index}")
	public String updata(@PathVariable("index") int index) {
		lottoService.updateLotto(index);
		return"redirect:../";
	}
	//刪除記錄
	@RequestMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		lottoService.deleteLotto(index);
		return"redirect:../";
	}
	@RequestMapping("/groupby")
	public String groupby() {		
		lottoService.groupbyLotto2();
		return"redirect:./";
	}		
}
