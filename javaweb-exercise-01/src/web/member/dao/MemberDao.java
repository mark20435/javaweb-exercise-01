package web.member.dao;

import java.util.List;

public interface MemberDao<B, K> {
	B selectByAccount(K key);
	B selectByAAndP(K key,K key2);
	int insert(B Bean);
	int deleteByKey(K key);
	int update(B Bean);
	List<B> selectAll();
}
