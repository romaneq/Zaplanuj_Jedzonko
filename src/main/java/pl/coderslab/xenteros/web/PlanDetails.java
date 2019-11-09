package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.dao.PlanWithDetailsDAO;
import pl.coderslab.xenteros.model.Plan;
import pl.coderslab.xenteros.model.PlanWithDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int planId = Integer.parseInt(request.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);

        PlanWithDetailsDAO planWithDetailsDAO = new PlanWithDetailsDAO();
        List<PlanWithDetails> planList = planWithDetailsDAO.readPlanWithDetails(planId);
        request.setAttribute("planList", planList);

        Set<String> planDayNamesSet = new HashSet<>();
        for(PlanWithDetails p : planList){
            planDayNamesSet.add(p.getDayName());
        }
        request.setAttribute("planDayNamesSet", planDayNamesSet);



        getServletContext().getRequestDispatcher("/planDetails.jsp").forward(request, response);


    }
}
