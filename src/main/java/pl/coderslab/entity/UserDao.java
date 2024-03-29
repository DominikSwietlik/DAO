package pl.coderslab.entity;

import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO workshop2.users(username, email, password) VALUES (?,?,?);";
    private static final String READ_USER_QUERY =
            """
                    select id,  username, email, password
                    from workshop2.users
                    where id = (?);""";

    private static final String UPDATE_USER_QUERY =
            """
                    UPDATE workshop2.users
                    set  workshop2.users.username = (?),workshop2.users.email = (?), workshop2.users.password = (?)
                    where id = (?);
                    """;
    private static final String DELETE_USER_QUERY =
            """
                    DELETE workshop2.users
                    FROM workshop2.users
                    where id = (?);
                    """;
    private static final String FIND_ALL_USER_QUERY =
            """
                    SELECT *
                    FROM workshop2.users;
                    """;

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String hashPassword(String password) {
        password = "password";

        return password;
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(hashPassword(resultSet.getString("password")));
                //BCrypt.hashpw(, BCrypt.gensalt())
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }
    public void findAll() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_USER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);

            }

        } catch (SQLException e) {
        }

        for(User user : users)
        {
            System.out.println(user.toString());
        }

    }
}