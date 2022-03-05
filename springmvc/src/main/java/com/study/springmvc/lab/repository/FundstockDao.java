package com.study.springmvc.lab.repository;

import java.util.List;

import com.study.springmvc.lab.entity.Fundstock;

public interface FundstockDao {
    //每頁五筆
	int LIMIT=5;
	//全部查詢
	List<Fundstock> queryAll();
	//分頁查詢
	List<Fundstock> queryPage(int offset);		
	//取得單筆
	Fundstock get(Integer sid );
	//查詢所有筆數
	int count();
	//新增
	int add(Fundstock fundStock);
	//修改
	int update(Fundstock fundStock);
	//刪除
	int delete(Integer sid);
	
	
}
