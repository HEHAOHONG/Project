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
import java.util.List;

@WebServlet("/runsearch_age.html")
public class SearchageServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();

        try{
            List<String> searchResult1 = model.search_age(request.getParameter("searchstring1"),request.getParameter("searchstring2"));
            List<Patient> searchResult2 = model.searchfor_age(request.getParameter("searchstring1"),request.getParameter("searchstring2"));

            request.setAttribute("result1", searchResult1);
            request.setAttribute("result2", searchResult2);

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
            dispatch.forward(request, response);
        }catch (Exception e){}

    }
}
