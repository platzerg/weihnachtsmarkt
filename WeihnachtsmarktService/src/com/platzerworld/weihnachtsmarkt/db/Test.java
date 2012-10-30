package com.platzerworld.weihnachtsmarkt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class Test {
	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");
		// Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		Connection conn1 = DriverManager
				.getConnection("jdbc:sqlite:weihnachtsmaerkte.sqlite");
		Statement stat1 = conn1.createStatement();

		ResultSet rs1 = stat1.executeQuery("select * from weihnachtsmarkt;");
		WeihnachtsmarktVO weihnachtsmarktVO = null;
		while (rs1.next()) {
			ResultSetMetaData rm = rs1.getMetaData();
			weihnachtsmarktVO = new WeihnachtsmarktVO();

			for (int i = 1; i <= rm.getColumnCount(); i++) {
				System.out.println("Column-Name: " + rm.getColumnName(i) + " " + rs1.getString(i));
				
				switch (i) {
				case 1:
						weihnachtsmarktVO.id = Long.parseLong(rs1.getString(i));
					break;
				case 2:
					weihnachtsmarktVO.name = rs1.getString(i);
					break;
				case 3:
					weihnachtsmarktVO.strasse = rs1.getString(i);
					break;
				case 4:
					weihnachtsmarktVO.plz = rs1.getString(i);
					break;
				case 5:
					weihnachtsmarktVO.ort = rs1.getString(i);
					break;
				case 6:
					weihnachtsmarktVO.telefon = rs1.getString(i);
					break;
				case 7:
					weihnachtsmarktVO.email = rs1.getString(i);
					break;
				case 8:
					weihnachtsmarktVO.url = rs1.getString(i);
					break;
				case 9:
					weihnachtsmarktVO.latitude = rs1.getString(i);
					break;
				case 10:
					weihnachtsmarktVO.longitude = rs1.getString(i);
					break;
				case 11:
					weihnachtsmarktVO.desc = rs1.getString(i);
					break;
				case 12:
					weihnachtsmarktVO.desclong = rs1.getString(i);
					break;
				case 13:
					weihnachtsmarktVO.gluehwein = rs1.getString(i);
					break;				
				case 14:
					weihnachtsmarktVO.lieblingsgericht = rs1.getString(i);
					break;
				case 15:
					weihnachtsmarktVO.speisenkommentar = rs1.getString(i);
					break;
				case 26:
					weihnachtsmarktVO.favorit = Boolean.valueOf(rs1.getString(i));
					break;

				default:
					break;
				}

				
			}
		}

		/*
		 * Statement stat = conn.createStatement();
		 * stat.executeUpdate("drop table if exists people;");
		 * stat.executeUpdate("create table people (name, occupation);");
		 * PreparedStatement prep = conn.prepareStatement(
		 * "insert into people values (?, ?);");
		 * 
		 * prep.setString(1, "Gandhi"); prep.setString(2, "politics");
		 * prep.addBatch(); prep.setString(1, "Turing"); prep.setString(2,
		 * "computers"); prep.addBatch(); prep.setString(1, "Wittgenstein");
		 * prep.setString(2, "smartypants"); prep.addBatch();
		 * 
		 * conn.setAutoCommit(false); prep.executeBatch();
		 * conn.setAutoCommit(true);
		 * 
		 * ResultSet rs = stat.executeQuery("select * from people;"); while
		 * (rs.next()) { System.out.println("name = " + rs.getString("name"));
		 * System.out.println("job = " + rs.getString("occupation")); }
		 */
		rs1.close();
		conn1.close();
	}
}
