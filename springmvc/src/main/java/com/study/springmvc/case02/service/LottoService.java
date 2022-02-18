package com.study.springmvc.case02.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LottoService {
	
	private  List<Set<Integer>> lottos=new ArrayList<>();
	private List<Integer> list=new ArrayList<Integer>();
	
	public List<Set<Integer>> getLottos() {	
		return lottos;
	}
	
	public void addLotto() {
		lottos.add(0,generateLotto());
	}
	
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
	}
	
	public void deleteLotto(int index) {
		lottos.remove(index);
	}
	
	private Set<Integer> generateLotto() {
		Random r = new Random();
		// 樂透 539: 1~39 取出不重複的5個號碼
		Set<Integer> lotto = new LinkedHashSet<>();
		while(lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}		
	public Map<Integer, Long> groupbyLotto(){   
	return list.stream().sorted()
	.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
	;	
	}
	
	public void groupbyLotto2(){
		  list.clear();		    
		    for(Set<Integer> lott:lottos) {
				for(Integer In:lott) {
					list.add(In);
				}
			}		
	}
}
