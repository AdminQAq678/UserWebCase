package Userservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Userservice.Userservice;
import Userservice.UserserviceImpl;
import domain.PageBean;
import domain.User;

/**
 * 分页查询
 * Servlet implementation class UserfindByPage
 */
@WebServlet("/UserfindByPage")
public class UserfindByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserfindByPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		request.setCharacterEncoding("utf-8");
		
		String rows=request.getParameter("rows");
		String currentPage=request.getParameter("currentPage");
		//判断不传参的情况
		if(currentPage==null||"".equals(currentPage)) {
			currentPage="1";
		}
		if(rows==null||"".equals(rows)) {
			rows="5";
		}
		
		Map<String, String[]> condition=request.getParameterMap();
		
		Userservice userservice=new UserserviceImpl();
		PageBean<User> pageBean=userservice.findUserByPage(currentPage,rows,condition);
		
		
		System.out.println(pageBean);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("condition", condition);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
