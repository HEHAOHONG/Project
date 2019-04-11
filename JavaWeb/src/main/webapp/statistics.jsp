<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Patient" %>
<%@ page import="uk.ac.ucl.model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result</h1>

    <ul>
        <h3>Youngest patient: </h3>
        <%
            List<Patient> youngest = (List<Patient>) request.getAttribute("youngest");
            for (int i=0; i<youngest.size(); i++)
            {
                String href = "viewPatientInfo.html?id=" + youngest.get(i).getid();
        %>
        <li><a href="<%=href%>"><%=youngest.get(i).getname()%>: <%=youngest.get(i).getage()%> years old </a></li>
        <% } %>
        <br><br>

        <h3>Oldest patient: </h3>
        <%
            List<Patient> oldest = (List<Patient>) request.getAttribute("oldest");
            for (int i=0; i<oldest.size(); i++)
            {
                String href = "viewPatientInfo.html?id=" + oldest.get(i).getid();
        %>
        <li><a href="<%=href%>"><%=oldest.get(i).getname()%>: <%=oldest.get(i).getage()%> years old </a></li>
        <% } %>
        <br><br>

        <h3>Average age: </h3>
        <%
            String average = (String) request.getAttribute("averageage");
        %>
        <a><%=average%></a>
        <br><br>

        <h3>Number of people in age range: </h3>
        <%
            int[] range = (int[]) request.getAttribute("range");
            for (int i=0; i<range.length; i++){
            %>
        <a><%=i%>-<%=(i+1)*10%>: <%=range[i]%></a>
        <br>
        <%}%>

    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>