<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
 
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath }/Userupdate" method="post">
        <div class="form-group" hidden>
            <label for="id">id：</label>
            <input type="text" class="form-control" id="id" name="id"  readonly="readonly"  value="${user.id }"/>
          </div>
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" placeholder="请输入姓名" value="${user.name }"/>
          </div>

          <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender=='男' }">
              <input type="radio" name="gender" value="男"  checked/>男
                <input type="radio" name="gender" value="女"  />女
          	</c:if>
          	<c:if test="${user.gender=='女' }">
              <input type="radio" name="gender" value="男"  />男
                <input type="radio" name="gender" value="女" checked />女
          	</c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" placeholder="请输入年龄" value="${user.age }"/>
          </div>

          <div class="form-group">
            <label for="address">籍贯：</label>
             <select name="address" class="form-control" >
             
             	<c:choose>
             		<c:when test="${user.address=='广东' }">
	             		<option value="广东" selected="selected">广东</option>
		                <option value="广西">广西</option>
		                <option value="湖南">湖南</option>
             		</c:when>
             		<c:when test="${user.address=='广西' }">
	             		<option value="广东" >广东</option>
		                <option value="广西" selected="selected">广西</option>
		                <option value="湖南">湖南</option>
             		</c:when>
             		<c:when test="${user.address=='湖南' }">
	             		<option value="广东" >广东</option>
		                <option value="广西">广西</option>
		                <option value="湖南" selected="selected">湖南</option>
             		</c:when>
             	</c:choose>
                
            </select>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" placeholder="请输入QQ号码" value="${user.qq }"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" placeholder="请输入邮箱地址" value="${user.address }"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回"/>
             </div>
        </form>
        </div>
    </body>
</html>