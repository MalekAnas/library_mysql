package se.blacklemon;

import se.blacklemon.model.user.User;
import se.blacklemon.model.user.UserBuilder;
import se.blacklemon.repository.UserRepositoryImpl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        User user = new UserBuilder()
                .withFirstName("Alina")
                .withLastName("Minkova")
                .withEmail("alina@gmail.com")
                .withPassword("aswqwe")
                .isStaff(true)
                .withUniqueId()
                .build();

        System.out.println(user);

        String st= "685ae8d6-9cd9-4a13-ac9d-887e688422d1";
        char[] str = st.toCharArray();
        int counter = 0;

        for (char c : str) {
            if (c != ' ')
                counter++;
        }

        System.out.println(counter);

        UserRepositoryImpl rep= new UserRepositoryImpl();

        rep.saveUser(user);

        User user2 = rep.getUser("malek@mail.com");
        System.out.println(user2);
    }

}
