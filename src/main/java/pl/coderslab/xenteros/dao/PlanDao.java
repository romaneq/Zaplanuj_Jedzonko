package pl.coderslab.xenteros.dao;

import pl.coderslab.xenteros.exception.NotFoundException;
import pl.coderslab.xenteros.model.Plan;
import pl.coderslab.xenteros.model.PlanDaysAndRecipes;
import pl.coderslab.xenteros.model.PlanWithDetails;
import pl.coderslab.xenteros.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description, created, admin_id) VALUES (?,?,?, ?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLAN_QUERY = "SELECT * FROM plan;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE id = ?;";
    private static final String USER_PLAN_ADDED = "SELECT COUNT(*) as amount from plan where admin_id = ?";
    private static final String SHOW_LAST_ADDED_PLAN_NAME = "SELECT name from plan where id = (SELECT MAX(id) from plan WHERE admin_id = ?);";
    private static final String SHOW_PLAN = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, recipe.id as recipe_id\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) \n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdmin_id(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }


    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }


    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdmin_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdmin_id());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countUserPlanAmount(int admin_id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_PLAN_ADDED)) {

            statement.setInt(1, admin_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                int recipeAmount = 0;
                while (resultSet.next()) {
                    recipeAmount = resultSet.getInt("amount");
                }
                return recipeAmount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<PlanWithDetails> showLastAddedPlan(Integer adminId) {
        List<PlanWithDetails> listPlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_PLAN)
        ) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            {
                while (resultSet.next()) {
                    PlanWithDetails planToAdd = new PlanWithDetails();
                    planToAdd.setDayName(resultSet.getString("day_name"));
                    planToAdd.setMealName(resultSet.getString("meal_name"));
                    planToAdd.setRecipeName(resultSet.getString("recipe_name"));
                    planToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                    planToAdd.setRecipeId(resultSet.getInt("recipe_id"));

                    listPlan.add(planToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPlan;

    }

    public String getShowLastAddedPlanName(Integer adminId) {
        String planName="brak dodanych planów";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_LAST_ADDED_PLAN_NAME)
        ) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 planName = resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planName;

    }

}



