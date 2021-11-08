package se.blacklemon.repository;

import se.blacklemon.model.user.User;

import java.sql.SQLException;

public interface UserRepository {
    void saveUser(User user);
    User getUser(String emailToken) throws SQLException;
    boolean isEmailValid(String emailToken) throws SQLException;
}
