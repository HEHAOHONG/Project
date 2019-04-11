package uk.ac.ucl.servlets;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewPatientInfo.html")
public class ViewPatientInfoServlet extends HttpServlet{

    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        String paramName = "id";
        String paramVale = req.getParameter(paramName);
        return paramVale;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String id = handleRequest(request,response);
//        System.out.println(id);
        List<Patient> patientInfo = model.searchFor(id);
        request.setAttribute("patientInfo",patientInfo);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/PatientInfo.jsp");
        dispatch.forward(request, response);
    }
}
