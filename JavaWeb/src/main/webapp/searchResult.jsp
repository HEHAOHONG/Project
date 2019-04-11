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
  <%
    List<String> result = (List<String>) request.getAttribute("result1");
    List<Patient> patient = (List<Patient>) request.getAttribute("result2");
    int index=0;
    if (result.size() !=0)
    {
    %>
    <ul>
      <%
        for (String s : result)
        {
          String href = "viewPatientInfo.html?id=" + patient.get(index).getid();
          index++;
      %>
      <li><a href="<%=href%>"><%=s%></a></li>
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>