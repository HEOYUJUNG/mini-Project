// dto : data trasfer object 
// 데이터 교환을 위해 사용하는 객체 
// 브라우저에서 form에 입력받은 데이터를 dto에 넣어서 전달한다.
package dto;

public class User {
	private String id; // 아이디
	private String password; // 비밀번호

	// 기본 생성자
	public User() {
	}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + "]";
	}
}
