package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class DeletePlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        if (delete.equals("ok")) {
            int planId = Integer.parseInt(request.getParameter("planId"));
            PlanDao planDao = new PlanDao();
            planDao.delete(planId);
        }
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("planId",request.getParameter("planId"));
        getServletContext().getRequestDispatcher("/deletePlan.jsp").forward(request, response);
    }
}
