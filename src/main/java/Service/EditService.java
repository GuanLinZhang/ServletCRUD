package main.java.Service;

import main.java.Bean.EmpBean;
import main.java.Dao.EmpDao;

import javax.servlet.ServletContext;

public class EditService {
    private EmpDao empDao;

    public EditService(ServletContext ctx) {
        empDao = new EmpDao(ctx);
    }

    public boolean update(EmpBean e) {
        int status = empDao.update(e);
        return status == 200;
    }

    public EmpBean getEmployeeByID(int id) {
        return empDao.getEmployeeByID(id);
    }
}
