过滤器的创建

类实现javax.Filter接口

在doFilter中

放行：

> filterChain.doFilter(servletRequest,servletResponse)

1. 配置拦截路径

>  @webFilter("/*")

web.xml中配置

<filter>

<filter-name>demo1</filter-name>

<filter-class><filter-class>



</filrer>



<filter-mapping>

<filter-name>demo1<filter-name>

<url-pattern>/*</url-pattern>

</filter-mapping>

* 目录拦截 @webFilter("/user/")
  * 对servlet的拦截可以根据虚拟路径去拦截
* 具体资源路径@webFilter("/index.jsp")
* 后缀名拦截@webFilter("*.jsp") 不要写/
* 拦截所有@webFilter("/*")

2. 生命周期

```java
public void destroy() {
		// TODO Auto-generated method stub
	//服务器关闭后执行
    //用于释放资源
}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
        //请求被拦截的时候执行
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
        //服务器开启后创建filter对象会执行该方法
        //用于初始化资源
		// TODO Auto-generated method stub
	}
```



3. 过滤器链

   1. 多个过滤器
      * 过滤器1执行->过滤器2执行->资源执行->回到过滤器2->回到过滤器1
   2. 过滤器的执行顺序
      * web.xml的配置方式
        * 定义在前面的过滤器先执行
      * 注解配置
        * 按照类名的比较结果进行查询，从小到大执行

4. 执行的顺序

   ```java
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
   		// TODO Auto-generated method stub
   		// place your code here
   
   		// pass the request along the filter chain
       	//可以对request对象进行增强
       	System.out.println("先执行这里")
   		chain.doFilter(request, response);//放行，将访问资源
       	System.out.println("最后执行这里")//访问资源完毕后回到这里，可以对response对象进行增强
   	}
   ```

5. 拦截方式(拦截请求转发或者是直接访问的)

   1. 配置方式

      1. web.xm

         1. <dispatcher>REQUEST</dispatcher>

         2. ```xml
            <filter>
            
            <filter-name>demo1</filter-name>
            
            <filter-class><filter-class>
            
            
            
            </filrer>
            
            
            
            <filter-mapping>
            
            <filter-name>demo1<filter-name>
            
            <url-pattern>/*</url-pattern>
            <dispatcher>REQUEST</dispatcher>
            
            </filter-mapping>
            ```

            

      2. 注解

         1. 设置dispatcherTypes 属性

            1. REQUEST 直接请求的情况下访问资源才会拦截
            2. FORWARD 请求转发的情况下访问资源才会拦截
            3. INCLUDE 包含访问资源的情况下访问资源才会拦截
            4. ERROR 错误跳转资源的情况下访问资源才会拦截
            5. ASYNC 异步访问资源的情况下访问资源才会拦截

            > @WebFilter(value="/*",dispatcherTypes = DispatcherType.REQUEST)