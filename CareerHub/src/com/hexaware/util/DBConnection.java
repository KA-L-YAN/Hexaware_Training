package com.hexaware.util;
import java.sql.*;

public class DBConnection {
	static Connection connection;
	public static Connection getConnection()
	{
		try {
			connection = DriverManager.getConnection(PropertyUtil.getPropertyString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args)
	{
		System.out.println(getConnection());
	}
}