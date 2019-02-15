package main.java.Service;

import main.java.Dao.EmpDao;

import javax.servlet.ServletContext;
import java.sql.Connection;

public class DeleteService {
    private EmpDao empDao;

    public DeleteService(ServletContext ctx) {
        empDao = new EmpDao(ctx);
    }

    public boolean delete(int id) {
        int status = empDao.delete(id);
        return status == 200;
    }
}
