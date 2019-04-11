<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<jsp:include page="/header.jsp"/>




<div class="main">
    <h3>Details:</h3>

    <ul>
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>BIRTHDATE</td>
                <td>DEATHDATE</td>
                <td>SSN</td>
                <td>DRIVERS</td>
                <td>PASSPORT</td>
                <td>PREFIX</td>
                <td>FIRST</td>
                <td>LAST</td>
                <td>SUFFIX</td>
                <td>MAIDEN</td>
                <td>MARITAL</td>
                <td>RACE</td>
                <td>ETHNICITY</td>
                <td>GENDER</td>
                <td>BIRTHPLACE</td>
                <td>ADDRESS</td>
                <td>CITY</td>
                <td>STATE</td>
                <td>ZIP</td>
            </tr>
            <%
                List<Patient> patients = (List<Patient>) request.getAttribute("patientInfo");%>
            <tr>
                <%for (Patient p : patients){
                    for (int i=0; i<p.getsize(); i++) {%>
                        <td><l><%=p.getelement(i)%></l></td>
                    <% }%>
                <% }%>
            </tr>
        </table>
    </ul>

</div>



<jsp:include page="/footer.jsp"/>
</body>
</html>