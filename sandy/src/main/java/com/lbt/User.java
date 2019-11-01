package com.lbt;

import java.time.LocalDate;

public class User {

    String firstName;
    String lastName;
    LocalDate dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder withDateOfBirth(LocalDate dateOfBirth) {
            user.setDateOfBirth(dateOfBirth);
            return this;
        }

        public User build() {
            return user;
        }
    }
}