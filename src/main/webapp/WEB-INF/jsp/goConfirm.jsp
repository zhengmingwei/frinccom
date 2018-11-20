<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<script src="<%=request.getContextPath() %>/static/js/jquery.min.js" type="text/javascript"></script>

<html>

    <head>
		<style type="text/css">
			.ant-btn-primary {
				color: #fff;
				background-color: #108ee9;
				border-color: #108ee9;
			}
		</style>
    </head>

	<body style="margin:0px;">
	<div align="center">
		<%@ include file="head.jsp"%>

		<br>
		<br>
		<br>
		<br>

    <form action="<%=request.getContextPath() %>/alipay/createOrder.action" method="post">
    	<input type="hidden" id="productId" name="productId" value="${p.id }" />
        <table>
        	<tr>
        		<td>
        			产品编号: ${p.id }
        		</td>
        	</tr>
        		<td>
        			产品名称: ${p.name }
        		</td>
        	<tr>
        	</tr>
        		<td>
        			产品价格: ${p.price }
        		</td>
        	<tr>
        	</tr>
        		<td>
        			购买个数： <input id="buyCounts" name="buyCounts" value="1" readonly="readonly" />
        		</td>
        	</tr>

			<tr><td colspan="2" align="center"></td></tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr></tr>
        	<tr>
        		<td colspan="2" align="center">
        			<%--<input type="submit" value="form提交，生成订单" />--%>
        			<%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
						<%--
        			<input type="button" value="ajax提交，生成订单" onclick="createOrder()" />--%>
						<input type="button" class="ant-btn-primary" value="生成订单" onclick="createOrder()" />
        		</td>
        	</tr>
        </table>
    </form>
    
    
        <input type="hidden" id="hdnContextPath" name="hdnContextPath" value="<%=request.getContextPath() %>"/>
		<br>
		<br>
		<br>
		<br>

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

		
	var hdnContextPath = $("#hdnContextPath").val();
	
	function createOrder() {
	 /*   var rl =  hdnContextPath + "/alipay/createOrder.action";
	    alert(rl);*/
		$.ajax({
	    	url: hdnContextPath + "/alipay/createOrder.action",
	    	type: "POST",
	    	data: {"productId": $("#productId").val(), "buyCounts": $("#buyCounts").val()},
	    	dataType: "json",
	    	success: function(data) {
				//alert(4422);
	            if(data.status == 200 && data.msg == "OK") {
					//alert("11data.status:"+data.status+";hdnContextPath:"+hdnContextPath);

	            	//debugger;
	            	// 提交订单成功后, 进入购买页面
	            	window.location.href = hdnContextPath + "/alipay/goPay.action?orderId=" + data.data;
	            } else {
					//alert("22data.status:"+data.status+";hdnContextPath:"+hdnContextPath);
	            	//alert(data.msg);
	            	console.log(JSON.stringify(data));
	            }
	    	}
	    });
	}

</script>

