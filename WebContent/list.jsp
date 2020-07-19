<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function delUser(id){
    		if(confirm("确定删除吗？"))
    		location.href="${pageContext.request.contextPath }/Userdel?id="+id;
    		
    	}
    	window.onload=function(){
    		document.getElementById("delselected").onclick=function(){
    			if(confirm("确定删除吗？"))
    			document.getElementById("form").submit();
    		}
    		
    		//获取第一个复选框
    		document.getElementById("firstcb").onclick=function(){
    			var cbs=document.getElementsByName("uid");
    			for (var i=0;i<cbs.length;i++){
    				cbs[i].checked=this.checked;
    			}
    		}
    		
    	}
    	
    	
    </script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
<style>
.top{
margin: 5px;

}
</style>
 <h3 style="text-align: center">用户信息列表</h3>
	<div style="float: left" class="top">
	<form class="form-inline" action="${pageContext.request.contextPath }/UserfindByPage" method="get" >
	  <div class="form-group">
	    <label for="exampleInputName2">姓名</label>
	    <input type="text" name="name"class="form-control" id="exampleInputName2"value="${condition.name[0] }" >
	  </div>
	  <div class="form-group">
	    <label for="address">地址</label>
	    <input type="text" name="address" class="form-control" id/="address" value="${condition.address[0] }">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputEmail2">邮箱</label>
	    <input type="email" name="email" class="form-control" id="exampleInputEmail2" value="${condition.email[0] }">
	  </div>
	  <button type="submit" class="btn btn-default">查询</button>
	</form>
	</div>
   
    
    <div style="float: right" class="top">
    <a class="btn btn-primary" href="add.jsp">添加联系人</a>
    <a class="btn btn-primary" href="javascript:vaid(0)" id="delselected">删除选中</a>
    </div>
    
    <form id="form" action="${pageContext.request.contextPath }/Userdelselected">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
        	<th>
        	<input type="checkbox" id="firstcb">
        	</th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
       <c:forEach items="${pageBean.list }" var="str" varStatus="s">
       <tr>
       		<td>
        	<input type="checkbox" name="uid" value="${str.id }">
        	</td>
       		<td>${s.count }</td>
       		<td>${ str.name}</td>
       		<td>${str.gender}</td>
       		<td>${str.age}</td>
       		<td>${str.address}</td>
       		<td>${str.qq}</td>
       		<td>${str.email}</td>
       	 <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath }/Userfind?id=${str.id }">修改</a>&nbsp;
       	 <a class="btn btn-default btn-sm" href="javascript:delUser(${str.id })">删除</a></td>
       </tr>
       </c:forEach>
        
        
    </table>
    
    </form>
    <div>
    <nav aria-label="Page navigation">
  <ul class="pagination">

  	<c:if test="${pageBean.currentPage==1 }">
  	<li class="disabled">
  	</c:if>
    <c:if test="${pageBean.currentPage!=1 }">
  	<li>
  	</c:if>
      <a href="${pageContext.request.contextPath }/UserfindByPage?currentPage=${pageBean.currentPage-1 }&rows=5&name=${condition.name[0]}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${ pageBean.totalPage}" var ="item" varStatus="s">
    	<c:if test="${pageBean.currentPage!=item}">
    	<li><a href="${pageContext.request.contextPath }/UserfindByPage?currentPage=${item }&rows=5&name=${condition.name[0]}&email=${condition.email[0]}&address=${condition.address[0]}">${item }</a></li>
     	</c:if>
     	
     	<c:if test="${pageBean.currentPage==item}">
    	<li class="active"><a href="${pageContext.request.contextPath }/UserfindByPage?currentPage=${item }&rows=5&name=${condition.name[0]}&email=${condition.email[0]}&address=${condition.address[0]}">${item }</a></li>
     	</c:if>
     </c:forEach>
     
     <c:if test="${pageBean.currentPage==pageBean.totalPage }">
  	<li class="disabled">
  	</c:if>
    <c:if test="${pageBean.currentPage!=pageBean.totalPage }">
  	<li>
  	</c:if>
   
      <a href="${pageContext.request.contextPath }/UserfindByPage?currentPage=${pageBean.currentPage+1 }&rows=5&name=${condition.name[0]}&email=${condition.email[0]}&address=${condition.address[0]}" aria-label="Next">
       <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <h5>共${pageBean.totalPage }页，第${pageBean.currentPage }页</h5>
  </ul>
  
	
	
</nav>
	
    </div>
</div>
</body>
</html>
