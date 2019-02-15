package main.java.Servlet;

import main.java.Service.DeleteService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet",urlPatterns = "/Delete")
public class DeleteServlet extends HttpServlet {
    private DeleteService deleteService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        deleteService = new DeleteService(config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

        if (deleteService.delete(id)) {
            out.println("Delete Success");
        } else {
            out.println("something is wrong");
        }

        req.getRequestDispatcher("/View").include(req, resp);
    }


}
