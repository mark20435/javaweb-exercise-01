package web.test.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.zaxxer.hikari.HikariDataSource;

public class DeleteMe {
	void m1() throws NamingException {
		HikariDataSource dataSource = (HikariDataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select EMPNO, ENAME from EMP");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Integer empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				System.out.println(empno + " " + ename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
