package main.java.Servlet;

import main.java.Bean.EmpBean;
import main.java.Service.ViewService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewServlet",urlPatterns = "/View")
public class ViewServlet extends HttpServlet {
    private ViewService viewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
            viewService = new ViewService(config.getServletContext());
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        out.println("<a href='/index.html'>Add New Employee</a>");
        out.println("<h1>Employees List</h1>");

        List<EmpBean> empList = viewService.getAllEmployees();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");

        for (EmpBean e : empList) {
            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getEmail()+"</td><td>"+e.getCountry()+"</td><td><a href='Edit?id="+e.getId()+"'>edit</a></td> <td><a href='Delete?id="+e.getId()+"'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}
