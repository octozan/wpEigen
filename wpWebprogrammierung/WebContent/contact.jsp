<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<html>
<head>
<meta charset="UTF-8">
<title>Kontaktanfrage</title>
</head>
<body>
<h1>Beispiel f�r kleine allInOne "Webanwendung"</h1>
<%
// Hilfsvariable
boolean success = false;
// identifizieren ob Formular abgeschickt wurde
if(request.getParameter("send")!=null){
	// Fehler�berpr�fung
	if(StringUtils.isBlank(request.getParameter("email"))||StringUtils.isBlank(request.getParameter("subject"))||StringUtils.isBlank(request.getParameter("message"))){
		%>
		<span style="color: red">Bitte w�hlen Sie einen Empf�nger aus und geben Sie einen Betreff und die Nachricht ein.</span>
		<%
	}else{
		// alles ok, E-Mail senden und Hilfsvariable setzen
		// (email senden fehlt hier, kommt sp�ter)
		success = true;
	}
}
// Falls E-Mail versendet wurde
if(success){
%>
	<span style="color: green">Nachricht an ${param.email} gesendet.</span>
<%	
// Beim ersten Aufruf oder bei Fehlern
}else{
%>
	<form action="" method="post">
	Empf�nger:<br/>
	<select name="email">
	<option value="">--ausw�hlen--</option>
	<%
	// E-Mail Adressen f�r select laden
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/wp?user=wp&password=wp");
	ResultSet rs = conn.createStatement().executeQuery("select * from person");
	while(rs.next()) {
		%>
		<option value="<%=rs.getString("email")%>"><%=rs.getString("email")%></option>
		<%
	}
	%>
	</select><br/>
	Betreff:<br/>
	<input type="text" name="subject" value="${param.subject}"><br/>
	Nachricht:<br/>
	<textarea name="message">${param.message}</textarea><br/>
	<input type="submit" name="send" value="senden">
	</form>
<%} %>
</body>
</html>