package pl.coderslab.xenteros.dao;

import pl.coderslab.xenteros.model.Admins;
import pl.coderslab.xenteros.model.RecipePlan;
import pl.coderslab.xenteros.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDAO {

    private static final String FIND_PLAN_BY_RECIPE_QUERY = "SELECT * FROM recipe_plan WHERE recipe_id = ?";
    private static final String DELETE_RECIPE_BY_ID_QUERY= "DELETE FROM recipe_plan WHERE plan_id = ? AND recipe_id = ?";

    public List<RecipePlan> findByRecipe(int recipeId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_PLAN_BY_RECIPE_QUERY)) {
            insertStm.setInt(1, recipeId);
            ResultSet resultSet = insertStm.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipePlanToAdd = new RecipePlan();
                recipePlanToAdd.setId(resultSet.getInt("id"));
                recipePlanToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlanToAdd.setMealName(resultSet.getString("meal_name"));
                recipePlanToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlanToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlanToAdd.setPlanId(resultSet.getInt("plan_id"));
                recipePlanList.add(recipePlanToAdd);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    private static final String CREATE_RECIPE_PLAN = "INSERT INTO recipe_plan\n" +
            "(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?)";

    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_RECIPE_PLAN, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                recipePlan.setId(resultSet.getInt(1));
            }
            return recipePlan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void removeRecipeByIdMealDay(int planId, int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(DELETE_RECIPE_BY_ID_QUERY)) {
            insertStm.setInt(1,planId);
            insertStm.setInt(2, recipeId);
            insertStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
