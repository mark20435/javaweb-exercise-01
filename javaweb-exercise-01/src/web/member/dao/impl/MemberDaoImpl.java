package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;

import com.zaxxer.hikari.HikariDataSource;

import web.member.bean.Member;
import web.member.dao.MemberDao;

public class MemberDaoImpl implements MemberDao<Member, String> {
	private HikariDataSource ds;
	
	public MemberDaoImpl() {
		try {
			ds = (HikariDataSource) 
					new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Member selectByKey(String key) {
		Member member = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select ID, ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE from `MEMBER` where ACCOUNT = ?");) {
			pstmt.setString(1, key);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					member = new Member();
					member.setId(rs.getInt("ID"));
					member.setAccount(rs.getString("ACCOUNT"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setNickname(rs.getString("NICKNAME"));
					member.setPass(rs.getBoolean("PASS"));
					member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public int insert(Member Bean) {
		String sql = "insert into `MEMBER`(ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE) "
				+ "VALUES(?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, Bean.getAccount());
			pstmt.setObject(2, Bean.getPassword());
			pstmt.setObject(3, Bean.getNickname());
			pstmt.setObject(4, Bean.getPass());
			pstmt.setObject(5, Bean.getLastUpdateDate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteByKey(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Member Bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
