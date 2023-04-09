package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Mysql DB 1) 연결 객체를 제공해주고, 2) 사용했던 자원을 해제하는 기능을 제공하는 클래스
 *
 */
public class DBUtil {
	// DB와 연결하기위해 필요한 DB의 URL
	// url은 jdbc:mysql://[host][:port]/[database][?propertyName1][=propertyValue1]형태로 작성한다.
	// serverTimezone=UTC 설정이 없으면 오류가 발생하므로 주의한다.
	private final String url = "jdbc:mysql://localhost:3306/login?serverTimezone=UTC";
	// DB의 USER 이름
	private final String username = "yudaeng";
	// 위 USER의 PASSWORD
	private final String password = "yudaeng";
	// Mysql 드라이버 클래스 이름
	private final String drivername = "com.mysql.cj.jdbc.Driver";

	// 싱글턴 패턴 (객체가 한 번만 생성되도록 한다 => 객체의 유일성 보장)
	private static DBUtil instance = new DBUtil();

	private DBUtil() {
		try {
			// JDBC 드라이버를 로드한다! 즉, JVM 메모리에 JDBC 관련한 것들을 미리 올려 놓는다.
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBUtil getInstance() {
		return instance;
	}

	/**
	 * DB와의 connection 생성 및 반환
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 사용한 리소스들을 정리. 자원 해제.
	 * Connection, Statement, ResultSet 모두 AutoCloseable 타입이다.
	 * ... 을 이용하므로 필요에 따라서
	 * select 계열 호출 후는 ResultSet, Statement, Connection
	 * dml 호출 후는 Statement, Connection 등 다양한 조합으로 사용할 수 있다.
	 * @param closeables
	 */
	public void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}