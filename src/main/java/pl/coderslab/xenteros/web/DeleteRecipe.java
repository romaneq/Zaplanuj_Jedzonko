package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.RecipeDao;
import pl.coderslab.xenteros.dao.RecipePlanDAO;
import pl.coderslab.xenteros.model.Recipe;
import pl.coderslab.xenteros.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/delete")
public class DeleteRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        String delete = request.getParameter("delete");
        if (delete.equals("ok")) {
            int recipeId = Integer.parseInt(request.getParameter("recipeId"));
            recipeDao.delete(recipeId);
        }
        List<Recipe> recipeList = recipeDao.findAll();
        request.setAttribute("recipeList", recipeList);
        getServletContext().getRequestDispatcher("/recipeList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String recipeId = request.getParameter("recipeId");
        RecipePlanDAO recipePlanDao = new RecipePlanDAO();
        List<RecipePlan> byRecipe = recipePlanDao.findByRecipe(Integer.parseInt(recipeId));

        if (byRecipe.size() == 0) {
            request.setAttribute("recipeId", recipeId);
            getServletContext().getRequestDispatcher("/deleteRecipe.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/notDeleteRecipe.jsp").forward(request, response);
        }
    }

}
