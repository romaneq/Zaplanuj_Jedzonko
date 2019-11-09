package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(Integer.parseInt(request.getParameter("id")));
        plan.setName(request.getParameter("name"));
        plan.setDescription(request.getParameter("description"));

        planDao.update(plan);
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PlanDao planDao = new PlanDao();
Plan plan = planDao.read(Integer.parseInt(request.getParameter("id")));

request.setAttribute("plan", plan);
getServletContext().getRequestDispatcher("/editPlan.jsp").forward(request, response);

    }
}

