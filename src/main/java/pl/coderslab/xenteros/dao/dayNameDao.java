package pl.coderslab.xenteros.dao;

import pl.coderslab.xenteros.model.Book;
import pl.coderslab.xenteros.model.dayName;
import pl.coderslab.xenteros.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dayNameDao {
    private static final String FIND_ALL_BOOKS_QUERY = "SELECT * FROM day_name;";

    public List<dayName> findAll() {
        List<dayName> dayNamesList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BOOKS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                dayName dayNameToAdd = new dayName();
                dayNameToAdd.setId(resultSet.getInt("id"));
                dayNameToAdd.setName(resultSet.getString("name"));
                dayNameToAdd.setDisplay_order(resultSet.getInt("display_order"));

                dayNamesList.add(dayNameToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNamesList;
    }

    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM day_name WHERE name = ?";


    public dayName read(String name) {

        dayName dayName = new dayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_BY_NAME_QUERY)) {

            insertStm.setString(1,name);
            ResultSet resultSet = insertStm.executeQuery();
            while  (resultSet.next()) {
                dayName.setId(resultSet.getInt("id"));
                dayName.setName(resultSet.getString("name"));
                dayName.setDisplay_order(resultSet.getInt("display_order"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayName;
    }
}
