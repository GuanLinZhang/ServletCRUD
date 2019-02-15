package main.java.Servlet;

import main.java.Service.SaveService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Save",urlPatterns = "/Save")
public class SaveServlet extends HttpServlet {
    private SaveService saveService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        saveService = new SaveService(config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        if (saveService.save(name, password, email, country)) {
            out.println("Save Success");
            req.getRequestDispatcher("index.html").include(req, resp);
        } else {
            out.println("error occur");
            req.getRequestDispatcher("index.html").include(req, resp);
        }
    }

}
