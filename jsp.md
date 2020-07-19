## 脚本

<%

定义的java代码

%>

<%!

定义成员变量

%>



<%=

输出表达式

%>



## 内置对象

不需要创建获取和创建的对象，就可以直接使用的对象

### 九大内置对象

#### 1.request

+ 

#### 2.response

#### 3.out 字符输出流对象 和response.getWriter()类似，不论代码放置的先后,response对象先于out对象输出



## HttpSession 对象

>  与浏览器的一次会话的多次请求中共享数据

HttpSession session=request.getSession ();

session.setAttribute("msg","value);

session.getAttribute("msg");

session.removeAttribute("msg");



### Session对象的原理

**Session依赖于Cookie**，当创建Session的时候会给浏览器发送响应头set-cookie:JSESSIONID ，浏览器在内存存中保存该JSESSIONNID的cookie，下一次访问服务器时，服务器从浏览器获取JSESSIONID，然后在服务器中寻找由JSESSIONID标识的JSESSION对象。



情况：

* 当客户端关闭后再访问服务器，两次获取的session默认情况是不一样的，如果要设置获取的session对象相同，可以设置cookie（JSESSIONID，session.getId());

* 当客户端不关闭，服务器关闭，两次获取的session是同一个吗？
  * 不是同一个
  * 如果要保证数据的不丢失
    * 可以将session序列化

Session对象的销毁

Session默认失效时间30分钟

Session对象调用invalidate();销毁地下



Session特点

可以存任意类型任意大小的数据



## Session与cookie的区别

Session再服务器存储

cookie存在浏览器

Session较为安全



## jstl用到的包 jstl.jar 和jstl-impl.jar









## el表达式

### 内置对象

pageContext

> 可以获取其他八个内置对象
>
> 可以获取虚拟目录
>
> 方法 :
>
> pageContext.request.contextPath



域对象

requestScope.setAttribute("msg","hello");

${requestScope.msg}



## JSTL

引入标签库

<%taglib prefix="" uri=""%>



### 常用jstl标签

1. if 
2. choose (相当于switch)
3. foreach(相当于for循环)



<c:if test="false">1234</c:if>
	<c:choose>
		<c:when test="${number %2==0 }">星期一</c:when>
		<c:otherwise>星期二</c:otherwise>
	</c:choose>





<c:forEach begin="1" end="10" step="2" varStatus="s">
	${s.count }  count 表示循环的次数 s.idnex为下标
	</c:forEach>