package main.java.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "ContentTypeFilter",urlPatterns = "/*")
public class ContentTypeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        resp.setContentType("text/html");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
