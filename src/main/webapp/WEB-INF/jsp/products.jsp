<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style type="text/css">
	.songgray12{
		font-size: 12px;
		color: #666666;
		font-family: "宋体", "仿宋_GB2312", "黑体"
	; line-height: 24px
	; font-weight: bolder
	}
	.songgray24{
		font-size: 14px;
		color: #000000;
		font-family: "宋体", "仿宋_GB2312", "黑体"
	; line-height: 24px
	; font-weight: bolder
	}
	.ttable{ width: 640px; min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse; padding:2px;}
	.ttr { border:1px solid #0094ff; }
	.ttd { border:1px solid #0094ff; }
</style>

<script src="<%=request.getContextPath() %>/static/js/jquery.min.js" type="text/javascript"></script>

<html>

    <head>
        
    </head>
    
    <body style="margin:0px;">
	<div align="center">
		<%@ include file="head.jsp"%>

		<br>
        <table border="0" class="ttable">

			<tr class="songgray24 ttr" style="height: 84px">
				<td colspan="6" align="center">价格体系</td>
			</tr>
        	<tr class="songgray24 ttr">
        		<td class="ttd">
        			产品编号
        		</td>
        		<td class="ttd">
        			产品名称
        		</td>
				<td class="ttd">
					描述说明
				</td>
        		<td class="ttd">
        			产品价格
        		</td>
				<td class="ttd">
					商品个数
				</td>
        		<td class="ttd">
        			操作
        		</td>
        	</tr>

			<%--
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
			--%>

			<c:forEach items="${pList }" var="p">
			<tr class="songgray24 ttr">
				<td class="ttd">
						${p.id }
				</td>
				<td class="ttd">
						${p.name }
				</td>
				<td class="ttd">
						${p.name }
				</td>
				<td class="ttd">
						${p.price }
				</td>
				<td class="ttd">
						${p.total }
				</td>
				<td class="ttd">
					<c:choose>
						<c:when test="${p.name== '首次加入费'}">
						</c:when>
						<c:otherwise>
							<a href="<%=request.getContextPath() %>/alipay/goConfirm.action?productId=${p.id }">购买</a>
						</c:otherwise>
					</c:choose>
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

