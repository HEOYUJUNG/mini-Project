// dao : data access object
// DB의 데이터에 접근하기 위한 객체 
// dto를 통해 데이터를 받은 서버가 dao를 이용해 DB에 넣는다. 
package dao;

import dto.User;

public interface UserDao {
	// 회원가입
	public void registUser(User user);
	
	// 로그인 
	public User loginUser(String id, String password);
}
