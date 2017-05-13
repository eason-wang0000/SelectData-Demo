package com.gdemc;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StartApp {
	public static ArrayList<House> houseList = new ArrayList<House>();
public static void main(String[] args) {
	processHtml(getHtml58());
	DateHelper datehelper = new DateHelper();
	for(House house:houseList){
		datehelper.insertHouse(house);
		System.out.println(house.toString());
	}
	MySqlHelper.connectionMySQL();
	}
public static String getHtml58(){
		try {
		String result = HttpUtils.doGet("http://gz.58.com/chuzu/0/pn2");
		return result;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	return "";
	}
	public static void processHtml(String result){
		Document doc = Jsoup.parse(result);
		Elements houseInfo = doc.select("tbody tr");
		for(Element house:houseInfo){
			//System.out.println(house.html());
			processHouseInfo(house);
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		}
	}
	public static void processHouseInfo(Element house){
		String title="";
		String address="";
		String date="";
		String price="";
		String specification="";
		Elements temp = house.select("td a.t");
		if(temp!=null&&temp.size()>0){
			title = temp.get(0).text();
			temp = house.select("td p.qj-renaddr");
			if(temp!=null&&temp.size()>0){
				address = temp.get(0).text();
			}
			temp = house.select("p.qj-renaddr span");
			if(temp!=null&&temp.size()>0){
				date = temp.get(0).text();
			}
			temp = house.select("td b.pri");
			if(temp!=null&&temp.size()>0){
				price = temp.get(0).text();
			}
			temp = house.select("td span.showroom");
			if(temp!=null&&temp.size()>0){
				specification = temp.get(0).text();
			}
			houseList.add(new House(title,address,date,price,specification));
		}
	}
}
