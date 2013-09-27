package com.fxpgy.fetch.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该工具类用于数据库连接，返回一个Statement对象,用户可以根据此对象执行sql语句获取结果
 */
public class DataBaseConnection {
	public static final String ACCESS_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private Connection connection = null;
	@SuppressWarnings("unused")
	private DataBaseConnection(){
	}
	public DataBaseConnection(String driver, String url, String user,
			String password) {
		try {
			// 1、加载驱动程序
			Class.forName(driver);

			// 2、通过url建立连接，连接到数据库
			if(connection == null){
				connection = DriverManager.getConnection(url, user, password);
			}
			// 3、创建语句，connection可以看出缆道，Statement可以看出缆车
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void excute(Statement st,String sql){
		try {
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询返回listMap
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public  List<Map<String,Object>> queryForList(String sql ,Object...params) throws Exception{
		ResultSet rs = query(sql,params);
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();
		ResultSetMetaData data=rs.getMetaData(); 
		while(rs.next()){
			Map<String,Object> rsMap = new HashMap<String, Object>();
			for(int i = 1 ; i<= data.getColumnCount() ; i++){
				rsMap.put(data.getColumnName(i), rs.getObject(i));
			}
			rsList.add(rsMap);
		}
		return rsList;
	}
	/**
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet query(String sql ,Object...params) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(sql);
		if(params != null){
			for(Object param : params){
				if(param instanceof String){
					statement.setObject(0, param);
				}
			}
		}
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}
	
	public void close(Statement st) {
		try {
			if (st != null)
				st.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		getStatement(MYSQL_DRIVER,"jdbc:mysql://localhost:3306/bsd","root","");
	}
}
