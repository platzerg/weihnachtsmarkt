package com.platzerworld.weihnachtsmarkt.common.db;

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
				.getConnection("jdbc:sqlite:database.sqlite");
		Statement stat1 = conn1.createStatement();

		ResultSet rs1 = stat1.executeQuery("select * from biergarten;");
		WeihnachtsmarktVO biergartenVO = null;
		while (rs1.next()) {
			ResultSetMetaData rm = rs1.getMetaData();
			biergartenVO = new WeihnachtsmarktVO();

			for (int i = 1; i <= rm.getColumnCount(); i++) {
				System.out.println("Column-Name: " + rm.getColumnName(i) + " " + rs1.getString(i));
				
				switch (i) {
				case 1:
						biergartenVO.id = Long.parseLong(rs1.getString(i));
					break;
				case 2:
					biergartenVO.name = rs1.getString(i);
					break;
				case 3:
					biergartenVO.strasse = rs1.getString(i);
					break;
				case 4:
					biergartenVO.plz = rs1.getString(i);
					break;
				case 5:
					biergartenVO.ort = rs1.getString(i);
					break;
				case 6:
					biergartenVO.telefon = rs1.getString(i);
					break;
				case 7:
					biergartenVO.email = rs1.getString(i);
					break;
				case 8:
					biergartenVO.url = rs1.getString(i);
					break;
				case 9:
					biergartenVO.latitude = rs1.getString(i);
					break;
				case 10:
					biergartenVO.longitude = rs1.getString(i);
					break;
				case 11:
					biergartenVO.desc = rs1.getString(i);
					break;
				case 12:
					biergartenVO.desclong = rs1.getString(i);
					break;
				case 13:
					biergartenVO.gluehwein = rs1.getString(i);
					break;
				case 14:
					biergartenVO.lieblingsgericht = rs1.getString(i);
					break;
				case 15:
					biergartenVO.speisenkommentar = rs1.getString(i);
					break;
				case 16:
					biergartenVO.favorit = Boolean.valueOf(rs1.getString(i));
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
