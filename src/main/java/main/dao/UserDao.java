package main.dao;

import main.models.GameStatistics;
import main.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public long storeUser(String nickname, String login, String password) throws SQLException {
        String insertTableSQL = "INSERT INTO USERS (NICKNAME, LOGIN, PASSWORD) VALUES(?,?,?)";
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nickname);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);
        long userId;
        preparedStatement.executeUpdate();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        preparedStatement.close();
        dbConnection.close();
        return userId;
    }

    public void updateGameStatistics(long userId, int attemptsCount) throws SQLException {
        String insertTableSQL =
                "UPDATE USERS SET GAMES_COUNT = GAMES_COUNT+ 1,ATTEMPTS_COUNT = ATTEMPTS_COUNT + ?  WHERE ID = ?";
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, attemptsCount);
        preparedStatement.setLong(2, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnection.close();
    }

    public boolean isNicknameUnique(String nickname) throws SQLException {
        String insertTableSQL = "SELECT NICKNAME from USERS where NICKNAME = ?";
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, nickname);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return false;
        }
        preparedStatement.close();
        dbConnection.close();
        return true;
    }

    public boolean isLoginUnique(String login) throws SQLException {
        String insertTableSQL = "SELECT LOGIN from USERS where LOGIN = ?";
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return false;
        }
        preparedStatement.close();
        dbConnection.close();
        return true;
    }

    public Long getIdByValidAuthorizationData(String login, String password) throws SQLException {
        String insertTableSQL = "SELECT ID from USERS where LOGIN = ? and PASSWORD = ?";
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong("ID");
        }
        preparedStatement.close();
        dbConnection.close();
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        Connection dbConnection = ConnectionManager.getDbConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(
                "SELECT NICKNAME, GAMES_COUNT, ATTEMPTS_COUNT FROM USERS"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        while (resultSet.next()) {
            String nickname = resultSet.getString("NICKNAME");
            int gamesCount = resultSet.getInt("GAMES_COUNT");
            int attemptsCount = resultSet.getInt("ATTEMPTS_COUNT");
            users.add(new User(nickname, new GameStatistics(gamesCount, attemptsCount)));
        }
        preparedStatement.close();
        dbConnection.close();
        return users;
    }
}
