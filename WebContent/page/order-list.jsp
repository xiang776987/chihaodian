<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/woxiangyao.js"></script>

    <style>
        body {
            margin: 0 auto;
        }

        .cont {
            position: relative;
            width: 100%;
            height: 70px;
        }
        /*显示按钮*/

        .shows {
            position: absolute;
            left: 0;
            width: 50%;
            font-size: 20px;
            height: 70px;
            text-align: center;
            background-color: #2AC845;
            color: #fff;
            line-height: 70px;
        }
        /*隐藏*/

        .nones {
            position: absolute;
            right: 0;
            width: 50%;
            font-size: 20px;
            height: 70px;
            text-align: center;
            background-color: #DD524D;
            color: #fff;
            line-height: 70px;
        }
        /*弹窗*/

        .dislog {
            position: fixed;
            z-index: 999;
            width: 100%;
            height: 100%;
            display: none;
            text-align: center;
            background-color: rgba(0,0,0,0.8);
        }
        /*状态*/

        .list {
            position: relative;
            width: 100%;
            height: 70px;
        }

        .dis_cont {
            position: relative;
            border-radius: 10px;
            top: 25%;
            width: 80%;
            display: inline-block;
            height: 160px;
            background-color: #9b9b9b;
        }
        /*确认取消*/

        .dis_bott {
            position: absolute;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
            border-top: 1px solid #ddd;
            width: 100%;
            height: 70px;
            background-color: #f5f5f5;
            bottom: 0px;
        }
        /*取消按钮*/

        .left_name {
            position: absolute;
            border-bottom-left-radius: 10px;
            text-align: center;
            left: 0px;
            width: 50%;
            height: 70px;
            line-height: 70px;
            background-color: #fff;
        }
        /*确认*/

        .right_name {
            position: absolute;
            text-align: center;
            border-bottom-right-radius: 10px;
            right: 0px;
            width: 49%;
            height: 70px;
            line-height: 70px;
            background-color: #fff;
        }
        /* 提示*/

        .cont_dis {
            position: relative;
            border-radius: 10px;
            text-align: center;
            width: 100%;
            height: 90px;
            font-size: 20px;
            display: flex;
            align-items: center;
            color: #fff;
            box-sizing: border-box;
            padding: 20px 20px;
        }



        #qrcode img {
            position: absolute;
            top: 40%;
            left: 30%;
            width: 200px;
            height: 200px;
            display: block;
        }

        #qrcode {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(255, 255, 255, 0.6);
            z-index: 9999;
            display: none;
        }


        .modal-alert {
            width: 270px;
            position: fixed;
            z-index: 1110;
            left: 50%;
            margin-left: -135px;
            margin-top: 0;
            top: 30%;
            text-align: center;
            border-radius: 0;
            perspective: 1000px;
        }
        .modal-alert_nav {
            border-radius: 0;
            background: #f8f8f8;}
        .modal-alert-bd {
            padding: 15px 10px;
            text-align: center;
            border-bottom: 1px solid #dedede;
            border-radius: 2px 2px 0 0;}
        .modal-alert-bd  input{
            height: 34px;
            border-radius: 4px;
            border:1px solid #dedede;
            padding: 0 4px;
        }
        .modal-alert-footer {
            height: 35px;
            overflow: hidden;
            display: table;
            width: 100%;
            border-collapse: collapse;
        }
        .modal-alert-btn {
            display: table-cell!important;
            padding: 0 5px;
            height: 34px;
            box-sizing: border-box!important;
            font-size: 15px;
            line-height: 34px;
            text-align: center;
            color: #0e90d2;
            cursor: pointer;
            border-left: 1px solid #dedede;
            margin-left:-1px;}

    </style>


</head>

<body>
<div id="qrcode">
    <img id="image" width="100%" height="100%" src="">
</div>

    <div class="sjsc-title2">
    	<h3 class="sjsc-t2l">我的订单</h3>


        <div class="dislog" style="display: {{nones}};">
          <%--  <div>
                <!--内容-->
                <div class="dis_cont">
                    <div  >
                        <input type="text" id="yzm"
                               class="input-text" style="width: 80%">
                        <input type="hidden"  id="yzm_order_id">
                    </div>

                    <div >
                        <label class="left_name" onclick="cances()">取消</label>
                        <label class="right_name" onclick="submits()">确认</label>

                    </div>

                </div>
            </div>--%>

                <div class="modal-alert">
                    <div class="modal-alert_nav">
                        <div class="modal-alert-bd">
                            <input type="text" name="" placeholder="输入验证码">
                        </div>
                        <div class="modal-alert-footer">
                            <label class="modal-alert-btn" onclick="submits()">确认</label>
                            <label class="modal-alert-btn" onclick="cances()">取消</label>
                        </div>
                    </div>
                </div>


        </div>
       <%-- <div class="cont">
            <div class="list">
                <label class="shows" onclick="shows()">显示</label>
                <label class="nones" onclick="nones()">隐藏</label>
            </div>
        </div>--%>



        <a href="center.html" class="sjsc-t2r"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px"/></a>
    </div>
    <ul class="quanbu-title2">
    	<li class="current" style="display: inline;"><a href="JavaScript:;">全部</a></li>
    	<li style="display: inline;"><a href="JavaScript:;">待支付</a></li>
    	<li style="display: inline;"><a href="JavaScript:;">
            待发货
        </a></li>
    	<li style="display: inline;"><a href="JavaScript:;">已发货</a></li>
        <div style="clear:both;"></div>
    </ul>
	
    <div class="my-dd">
    	<div class="my-info">
    	<c:forEach items="${map['list']}" var="list" varStatus="s">
    	<c:set value="ord${s.index}" var="ord"></c:set>
        	<div class="my-k1">
            	<ul class="my-p1">
                	<li class="my-spl f-l">${list.add_time}</li>
                	<li class="my-spr f-r">
					<c:if test="${list.status==0 }">待支付</c:if>
					<c:if test="${list.status==1 }">待发货</c:if>
					<c:if test="${list.status==2 }">已发货</c:if>
					<c:if test="${list.status==-5 }">退款中</c:if>
					<c:if test="${list.status==-6 }">已关闭</c:if>
					</li>
                    <div style="clear:both;"></div>
                </ul>
                <c:forEach items="${map[ord]}" var="ordList">
                <dl class="my-dl1">
                	<dt><a href="#"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
                    <%--<dt><a href="#"><img src="${list.qr_image}" style="width: 68px"></a></dt>--%>
                    <dd>
                    	<h3><a href="#">${ordList.goods_name}</a></h3>
                    	
                        <p class="my-dp1">价格：<span>${ordList.goods_price}</span></p>
                        <div class="my-jdt">
                        	<p class="jdt-p1 f-l">数量：</p>
                           
                            <p class="jdt-shuzi f-l">${ordList.goods_num}</p>
                    		<div style="clear:both;"></div>
                        </div>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                </c:forEach>
                <div class="my-p2">
                	<span class="my-sp3 f-l">订单号：${list.order_id}</span>
                	<c:if test="${list.status==0 }">
                <button class="my-btn1 f-r" onclick="window.location.href='payOrder.html?order_id=${list.order_id}'">支付：￥${list.goods_total}</button>
                	</c:if>
                	<c:if test="${list.status!=0}">
                	<c:if test="${!empty list.express_hm}">
                	<button class="my-btn1 f-r" onclick="window.location.href='order.html?order_id=${list.order_id}'">物流查询</button>
                	</c:if>
                	<button class="my-btn1 f-r" >￥${list.goods_total}</button>

                	<c:if test="${list.status==1}">
               		    <button class="my-btn1 f-r" onclick="send('${list.order_id}')">退款</button>
                        <c:if test="${list.is_coupon==1}">
                            <button class="my-btn1 f-r" onclick="qrToggle('${list.qr_image}')">二维码</button>
                            <button class="my-btn1 f-r" onclick="shows('${list.order_id}')">验证码</button>
                        </c:if>
                	</c:if>

                	</c:if>
                    <div style="clear:both;"></div>
                </div>
            </div>
           </c:forEach> 
        	
        </div>
        <div class="my-info" style="display:none;">
        	<c:forEach items="${map['list0']}" var="list" varStatus="s">
    	<c:set value="ord0${s.index}" var="ord"></c:set>
        	<div class="my-k1">
            	<ul class="my-p1">
                	<li class="my-spl f-l">${list.add_time}</li>
                	<li class="my-spr f-r">
					<c:if test="${list.status==0 }">待支付</c:if>
					<c:if test="${list.status==1 }">待发货</c:if>
					<c:if test="${list.status==2 }">已发货</c:if>
					</li>
                    <div style="clear:both;"></div>
                </ul>
                <c:forEach items="${map[ord]}" var="ordList">
                <dl class="my-dl1">
                	<dt><a href="#"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
                    <dd>
                    	<h3><a href="#">${ordList.goods_name}</a></h3>
                        <p class="my-dp1">价格：<span>￥${ordList.goods_price}</span></p>
                        <div class="my-jdt">
                        	<p class="jdt-p1 f-l">数量：</p>
                           
                            <p class="jdt-shuzi f-l">${ordList.goods_num}</p>
                    		<div style="clear:both;"></div>
                        </div>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                </c:forEach>
                <div class="my-p2">
                	<span class="my-sp3 f-l">订单号：${list.order_id}</span>
                	<button class="my-btn1 f-r" onclick="window.location.href='payOrder.html?order_id=${list.order_id}'">支付：￥${list.goods_total}</button>
                    <div style="clear:both;"></div>
                </div>
            </div>
           </c:forEach> 
        
        	
        </div>
        <div class="my-info" style="display:none;">
            <c:forEach items="${map['list1']}" var="list" varStatus="s">
    	<c:set value="ord1${s.index}" var="ord"></c:set>
        	<div class="my-k1">
            	<ul class="my-p1">
                	<li class="my-spl f-l">${list.add_time}</li>
                	<li class="my-spr f-r">
					<c:if test="${list.status==0 }">待支付</c:if>
					<c:if test="${list.status==1 }">待发货</c:if>
					<c:if test="${list.status==2 }">已发货</c:if>
					</li>
                    <div style="clear:both;"></div>
                </ul>
                <c:forEach items="${map[ord]}" var="ordList">
                <dl class="my-dl1">
                	<dt><a href="#"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
                    <dd>
                    	<h3><a href="#">${ordList.goods_name}</a></h3>
                        <p class="my-dp1">价格：<span>￥${ordList.goods_price}</span></p>
                        <div class="my-jdt">
                        	<p class="jdt-p1 f-l">数量：</p>
                           
                            <p class="jdt-shuzi f-l">${ordList.goods_num}</p>
                    		<div style="clear:both;"></div>
                        </div>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                </c:forEach>
                <div class="my-p2">
                	<span class="my-sp3 f-l">订单号：${list.order_id}</span>
                	
                    <button class="my-btn1 f-r">￥${list.goods_total}</button>
                    <c:if test="${list.status==1}">
               		 <button class="my-btn1 f-r" onclick="send('${list.order_id}')">退款</button>
                	</c:if>
                    <div style="clear:both;"></div>
                </div>
            </div>
           </c:forEach> 
                 	
        </div>
        <div class="my-info" style="display:none;">
            <c:forEach items="${map['list2']}" var="list" varStatus="s">
    	<c:set value="ord2${s.index}" var="ord"></c:set>
        	<div class="my-k1">
            	<ul class="my-p1">
                	<li class="my-spl f-l">${list.add_time}</li>
                	<li class="my-spr f-r">
					<c:if test="${list.status==0 }">待支付</c:if>
					<c:if test="${list.status==1 }">待发货</c:if>
					<c:if test="${list.status==2 }">已发货</c:if>
					</li>
                    <div style="clear:both;"></div>
                </ul>
                <c:forEach items="${map[ord]}" var="ordList">
                <dl class="my-dl1">
                	<dt><a href="#"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
                    <dd>
                    	<h3><a href="#">${ordList.goods_name}</a></h3>
                        <p class="my-dp1">价格：<span>￥${ordList.goods_price}</span></p>
                        <div class="my-jdt">
                        	<p class="jdt-p1 f-l">数量：</p>
                           
                            <p class="jdt-shuzi f-l">${ordList.goods_num}</p>
                    		<div style="clear:both;"></div>
                        </div>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                </c:forEach>
                <div class="my-p2">
                	<span class="my-sp3 f-l">订单号：${list.order_id}</span>
                	<button class="my-btn1 f-r" onclick="window.location.href='express.jsp?express_dm=${list.express_dm}&express_hm=${list.express_hm}&express_name=${list.express_name}'">物流查询</button>
                    <button class="my-btn1 f-r">￥${list.goods_total}</button>
                    <div style="clear:both;"></div>
                </div>
            </div>
           </c:forEach> 
            
        </div>
    </div>
    <jsp:include page="footer5.jsp"></jsp:include>	
    		
    		<script type="text/javascript">

                //显示遮罩弹窗
                function shows(order_id){
                    $('#yzm_order_id').val(order_id);
                    // alert($('#yzm_order_id').val());
                    $(".dislog").css("display","block");
                }
                //隐藏遮罩弹窗
                function nones(){
                    $(".dislog").css("display","none");
                }
                function submits(order_id){

                    var yzm = $('#yzm').val();
                    var order_id =$('#yzm_order_id').val();
                    // alert($('#yzm_order_id').val())
                    // alert($('#yzm').val())
                    $.ajax({
                        url:'yzmhx.html',
                        type:'post',
                        data:'yzm='+yzm+'&order_id='+order_id,
                        success:function(rs){
                            alert(rs);
                            if (rs=='核销成功'){
                                window.location.reload(true)
                            }


                        }
                    })
                }
                //取消
                function cances(){
                    $(".dislog").css("display","none");
                }


    		function send(order_id){
    			var  b = confirm('确定退款吗？');
        		if(!b){
        		return ;
        		}
    			$.ajax({
    				url:'orderUpdate.html',
    				type:'post',
    				data:'order_id='+order_id+'&status=-5',
    				success:function(rs){
    					if(rs==1){
    						alert("提交成功，我们将3个工作日内给您退款！");
    						location.reload();
    					}else{
    						alert("失败，请联系客服！");
    					}
    				}
    			})
    		}
    		function qrToggle( qrImage){
                // qrImage = "/chihaodian"+qrImage;
                    $("#image").attr('src',qrImage);
                    $("#qrcode").fadeIn("slow");
    		}

            $("#qrcode").click(function() {
                $("#qrcode").fadeOut("slow");
            })
    		</script>
</body>
</html>
