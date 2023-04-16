package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;
import util.DBUtil;

public class UserDaoImpl implements UserDao {

	// DB와의 연결을 위한 객체 가져오기
	private final DBUtil util = DBUtil.getInstance();

	// 싱글턴 패턴
	private static UserDaoImpl instance = new UserDaoImpl();

	private UserDaoImpl() {
	};

	public static UserDaoImpl getInstance() {
		return instance;
	}

	/**
	 * 회원가입을 위해 받은 id, password 등의 데이터를 DB에 저장하기 실행 결과 잘 됐으면 true, 잘 안됐으면 false 반환
	 */
	@Override
	public boolean registUser(User user) {
		// 사용할 sql문 (users 테이블에 id, password 데이터 삽입하고 싶음)
		String sql = "INSERT INTO `users` (id, password) VALUES (?,?);";
		// DB 연결 객체
		Connection conn = null;
		// sql문 실행 객체
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// DB 연결 객체 얻기
			conn = util.getConnection();
			// 위에서 사용할 sql문을 통해 pstmt 구문 객체 생성
			pstmt = conn.prepareStatement(sql);

			// dto에서 데이터 가져와서 DB에 넣어줘야 함
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());

			System.out.println(user.toString());
			System.out.println("result : " + result);

			// sql 실행 결과 영향받은 행 수가 0보다 큰 경우 정상 실행
			result = pstmt.executeUpdate() > 0 ? true : false;
			System.out.println("result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제해주기
			util.close(conn, pstmt);
		}

		return result;
	}

	/**
	 * 로그인 정보(id, password)를 받아와서 DB에서 일치하는 사용자 있는지 조회 일치하는 사용자가 있다면 해당 사용자의 id와
	 * password를 담은 user 객체가 반환되고, 일치하는 사용자가 없다면 null이 반환된다.
	 */
	@Override
	public User loginUser(String id, String password) {
		String sql = "SELECT * FROM `users` WHERE id = ? AND password = ?;";

		Connection conn = null;
		PreparedStatement pstmt = null;
		// sql문 실행 결과 집합
		ResultSet rs = null;
		// 로그인 정보를 이용해서 DB 조회한 결과를 반환할 User 객체
		User user = new User();

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);

			// 로그인 하려는 아이디, 비밀번호 정보 담아서
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			// 실행. sql문 실행 결과가 rs에 저장됨
			rs = pstmt.executeQuery();

			if (rs.next()) { // 다음 결과가 있으면
				user.setId(rs.getString("id")); // 컬럼의 값을 가져와서 user 객체에 저장하기
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			util.close(conn, pstmt, rs);
		}

		return user;
	}

}
