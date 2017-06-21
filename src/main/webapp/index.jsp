<%@ page import="entity.MonitoringURL" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
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

<table class="container">
    <thead>
    <tr>
        <th><h1>URL</h1></th>
        <th><h1>STATUS</h1></th>
        <th><h1>STATUS CODE</h1></th>
        <th><h1>SOMETHING ELSE</h1></th>
    </tr>
    </thead>
    <tbody>

    <% List<MonitoringURL> list = new LinkedList<>();
        final MonitoringURL monitoringURL = new MonitoringURL();
        monitoringURL.setUrl("https://www.google.com.ua/");
        monitoringURL.setStatus("OK");
        monitoringURL.setStatusCode(200);
        monitoringURL.setSomethingElse("Something else");

        final MonitoringURL git = new MonitoringURL();
        git.setUrl("https://github.com/");
        git.setStatus("OK");
        git.setStatusCode(200);
        git.setSomethingElse("Something");
        list.add(monitoringURL);
        list.add(git);
    %>

    <%

        for (MonitoringURL url : list) {

        %>

    <tr>
        <td><%= url.getUrl()%></td>
        <td><%= url.getStatus()%></td>
        <td><%= url.getStatusCode()%></td>
        <td><%= url.getSomethingElse()%></td>
    </tr>

    <%}%>
    <tr>
        <td>Twitter</td>
        <td>7326</td>
        <td>10437</td>
        <td>00:51:22</td>
    </tr>
    <tr>
        <td>Amazon</td>
        <td>4162</td>
        <td>5327</td>
        <td>00:24:34</td>
    </tr>
    <tr>
        <td>LinkedIn</td>
        <td>3654</td>
        <td>2961</td>
        <td>00:12:10</td>
    </tr>
    <tr>
        <td>CodePen</td>
        <td>2002</td>
        <td>4135</td>
        <td>00:46:19</td>
    </tr>
    <tr>
        <td>GitHub</td>
        <td>4623</td>
        <td>3486</td>
        <td>00:31:52</td>
    </tr>
    </tbody>
</table>

</body>
</html>
