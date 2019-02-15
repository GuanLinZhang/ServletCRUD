package main.java.Bean;

public class EmpBean {
    private int id;
    private String name;
    private String password;
    private String email;

    public EmpBean() {
    }

    public EmpBean(int id, String name, String password, String email, String country) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.country = country;
    }

    public EmpBean(String name, String password, String email, String country) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
