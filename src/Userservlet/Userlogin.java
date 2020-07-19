package Userservlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import Userservice.UserserviceImpl;
import domain.User;

/**
 * Servlet implementation class Userlogin
 */
@WebServlet("/Userlogin")
public class Userlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 处理用户登录逻辑的servlet
     * @see HttpServlet#HttpServlet()
     */
    public Userlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	
		HttpSession session=request.getSession();
		String userCheckCode=request.getParameter("verifycode");
		//获取到服务器生成的验证码后删除session信息，保证一个验证码只能使用一次
		String checkCode=(String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");
//		System.out.println(userCheckCode);
//		System.out.println(checkCode);
		if(!checkCode.equalsIgnoreCase(userCheckCode)) {
			System.out.println("验证码错误");
			request.setAttribute("error_msg", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return ;
		}
		User user=new User();
		
		try {
			//封装对象
			BeanUtils.populate(user, request.getParameterMap());
			
			User loginUser =new UserserviceImpl().login(user);
			System.out.println("user"+user);
			
			if(loginUser!=null) {
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else {
				
				request.setAttribute("error_msg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
