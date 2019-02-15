package main.java.Servlet;

import main.java.Bean.EmpBean;
import main.java.Service.EditService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditServlet2",urlPatterns = "/EditServlet2")
public class EditServlet2 extends HttpServlet {
    private EditService editService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        editService = new EditService(config.getServletContext());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String sid = req.getParameter("id");

        int id = Integer.parseInt(sid);
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String Country = req.getParameter("country");

        EmpBean e = new EmpBean(id,name,password,email,Country);

        if (editService.update(e)) {
            out.println("update success");
        } else {
            out.println("something is wrong");
        }

        req.getRequestDispatcher("/Edit").include(req, resp);
    }

}
