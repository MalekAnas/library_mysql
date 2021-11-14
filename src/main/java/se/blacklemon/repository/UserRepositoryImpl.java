package se.blacklemon.repository;

import se.blacklemon.config.DatabaseConfig;
import se.blacklemon.model.user.User;
import se.blacklemon.model.user.UserBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void saveUser(User user) {
        try {
            String addUserQuery = "INSERT INTO users (id, f_name, l_name, email, password, staff) VALUES( ?, ? , ?, ?, ?, ?)";
            PreparedStatement prepareStatement = DatabaseConfig.prepareStatement(addUserQuery);

            prepareStatement.setString(1, user.getId());
            prepareStatement.setString(2, user.getFirstName());
            prepareStatement.setString(3, user.getLastName());
            prepareStatement.setString(4, user.getEmail());
            prepareStatement.setString(5, user.getPassword());
            prepareStatement.setBoolean(6, user.isStaff());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String emailToken) throws SQLException {
        User user = new User();

        boolean staff = false;
        String fetchUserName = "SELECT * FROM users WHERE (email= ?) ";
        PreparedStatement ps = DatabaseConfig.prepareStatement(fetchUserName);


        ps.setString(1, emailToken.toLowerCase());

        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("no user found");
        }
        while (rs.next()) {
            user = new UserBuilder()
                    .withUniqueId(rs.getString("id"))
                    .withFirstName(rs.getString("f_name"))
                    .withLastName(rs.getString("l_name"))
                    .withEmail(rs.getString("email"))
                    .withEncodedPassword(rs.getString("password"))
                    .isStaff(rs.getBoolean("staff"))
                    .build();
        }
        return user;
    }

    @Override
    public boolean isEmailValid(String emailToken) throws SQLException {
        String fetchTokenQuery = "SELECT * FROM users WHERE (email= ?)";
        PreparedStatement ps = DatabaseConfig.prepareStatement(fetchTokenQuery);
        ps.setString(1, emailToken.toLowerCase());
        ResultSet rs = ps.executeQuery();

        return !rs.next();
    }
}