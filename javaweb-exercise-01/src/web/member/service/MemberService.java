package web.member.service;

import web.member.bean.Member;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;

public class MemberService {
	private MemberDao<Member, String> dao;

	public MemberService() {
		dao = new MemberDaoImpl();
	}

	public int checkAndInsert(Member member) {
		if (member.getAccount().length() < 5 || member.getAccount().length() > 50) {
			return -1;
		}else if(member.getPassword().length() < 6 || member.getPassword().length() > 12){
			return -2;
		}else if(member.getNickname().length() < 1 || member.getNickname().length() > 20) {
			return -3;
		}else if(dao.selectByKey(member.getAccount()) != null){
			return -4;
		}else {
			return dao.insert(member);
		}
	}
	
	
}
