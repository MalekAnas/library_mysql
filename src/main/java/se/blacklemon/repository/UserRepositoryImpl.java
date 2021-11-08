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
        String firstName = "";
        String lastName = "";
        String email = "";
        String password = "";
        String id = "";
        boolean staff = false;
        String fetchUserName = "SELECT * FROM users WHERE (email= ?) ";
        PreparedStatement ps = DatabaseConfig.prepareStatement(fetchUserName);


        ps.setString(1, emailToken);

        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("no user found");
        }
        while (rs.next()) {
            firstName = rs.getString("f_name");
            lastName = rs.getString("l_name");
            password = rs.getString("password");
            email = rs.getString("email");
            id = rs.getString("id");
            staff = rs.getBoolean("staff");
        }

        User user = new UserBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .isStaff(staff)
                .build();

        user.setId(id);

        return user;
    }

    @Override
    public boolean isEmailValid(String emailToken) throws SQLException {
        String fetchTokenQuery = "SELECT * FROM users WHERE (email= ?)";
        PreparedStatement ps = DatabaseConfig.prepareStatement(fetchTokenQuery);
        ps.setString(1, emailToken);
        ResultSet rs = ps.executeQuery();

        return !rs.next();
    }
}