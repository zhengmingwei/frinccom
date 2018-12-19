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
	.songgray2412{
		font-size: 14px;
		color: #000000;
		background-color:lightblue;
		font-family: "宋体", "仿宋_GB2312", "黑体"
	; line-height: 24px
	; font-weight: bolder
	}
	.songgray12{
		font-size: 12px;
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
<script type="text/javascript">



    //加载购买套餐记录信息
    function loadstu(){
        $.ajax({
            url:"<%=request.getContextPath() %>/alipay/orderPackagesByUserId",
            type:"POST",
            dataType:"json",
            success:function(data){

                var str=null;

                $.each(data.rows, function (index, item) {
                    str+="<tr class=\"songgray12 ttr\">";
                    str+="<td class=\"ttd\">"+item.orderPriceSystemId+"</td>";
                    str+="<td class=\"ttd\">"+item.name+"</td>";
                    str+="<td class=\"ttd\">"+item.total+"</td>";
                    str+="<td class=\"ttd\">"+item.quantityUsed+"</td>";
                    str+="<td class=\"ttd\">"+item.surplusQuentity+"</td>";
                    str+="<td class=\"ttd\">"+item.price+"</td>";
                    str+="<td class=\"ttd\">"+item.createTime+"</td>";
                    /*
                    str+="<td>"+data[i].name+"</td>";
                    str+="<td>"+data[i].sex+"</td>";
                    str+="<td>"+data[i].age+"</td>";
                    str+="<td>"+data[i].classid+"</td>";
                    str+="<td><a href=\"javascript:void(0);\" onclick='doDel("+item.id+",this)'>删除</a></td>";
                    */
                    str+="</tr>" ;


                    //循环获取数据
                    var id = item.id;
                   /*
                    var peopleid = item.peopleid;
                    var peoplename = item.peoplename;
                    var servicestatusid = item.servicestatusid;
                    var feespecialid = item.feespecialid;
                    var chargefee = item.chargefee;
                    var periodicalid = item.periodicalid;
                    var startdate = item.startdate;
                    var enddate = item.enddate;
                    var isfeeflagid = item.isfeeflagid;
                    var remark = item.remark;*/

                });

/*
                for(var i=0;i<data.length;i++){
                    str+="<tr>";
                    str+="<td>"+data[i].id+"</td>";

                    str+="<td>"+data[i].name+"</td>";
                    str+="<td>"+data[i].sex+"</td>";
                    str+="<td>"+data[i].age+"</td>";
                    str+="<td>"+data[i].classid+"</td>";
                    str+="<td><a href=\"javascript:void(0);\" onclick='doDel("+data[i].id+",this)'>删除</a></td>";
                    str+="</tr>";

                }*/
                $("#stuid tbody").empty();
                $("#stuid tbody").append(str);
            },
            error : function() {
                alert("数据访问错误");
            }

        });
    }


</script>


<html>

    <head>
        
    </head>
    
    <body style="margin:0px;">
	<div align="center">
		<%@ include file="head.jsp"%>

		<br>
        <table border="0" class="ttable">

			<tr class="songgray24 ttr" style="height: 84px">
				<td colspan="6" align="center">付款汇总表</td>
			</tr>
        	<tr class="songgray2412 ttr">
        		<td class="ttd"> 年月 </td>
        		<td class="ttd"> 金额 </td>
        	</tr>
			<c:forEach items="${payList }" var="p">
			<tr class="songgray24 ttr">
				<td class="ttd"> ${p.name } </td>
				<td class="ttd"> ${p.paymentAmount } </td>

			</tr>

		</c:forEach>
        </table>

		<table id="stuid" border="0" class="ttable">
			<thead>
			</thead>
			<tbody>

			</tbody>
		</table>


		<input type="hidden" id="hdnContextPath" name="hdnContextPath" value="<%=request.getContextPath() %>"/>

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

