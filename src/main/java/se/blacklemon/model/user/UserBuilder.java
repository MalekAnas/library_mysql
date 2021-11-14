package se.blacklemon.model.user;

import static se.blacklemon.utils.UniqueIdGenerator.createUniqueId;

public class UserBuilder<SELF extends UserBuilder<SELF>> {

    protected User user = new User();

    public SELF withUniqueId() {
        user.setId(createUniqueId());
        return self();
    }

    public SELF withUniqueId(String id) {
        user.setId(id);
        return self();
    }

    public SELF withFirstName(String firstName) {
        user.setFirstName(firstName);
        return self();
    }

    public SELF withLastName(String lastName) {
        user.setLastName(lastName);
        return self();
    }

    public SELF withEmail(String email) {
        user.setEmail(email);
        return self();
    }

    public SELF withEncodedPassword(String password) {
        user.setPassword(password);
        return self();
    }

    public SELF isStaff(boolean isStaff) {
        user.setStaff(isStaff);
        return self();
    }

    public User build() {
        return user;
    }

    protected SELF self() {
        return (SELF) this;
    }
}
