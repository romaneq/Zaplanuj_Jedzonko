package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlanList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAll();
        request.setAttribute("planList", planList);
        getServletContext().getRequestDispatcher("/planList.jsp").forward(request, response);
    }
}
