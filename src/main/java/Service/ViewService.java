package main.java.Service;

import main.java.Bean.EmpBean;
import main.java.Dao.EmpDao;

import javax.servlet.ServletContext;
import java.util.List;

public class ViewService {
    private EmpDao empDao;

    public ViewService(ServletContext ctx) {
        empDao = new EmpDao(ctx);
    }

    public List<EmpBean> getAllEmployees() {
        return empDao.getAllEmployees();
    }
}
