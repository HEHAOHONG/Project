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

@WebServlet("/statistics.html")
public class ViewStatisticsServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

        Model model = ModelFactory.getModel();
        String average = model.getaverage();
        List yougest = model.getyoungest();
        List oldest = model.getoldest();
        int[] range = model.getrange();
        request.setAttribute("averageage", average); //
        request.setAttribute("youngest",yougest);
        request.setAttribute("oldest",oldest);
        request.setAttribute("range",range);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statistics.jsp");
        dispatch.forward(request, response);
    }
}
