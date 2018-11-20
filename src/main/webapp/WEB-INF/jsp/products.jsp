<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style type="text/css">
	.songgray12{
		font-size: 12px;
		color: #666666;
		font-family: "宋体", "仿宋_GB2312", "黑体"
	; line-height: 16px
	; font-weight: normal
	}
</style>

<script src="<%=request.getContextPath() %>/static/js/jquery.min.js" type="text/javascript"></script>

<html>

    <head>
        
    </head>
    
    <body style="margin:0px;">
	<div align="center">
		<%@ include file="head.jsp"%>

        <table>
        	<tr>
        		<td>
        			产品编号
        		</td>
        		<td>
        			产品名称
        		</td>
        		<td>
        			产品价格
        		</td>
        		<td>
        			操作
        		</td>
        	</tr>
	        <c:forEach items="${pList }" var="p">
	        	<tr>
	        		<td>
	        			${p.id }
	        		</td>
	        		<td>
	        			${p.name }
	        		</td>
	        		<td>
	        			${p.price }
	        		</td>
	        		<td>
	        			<a href="<%=request.getContextPath() %>/alipay/goConfirm.action?productId=${p.id }">购买</a>
	        		</td>
	        	</tr>
	        	
	        </c:forEach>
        </table>
        
        <input type="hidden" id="hdnContextPath" name="hdnContextPath" value="<%=request.getContextPath() %>"/>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<%@ include file="foot.jsp"%>
	</div>
    </body>
    
</html>


<script type="text/javascript">

	$(document).ready(function() {
		
		var hdnContextPath = $("#hdnContextPath").val();
		
		
	});
	

</script>

