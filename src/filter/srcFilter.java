package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class srcFilter
 */
@WebFilter("/*")
public class srcFilter implements Filter {

    /**
     * Default constructor. 
     */
    public srcFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) request;
		String uri=req.getRequestURI();
		System.out.println(uri);
		//�ų���¼��Դ
		if(uri.contains("login.jsp")||uri.contains("/Userlogin")||uri.contains("/css/")
				||uri.contains("/fonts/")||uri.contains("/js/")||uri.contains("/checkCodeServlet")) {
			chain.doFilter(request, response);
		}else {//�ж��û��Ƿ��¼
			if(req.getSession().getAttribute("User")==null) {
				request.setAttribute("error_msg", "����δ��¼�����Ƚ���");
				
				request.getRequestDispatcher("/login.jsp").forward(req, response);
			}
			
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
