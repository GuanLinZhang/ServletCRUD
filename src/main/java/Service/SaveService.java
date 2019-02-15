package main.java.Service;

import main.java.Bean.EmpBean;
import main.java.Dao.EmpDao;

import javax.servlet.ServletContext;

public class SaveService {
    private EmpDao empDao;

    public SaveService(ServletContext ctx) {
        empDao = new EmpDao(ctx);
    }

    public boolean save(String name, String password, String email, String country) {
        EmpBean e = new EmpBean(name,password,email,country);
        int status = empDao.save(e);
        return status == 200;
    }

    public EmpBean getEmployeeByID(int id) {
        EmpBean e = empDao.getEmployeeByID(id);
        return e;
    }
}
