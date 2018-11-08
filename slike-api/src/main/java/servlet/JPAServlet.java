package servlet;


import beans.SlikeBeans;
import entities.Slika;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    public SlikeBeans slikeBeans;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Slika> slikaList = slikeBeans.getSlikaList();

        // izpis uporabnikov na spletno stran
        resp.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "JPA Servlet" + "</h1>");

        if (slikaList == null || slikaList.isEmpty())
            resp.getWriter().println("No uporabnik found.");
        else {
            for (Slika u : slikaList) {
                resp.getWriter().println(u.toString());
                resp.getWriter().println("<br>");
                log(u.toString());
            }
        }
    }



}
