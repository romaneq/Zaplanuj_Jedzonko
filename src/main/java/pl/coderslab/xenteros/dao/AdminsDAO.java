package pl.coderslab.xenteros.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.xenteros.model.Admins;
import pl.coderslab.xenteros.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminsDAO {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin, enable) VALUES (?, ?, ?,?,?,?)";

    public Admins create(Admins admins) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, admins.getFirstName());
            statement.setString(2, admins.getLastName());
            statement.setString(3, admins.getEmail());
            statement.setString(4, admins.getPassword());
            statement.setInt(5, admins.getSuperadmin());
            statement.setBoolean(6, admins.getEnable());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admins.setId(resultSet.getInt(1));
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id= ?";

    public Admins read(int adminId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_ADMIN_QUERY);
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admins admins = new Admins();
                admins.setId(resultSet.getInt("id"));
                admins.setFirstName(resultSet.getString("first_name"));
                admins.setLastName(resultSet.getString("first_name"));
                admins.setEmail(resultSet.getString("email"));
                admins.setPassword(resultSet.getString("password"));
                admins.setSuperadmin(resultSet.getInt("superadmin"));
                admins.setEnable(resultSet.getBoolean("enable"));
                return admins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?, last_name=?, email=?, " +
            "password=?, superadmin=?, enable=? where id = ?";

    public void update(Admins admins) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_ADMIN_QUERY);
            statement.setString(1, admins.getFirstName());
            statement.setString(2, admins.getLastName());
            statement.setString(3, admins.getEmail());
            statement.setString(4, admins.getPassword());
            statement.setInt(5, admins.getSuperadmin());
            statement.setBoolean(6, admins.getEnable());
            statement.setInt(7, admins.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id=?";

    public void delete(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ADMIN_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins";

    public List<Admins> findAll() {
        List<Admins> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admins adminsToAdd = new Admins();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirstName(resultSet.getString("first_name"));
                adminsToAdd.setLastName(resultSet.getString("last_name"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPassword(resultSet.getString("password"));
                adminsToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getBoolean("enable"));
                adminsList.add(adminsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }

    private static final String FIND_ADMINS_PASSWORD_QUERY = "SELECT password FROM admins WHERE email = ?";

    public boolean checking(String email, String candidate) {
        String hashed = "";
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ADMINS_PASSWORD_QUERY);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hashed = resultSet.getString("password");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (BCrypt.checkpw(candidate, hashed)) {
            return true;
        }
        return false;
    }

    private static final String FIND_ADMIN_QUERY = "SELECT * FROM admins WHERE email= ?";

    public Admins findAdmin(String adminEmail) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ADMIN_QUERY);
            statement.setString(1,adminEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admins admins = new Admins();
                admins.setId(resultSet.getInt("id"));
                admins.setFirstName(resultSet.getString("first_name"));
                admins.setLastName(resultSet.getString("first_name"));
                admins.setEmail(resultSet.getString("email"));
                admins.setPassword(resultSet.getString("password"));
                admins.setSuperadmin(resultSet.getInt("superadmin"));
                admins.setEnable(resultSet.getBoolean("enable"));
                return admins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

