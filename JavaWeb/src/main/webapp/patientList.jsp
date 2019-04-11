<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <ul>
    <%
      List<Patient> patients = (List<Patient>) request.getAttribute("patientNames");
      for (int i=1; i<patients.size(); i++)
      {
        String href = "viewPatientInfo.html?id=" + patients.get(i).getid();
    %>
    <li><a href="<%=href%>"><%=patients.get(i).getname()%></a></li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
