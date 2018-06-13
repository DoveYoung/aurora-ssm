<%@page contentType="text/html; charset=gb2312" language="java"%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en" >
<html>
	<head>
		<title>��Ǯ֧�����</title>
		<meta http-equiv="content-type" content="text/html; charset=gb2312" >
	</head>
	
<BODY>
	<div align="center">
		<h2 align="center">��Ǯ֧�����ҳ��</h2>
		<font color="#ff0000">����ҳ������ο���</font>
    	<table width="500" border="1" style="border-collapse: collapse" bordercolor="green" align="center">
			<tr>
				<td align="center">
					��Ǯ���׺�
				</td>
				<td align="center">
					${dealId}
				</td>
			</tr>
			<tr>
				<td align="center">
					������
				</td>
				<td align="center">
					${orderId}
				</td>
			</tr>
			<tr>
				<td align="center">
					�������
				</td>
				<td align="center">
					${orderAmount}
				</td>
			</tr>
			<tr>
				<td align="center">
					�µ�ʱ��
				</td>
				<td align="center">
					${orderTime}
				</td>
			</tr>
			<tr>
				<td align="center">
					������
				</td>
				<td align="center">
					${msg}
				</td>
			</tr>	
		</table>
	</div>
</BODY>
</HTML>