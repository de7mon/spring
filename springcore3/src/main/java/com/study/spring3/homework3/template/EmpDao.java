package com.study.spring3.homework3.template;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.study.spring3.homework3.entity.Emp;

@Component
@Repository
public class EmpDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  
  @Autowired
  private ComboPooledDataSource dataSource;
  
  
  private List<String> timeLog=new ArrayList<String>();
  //public static List<Map<String, Date>> querryTime;
  //多筆查詢I
  public List<Map<String , Object>> queryAll(){
	  String sql="select eid,ename,age,createtime from emp";
	  List<Map<String , Object>> emps=jdbcTemplate.queryForList(sql);	  
	  return emps;
  }
 
  public List<Map<String , Object>> queryTimelog(){
	  String sql="select method_name ,log_timestamp from timelog";
	  List<Map<String , Object>> emps=jdbcTemplate.queryForList(sql);	  
	  return emps;
  }
  public void deletTimelog(){
	  jdbcTemplate.update("DELETE FROM timelog WHERE method_name='queryAll'");	
  }
  
  
  
  //多筆查詢II
  public List<Emp> querreEmps(){
	  String sql="select eid,ename,age,createtime from emp";
	  List<Emp> emps =jdbcTemplate.query(sql, (ResultSet rs ,int roeNum)->{
		  Emp emp =new Emp();
		  emp.setEid(rs.getInt("eid"));
		  emp.setEname(rs.getString("ename"));
		  emp.setAge(rs.getInt("age"));
		  emp.setCreatetime(rs.getTimestamp("createtime"));		  
		  return emp;		 		  		  
	  });
	  return emps;
	  
  }
//多筆查詢II
  public List<Emp> querreEmps2(){
	  String sql="select eid,ename,age,createtime from emp";
	  return jdbcTemplate.query(sql,new BeanPropertyRowMapper(Emp.class));
	  
	  
  }
  

  //單筆新增I
  public int addOne1(String ename ,int age) {
	  String sql= "insert into emp(ename,age) values(?,?)" ;
	  int rowcount= jdbcTemplate.update(sql ,ename ,age);
	  return rowcount;
	  
  }
//單筆新增II
  public int addOne2(String ename ,int age) {
	  String sql= "inset into emp(ename,age) values(:ename,:age)" ;
	 MapSqlParameterSource params=
			 new MapSqlParameterSource().addValue("ename",ename)
			 .addValue("age",age);
	 int rowcount = namedParameterJdbcTemplate.update(sql, params);
		return rowcount;
  }
//多筆新增I
  public int[] multiAdd1(List<Object[]> rows) {
	  String sql= "insert into emp(ename,age) values(?,?)" ;  
	  return jdbcTemplate.batchUpdate(sql,rows);
  }
  
  
  //多筆新增II
  public int[] multiAdd2(List<Emp> emps) {
	  String sql= "insert into emp(ename,age) values(?,?)" ; 
	  BatchPreparedStatementSetter setter=new BatchPreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {		
			//i=emps 的index
			ps.setString(1, emps.get(i).getEname());
			ps.setInt(2,emps.get(i).getAge() );
		}		
		@Override
		public int getBatchSize() {
			return emps.size();
		}
	};
	  	  
	  return jdbcTemplate.batchUpdate(sql,setter);
  }
  
  
//修改
  public int updateById(Integer eid,String ename ,Integer age) {
  String sql ="update emp set ename=?, age=? where eid=?";
  return jdbcTemplate.update(sql,ename,age,eid);
  
  }
  
//刪除
  public int deletById(Integer eid) {
  String sql ="delete from emp where eid=?";
  return jdbcTemplate.update(sql,eid);
  
  }
  //單筆新增交易版
  public int addOneTx(String ename, Integer age)throws Exception {
	  //建立 TransactionManager
	  DataSourceTransactionManager transactionManager =new DataSourceTransactionManager(dataSource);
	  //定義 TransactionDefinition
	  DefaultTransactionDefinition def=new DefaultTransactionDefinition();
	  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	  int rowcount=0;
	  TransactionStatus status=transactionManager.getTransaction(def);
	  
	  try {
	  String sql ="insert into emp(ename,age) values(?,?)";
	  rowcount=jdbcTemplate.update(sql,ename,age);
	  System.out.println(10/0);//模擬發生錯誤
	  }catch (Exception e) {
		transactionManager.rollback(status);
		throw e;
	}
	  transactionManager.commit(status);
	  return rowcount;
	  }
  
}
