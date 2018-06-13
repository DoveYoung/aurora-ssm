<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String spread = request.getParameter("spread");
request.setAttribute("spread", spread);
%>
<jsp:forward page="/home"/>
<!--goOrderConfirmCar-->
<!--goOrderConfirmBuyNow-->
<!--goHomePage-->
<!--goOrderPayway-->
<!--goOrderPaySuccess-->
<!--goFooterList-->
<!--goOrderError-->
