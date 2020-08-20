package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	public Member selectByAccount(String key) {
		Member member = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select ID, ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE, ROLE_ID from `MEMBER` where ACCOUNT = ?");) {
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
					member.setRole_id(rs.getInt("ROLE_ID"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public int insert(Member Bean) {
		String sql = "insert into `MEMBER`(ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE, ROLE_ID) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, Bean.getAccount());
			pstmt.setObject(2, Bean.getPassword());
			pstmt.setObject(3, Bean.getNickname());
			pstmt.setObject(4, Bean.getPass());
			pstmt.setObject(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setObject(6, Bean.getRole_id());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteByKey(String key) {
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from `MEMBER` where ID = ?")) {
			pstmt.setInt(1, Integer.parseInt(key));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Member Bean) {
		String sql = "update `MEMBER` set PASSWORD = ?, NICKNAME = ?, LAST_UPDATE_DATE = ? where ACCOUNT = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, Bean.getPassword());
			pstmt.setObject(2, Bean.getNickname());
			pstmt.setObject(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setObject(4, Bean.getAccount());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Member> selectAll() {
		List<Member> mList = new ArrayList<Member>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("select ID, ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE, ROLE_ID from `MEMBER`");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("ID"));
				member.setAccount(rs.getString("ACCOUNT"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setNickname(rs.getString("NICKNAME"));
				member.setPass(rs.getBoolean("PASS"));
				member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
				member.setRole_id(rs.getInt("ROLE_ID"));
				mList.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

	@Override
	public Member selectByAAndP(String key, String key2) {
		Member member = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select ID, ACCOUNT, PASSWORD, NICKNAME, PASS, LAST_UPDATE_DATE, ROLE_ID  from `MEMBER` where ACCOUNT = ? and PASSWORD = ?");) {
			pstmt.setString(1, key);
			pstmt.setString(2, key2);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					member = new Member();
					member.setId(rs.getInt("ID"));
					member.setAccount(rs.getString("ACCOUNT"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setNickname(rs.getString("NICKNAME"));
					member.setPass(rs.getBoolean("PASS"));
					member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
					member.setRole_id(rs.getInt("ROLE_ID"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

}
