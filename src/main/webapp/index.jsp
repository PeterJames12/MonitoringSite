<%@ page import="monitoring.controller.MonitoringController" %>
<%@ page import="monitoring.model.MonitoringURL" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="main.css" rel="stylesheet">
</head>
<body>

<h1>
    <span class="blue"></span>Monitoring
    <span class="yellow">Site</span>
</h1>

<div class="form-style-5">
    <form action="index.jsp">
        <input type="text" name="url" placeholder="Url">
        <input type="submit" value="Apply">
    </form>
</div>

<table class="container">
    <thead>
    <tr>
        <th><h1>URL</h1></th>
        <th><h1>STATUS</h1></th>
        <th><h1>STATUS CODE</h1></th>
        <th><h1>EXTRA INFO</h1></th>
    </tr>
    </thead>

    <%

        final String url = request.getParameter("url");

        if (!"".equals(url)) {
            final MonitoringController monitoringController = new MonitoringController();
            monitoringController.saveUrl(url);
        }
    %>


    <tbody>

    <%
        final MonitoringController monitoringController = new MonitoringController();
        final List<MonitoringURL> urlList = monitoringController.getUrlInfo();
    %>

    <%

        for (MonitoringURL url : urlList) {

        %>

    <tr>
        <td><%= url.getUrl()%></td>
        <td><%= url.getStatus()%></td>
        <td><%= url.getStatusCode()%></td>
        <td><%= url.getExtraInfo()%></td>
    </tr>

    <%}%>
    </tbody>
</table>

</body>
</html>
