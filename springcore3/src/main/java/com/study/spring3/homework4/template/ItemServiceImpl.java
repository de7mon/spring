package com.study.spring3.homework4.template;

import java.security.acl.Group;
import java.sql.ResultSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSOutput;

import com.study.spring3.homework4.entity.Invoice;
import com.study.spring3.homework4.entity.Item;
import com.study.spring3.homework4.entity.ItemProduct;


@Repository
public class ItemServiceImpl implements ItemService{

	@Autowired
	  private JdbcTemplate jdbcTemplate;
	
	String sqlP="select   id ,text,price,inventory from ItemProduct";
	String sqlT="select   id ,amount,ipid,invid from Item";
	String sqlV="select   id ,invdate from Invoice";
	
	
	//商品項目
	public List<ItemProduct> queryItemProducts(){		
	
	List<ItemProduct> itemProducts=jdbcTemplate.query(sqlP,( ResultSet rs,int rowNum)->{
		ItemProduct itemProduct=new ItemProduct();
		itemProduct.setId(rs.getInt("id"));
		itemProduct.setText(rs.getString("text"));
		itemProduct.setPrice(rs.getInt("price"));
		itemProduct.setInventory(rs.getInt("inventory"));
	return itemProduct;
	});	
	return itemProducts;
	}
	//發票項目
	public List<Item> queryItem(){				
		List<Item> items=jdbcTemplate.query(sqlT,( ResultSet rs,int rowNum)->{
			Item item=new Item();
			item.setId(rs.getInt("id"));
			item.setAmount(rs.getInt("amount"));
			item.setIpid(rs.getInt("ipid"));
			item.setInvid(rs.getInt("invid"));
		return item;
		});	
		return items;
		}
	//發票
	public List<Invoice> queryInvoice(){		
		
		List<Invoice> invoices=jdbcTemplate.query(sqlV,( ResultSet rs,int rowNum)->{
			Invoice invoice=new Invoice();
			invoice.setId(rs.getInt("id"));
			invoice.setInvdate(rs.getTimestamp("invdate"));
		return invoice;
		});	
		return invoices;
		}
	//發票明細
	public List<Invoice> InvoiceALL() {
		List<Invoice> invoices=jdbcTemplate.query(sqlV,( ResultSet rs,int rowNum)->{
			Invoice invoice=new Invoice();
			invoice.setId(rs.getInt("id"));
			invoice.setInvdate(rs.getTimestamp("invdate"));	
			String sql2="select   id ,amount,ipid,invid from Item where invid=?";
			List<Item> items=jdbcTemplate.query(sql2,new BeanPropertyRowMapper<>(Item.class),invoice.getId());
			for(Item IT:items) {
			String sql3="select   id ,text,price,inventory from ItemProduct where id=?";
			List<ItemProduct> itemProducts=jdbcTemplate.query(sql3,new BeanPropertyRowMapper<>(ItemProduct.class)
					,IT.getIpid());
			if(itemProducts!=null  ) {
				IT.setItemProducts(itemProducts);		    	
		    }
			}			
			if(items!=null ) {
		    	invoice.setItems(items);	    	
		    }		
			return invoice;		
		});
		return invoices;
	}
	//商品總類明細
	public List<ItemProduct> Products(){		
		
		List<ItemProduct> itemProducts=jdbcTemplate.query(sqlP,( ResultSet rs,int rowNum)->{
			ItemProduct itemProduct=new ItemProduct();
			itemProduct.setId(rs.getInt("id"));
			itemProduct.setText(rs.getString("text"));
			itemProduct.setPrice(rs.getInt("price"));
			itemProduct.setInventory(rs.getInt("inventory"));
			String sql2="select   id ,amount,ipid,invid from Item where ipid=?";
			List<Item> items=jdbcTemplate.query(sql2,new BeanPropertyRowMapper<>(Item.class),itemProduct.getId());
			if(items!=null ) {
				itemProduct.setItems(items);	    	
		    }
			
		return itemProduct;
		});	
		return itemProducts;
		}
//每一張發票有那些商品
	@Override
	public void InvoiceProduct() {
		String sql="select  I.invid ,  IP.text  from  ItemProduct as IP inner join Item as I	"
				+ "on IP.id = I.ipid"				 
				;		
		List<Map<String , Object>> invpro=jdbcTemplate.queryForList(sql);	
		System.out.println(jdbcTemplate.queryForList(sql));	
	}
   //每一張發票有幾件商品?
	@Override
	public void InvoiceProductCount() {
		String sql="select  I.invid , IP.text,  I.amount  \r\n"
				+ "from  Invoice as IV inner join Item as I	\r\n"
				+ "				on IV.id = I.invid\r\n"
				+ "				inner join ItemProduct as IP on IP.id = I.ipid  "
				 
				;
		List<Map<String , Object>> invpro=jdbcTemplate.queryForList(sql);		
		System.out.println(jdbcTemplate.queryForList(sql));
	}
   
	 //每一張發票價值多少?
	@Override
	public void InvoiceMoney() {
		List<Invoice> invoices=InvoiceALL();
		for(Invoice IV:invoices) {
			System.out.println("發票序號:"+IV.getId());
			int x=0;
			for(Item IT:IV.getItems()) {
				System.out.print("產品數量:"+IT.getAmount()+"\t");								
				for(ItemProduct IP:IT.getItemProducts()) {
					System.out.print("產品價格:"+IP.getPrice()+"\t");
					System.out.println("產品名稱:"+IP.getText());
					System.out.println(IP.getText()+"總價格:"+(IP.getPrice()*IT.getAmount()));
				x+=(IP.getPrice()*IT.getAmount());
				}			
			}
			System.out.println("發票價值:"+x);			
			}		
	}
	 //每一樣商品各賣了多少?
	@Override
	public void ProductMoney() {
		List<ItemProduct> products= Products();
		for(ItemProduct IP:products) {
			int x =0; 		
			System.out.println("產品名稱:"+IP.getText());
			for(Item IT:IP.getItems()) {				
				x=+IT.getAmount();				
			}
			System.out.println("產品賣出數量:"+x);
			System.out.println("產品賣出總價:"+x*IP.getPrice());
		}	
	}
	//哪一件商品賣得錢最多?
	@Override
	public void ProductMaxMoney() {
		String sql="select  text as '產品名稱' ,max(price) as price from  ItemProduct";		
		List<Map<String , Object>> invpro=jdbcTemplate.queryForList(sql);	
		System.out.println("賣最多"+jdbcTemplate.queryForList(sql));			
	}
	//哪一張發票價值最高
	@Override
	public void InvoiceMaxMoney() {
		List<Invoice> invoices=InvoiceALL();
		int max=0;
		String Ivid="";
		for(Invoice IV:invoices) {
			System.out.println("發票序號:"+IV.getId());
			int x=0;
			for(Item IT:IV.getItems()) {											
				for(ItemProduct IP:IT.getItemProducts()) {
				x+=(IP.getPrice()*IT.getAmount());
				}			
			}
			System.out.println("發票價值:"+x);
			Ivid=max<x?IV.getId()+"":Ivid;
			max=max<x?x:max;
			
			
			}		
		System.out.println(Ivid+"發票價值最高，值:"+max);
	}

}
