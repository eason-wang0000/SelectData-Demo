package com.gdemc;

public class House {
	
	public String title="";
	public String address="";
	public String date="";
	public String price="";
	public String specification="";
	public House(String title,String address,String date,String price,String specification){
		this.title=title;
		this.address=address;
		this.date=date;
		this.price=price;
		this.specification=specification;
	}
	public String toString(){
		return "出租标题："+this.title+"地址："+this.address+"发布日期："+this.date+"出租价格："+this.price+"标准："+this.specification;
				}
}
