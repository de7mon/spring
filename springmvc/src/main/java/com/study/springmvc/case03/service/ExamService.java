package com.study.springmvc.case03.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.study.springmvc.case03.entity.Exam;
import com.study.springmvc.case03.entity.ExamSubject;

@Service
public class ExamService {

	// CopyOnWriteArrayList 是執行緒安全的集合-適合多執行緒操作
	// ArrayList 是非執行緒安全的集合-適合單緒操作
	// 因為預設的情況下 Spring 會將 ExamController 定義為 Singleton（單一實體）
	// 所以可以使用 CopyOnWriteArrayList 來作為多執行緒資料操作的集合類
	private List<Exam> exams = new CopyOnWriteArrayList<>();// 註冊考試紀錄的集合
	private List<ExamSubject> examSubjects = new CopyOnWriteArrayList<>();// 註冊考試紀錄的集合
	private Map<Boolean, String> examPay =  new LinkedHashMap<Boolean, String>();
	private Map<String, String> examSlot =  new LinkedHashMap<String, String>();
	{
		examSubjects.add(new ExamSubject("808", "JavaSE 8 OCP I"));
		examSubjects.add(new ExamSubject("809", "JavaSE 8 OCP II"));
		examSubjects.add(new ExamSubject("819", "JavaSE 11 OCP "));
		examSubjects.add(new ExamSubject("900", "JavaEE 7 OCP"));
		examPay.put(true,"已繳");
	    examPay.put(false,"未繳");
	    examSlot.put( "A", "上午(A)");
	    examSlot.put( "B","上午(B)");
	    examSlot.put( "C","上午(C)");
		
	}
	public Map<String, String> queryexamSlot() {
		return examSlot;
	}
	public Map<Boolean, String> queryExamPay() {
		return examPay;
	}
	
	public List<ExamSubject> queryExamSubjectList() {
		return examSubjects;
	}
	// 首頁(查詢多筆)
	public List<Exam> query() {

		return exams;
	}

	// 查詢單筆
	public Optional<Exam> get(int index) {
		if (index < 0 || exams.size() == 0 || index >= exams.size()) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(exams.get(index));
	}

	// 新增
	public Boolean add(Exam exam) {
		int previousSize = exams.size();
		exams.add(exam);
		int nextSize = exams.size();
		return previousSize > nextSize;
	}

	// 修改
	public synchronized Boolean update(int index, Exam exam) {
		if(index < 0 || exams.size() == 0 || index >= exams.size()) {
			return false;
		}
		exams.set(index, exam);
		return true;
	}
	// 修改 ExamNote 單一欄位資料
		public synchronized Boolean updateExamNote(int index, String examNote) {
			if(index < 0 || exams.size() == 0 || index >= exams.size()) {
				return false;
			}
			Exam exam=exams.get(index);
		    exam.setExamNote(examNote);
			exams.set(index, exam);
			return true;
		}

	// 刪除
	public synchronized Boolean delete(int index) {
		if (index < 0 || exams.size() == 0 || index >= exams.size()) {
			return false;
		}
		exams.remove(index);
		return true;
	}

}