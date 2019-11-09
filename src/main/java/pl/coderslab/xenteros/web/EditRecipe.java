package pl.coderslab.xenteros.web;
import pl.coderslab.xenteros.dao.RecipeDao;
import pl.coderslab.xenteros.model.Admins;
import pl.coderslab.xenteros.model.Recipe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(Integer.parseInt(request.getParameter("id")));
        recipe.setName(request.getParameter("name"));
        recipe.setDescription(request.getParameter("description"));
        recipe.setPreparation_time(Integer.parseInt(request.getParameter("preparationTime")));
        recipe.setPreparation(request.getParameter("preparation"));
        recipe.setIngredients(request.getParameter("ingredients"));
        recipe.setCreated(request.getParameter("created"));

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        recipe.setAdmin_id(admin.getId());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();
        recipe.setUpdated(df.format(dateobj));

        recipeDao.update(recipe);

        response.sendRedirect("/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();

        request.setAttribute("recipe",recipeDao.read(id));

        getServletContext().getRequestDispatcher("/editRecipe.jsp").forward(request, response);
    }
}
