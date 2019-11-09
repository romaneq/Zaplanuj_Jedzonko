package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.RecipeDao;
import pl.coderslab.xenteros.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/list")
public class RecipeList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.findAll();
        request.setAttribute("recipeList", recipeList);

        getServletContext().getRequestDispatcher("/recipeList.jsp").forward(request, response);
    }
}
