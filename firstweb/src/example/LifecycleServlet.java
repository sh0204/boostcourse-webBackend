package example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LifecycleServlet", value = "/LifecycleServlet")
public class LifecycleServlet extends HttpServlet {
    public LifecycleServlet() {
        System.out.println("LifecycleServlet 생성!!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service 호출");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init 호출");
    }


    @Override
    public void destroy() {
        System.out.println("destroy 호출!!");
    }


}
