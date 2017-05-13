package com.gdemc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DateHelper {
	public Connection conn = null;
	public DateHelper(){
		conn = MySqlHelper.connectionMySQL();
	}
	public void insertHouse(House house){
		if(house.title.length()<6){
			return;
		}
		String sql = "INSERT INTO `houseinfo`(`title`, `address`, `date`, `price`, `specification`) VALUES ('"+house.title+"','"+house.address+"','"+house.date+"','"+house.price+"','"+house.specification+"')";
		Statement statement = null;
		try {
			statement= conn.prepareStatement(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
