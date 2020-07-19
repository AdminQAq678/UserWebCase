package Userservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Userservice.Userservice;
import Userservice.UserserviceImpl;
import domain.User;

/**
 * Servlet implementation class Userfind
 */
@WebServlet("/Userfind")
public class Userfind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 用户update操作查询用户信息回显的servlet
     * @see HttpServlet#HttpServlet()
     */
    public Userfind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Userservice userservice=new UserserviceImpl();
		//获取id
		int id=Integer.parseInt(request.getParameter("id"));
		//查找该id的用户
		User user=userservice.findUserById(id);
		//设置域信息 
		request.setAttribute("user", user);
		
		//请求转发到回显页面update.jsp
		request.getRequestDispatcher("/update.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
