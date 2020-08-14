package web.member.dao;

import java.util.List;

public interface MemberDao<B, K> {
	B selectByKey(K key);
	int insert(B Bean);
	int deleteByKey(K key);
	int update(B Bean);
	List<B> selectAll();
}
