package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import dto.User;
import encrypt.SHA256;

//이 서블릿이 호출되기 위해서는 url 상에 http://server_ip:port/context_name/main 이 필요하다.
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDaoImpl dao = UserDaoImpl.getInstance();
	private SHA256 sha = new SHA256(); // 이전에 작성한 SHA256 클래스 임포트 해오기

	/**
	 * get 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를
	 * 호출한다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	/**
	 * post 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를
	 * 호출한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 요청 시 한글 파라미터의 처리를 위해 encoding을 처리한다.
		req.setCharacterEncoding("utf-8");
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String act = req.getParameter("act");

		switch (act) {
		case "regist":
			doRegist(req, resp);
			break;
		case "login":
			doLogIn(req, resp);
			break;
		case "logout":
			doLogOut(req, resp);
			break;
		}
	}

	private void doRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req 객체에서 전달된 parameter를 추출한다.
		// login.jsp의 input 태그에서 입력한 id, password
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		// 전달받은 파라미터로 User 객체를 생성하고, attribute에 등록한다.
		User user = null;

		try {
			String encPassword = sha.doEncrypt(password);
			user = new User(id, encPassword); // 비밀번호 암호화하기!!
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		req.setAttribute("user", user);

		String msg = "회원가입 실패 (아이디 중복)";

		// 생성한 user를 DB에 등록하기
		if (dao.registUser(user)) {
			msg = "회원가입 성공!";
		}
		req.setAttribute("msg", msg);

		// JSP 화면 호출을 위해 RequestDispatcher의 forward를 사용한다.
		// 이때 연결할 jsp의 이름을 넘겨준다. forward에서 /는 context root를 나타낸다.
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	private void doLogIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		User user = null;
		try {
			// DB에 암호화된 비밀번호가 저장되어 있기 때문에
			// 비교를 위해서는 로그인 할 때도 암호화된 비밀번호를 가져가야 한다.
			user = dao.loginUser(id, sha.doEncrypt(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String msg = "로그인 성공!";

		HttpSession session = req.getSession();

		// DB에 저장된 정보가 없다면 null 반환
		if (user.getId() == null) {
			msg = "로그인 실패!";
		} else { // DB에 저장된 정보가 있다면
			// loginUser 정보는 request가 아닌 session에 저장하기 (암호화 작업 후에 암호화된 비밀번호를 페이지에서 확인할 예정)
			session.setAttribute("loginUser", user);
		}

		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	private void doLogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession();

		session.invalidate(); // session에 저장된 loginUser 정보를 날려준다.

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

}
