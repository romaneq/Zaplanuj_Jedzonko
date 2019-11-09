package pl.coderslab.xenteros.web;


import pl.coderslab.xenteros.dao.RecipePlanDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/recipe/delete")
public class DeleteFromPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        if (delete.equals("ok")) {

            RecipePlanDAO recipePlanDao = new RecipePlanDAO();
            recipePlanDao.removeRecipeByIdMealDay(planId, recipeId);
        }
        response.sendRedirect("/app/plan/details?id="+planId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("planId",request.getParameter("planId"));
        request.setAttribute("recipeId",request.getParameter("recipeId"));

        getServletContext().getRequestDispatcher("/deleteFromPlan.jsp").forward(request, response);
    }
}
