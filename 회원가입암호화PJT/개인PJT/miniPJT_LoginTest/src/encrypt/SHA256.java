package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// SHA-256 암호화하기
public class SHA256 {
	public SHA256() {
		
	}

	public String doEncrypt(String text) throws NoSuchAlgorithmException {
		// MessageDigest 클래스는 자바에서 단방향 해시 함수 값을 구할 때 사용한다.
		// SHA-256 알고리즘을 수행하는 MessageDigest 객체 생성
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// getBytes() : String을 바이트코드로 "인코딩" 해준다. 디폴트는 사용자 플랫폼의 기본 charset.
		// update(byte[] input) : 객체 내에 저장된 digest 값을 갱신시킨다.
		md.update(text.getBytes());

		// digest() : update() 실행 및 계산 완료 후 해시화된 값을 반환한다.
		return bytesToHex(md.digest());
	}

	// byte 배열을 hex string으로 변환하기 (디코딩)
	private String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			// &x : 16진수 / 2 : 두자리수로 만들겠다 / 0 : 빈 공간을 공백이 아닌 0으로 채워라
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
}
