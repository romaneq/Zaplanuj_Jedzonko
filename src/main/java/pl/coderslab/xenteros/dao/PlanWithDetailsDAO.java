package pl.coderslab.xenteros.dao;

import pl.coderslab.xenteros.model.PlanWithDetails;
import pl.coderslab.xenteros.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanWithDetailsDAO {

    private static final String READ_PLAN_DETAILS ="SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, recipe.id\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";


    public List<PlanWithDetails> readPlanWithDetails(int planId){
        List<PlanWithDetails> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_DETAILS);
        ) {
            statement.setInt(1, planId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlanWithDetails planToAdd = new PlanWithDetails();
                planToAdd.setDayName(resultSet.getString("day_name"));
                planToAdd.setMealName(resultSet.getString("meal_name"));
                planToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planToAdd.setRecipeId(resultSet.getInt("id"));

                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }
}
