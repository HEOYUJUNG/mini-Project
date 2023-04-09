package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDaoImpl dao = UserDaoImpl.getInstance();
	
	/**
	 * get 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를 호출한다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	/**
	 * post 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를 호출한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) {
		String act = req.getParameter("act");
		switch(act) {
		case "regist":
			doRegist(req,resp);
			break;
		case "login":
			doLogIn(req,resp);
			break;
		}
	}

	private void doLogIn(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void doRegist(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}
