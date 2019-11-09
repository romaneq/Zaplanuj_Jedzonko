package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.dao.RecipeDao;
import pl.coderslab.xenteros.dao.RecipePlanDAO;
import pl.coderslab.xenteros.model.Plan;
import pl.coderslab.xenteros.model.Recipe;
import pl.coderslab.xenteros.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class RecipePlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer plan = Integer.parseInt(request.getParameter("choosePla"));
        String name = request.getParameter("name");
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer recipie = Integer.parseInt(request.getParameter("recipie"));
        Integer day = Integer.parseInt(request.getParameter("day"));

        RecipePlan recipePlan = new RecipePlan(recipie, name, number, day, plan);
        RecipePlanDAO recipePlanDao = new RecipePlanDAO();
        recipePlanDao.create(recipePlan);

        getServletContext().getRequestDispatcher("/recipePlanAdd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.findAll();
        request.setAttribute("recipe", recipeList);

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAll();
        request.setAttribute("choosePlan", planList);

        getServletContext().getRequestDispatcher("/recipePlanAdd.jsp").forward(request, response);
    }
}
