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
		return "������⣺"+this.title+"��ַ��"+this.address+"�������ڣ�"+this.date+"����۸�"+this.price+"��׼��"+this.specification;
				}
}
