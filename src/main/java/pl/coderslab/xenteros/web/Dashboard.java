package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.dao.RecipeDao;
import pl.coderslab.xenteros.model.Admins;
import pl.coderslab.xenteros.model.PlanWithDetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");

        PlanDao planDao1 = new PlanDao();
        List<PlanWithDetails> plan = planDao1.showLastAddedPlan(admin.getId());
        request.setAttribute("plan",plan);
        request.setAttribute("planName", planDao1.getShowLastAddedPlanName(admin.getId()));
        request.setAttribute("countPlan",planDao1.countUserPlanAmount(admin.getId()));
        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("countRecipe",recipeDao.countUserRecipeAmount(admin.getId()));

        Set<String> planDayNamesSet = new HashSet<>();
        for(PlanWithDetails p : plan){
            planDayNamesSet.add(p.getDayName());
        }
        request.setAttribute("planDayNamesSet", planDayNamesSet);

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}

