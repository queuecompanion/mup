package com.queuecompanion.mup.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

// TODO
//   https://ppbruna.medium.com/why-you-should-not-use-lombok-f7556662e8c3 TELL DON'T ASK principle
//   seems like an interesting read
@Entity
@Table(name = "users")
public class User {
    // TODO: if needed, introduce separate attribute as Id
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // TODO: rework
    @Column(name = "iso_country_code")
    private String isoCountryCode;

    protected User() {
    }

    public User(String test, String username, String emailAddress, String password, String firstName, String lastName, String isoCountryCode) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isoCountryCode = isoCountryCode;
    }

    private User(Builder builder) {
        this.username = builder.username;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.isoCountryCode = builder.isoCountryCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isoCountryCode='" + isoCountryCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(isoCountryCode, user.isoCountryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, emailAddress, password, firstName, lastName, isoCountryCode);
    }

    public static UsernameStep builder() {
        return new Builder();
    }

    // step builder - https://blog.jayway.com/2012/02/07/builder-pattern-with-a-twist/
    static class Builder implements UsernameStep, EmailStep, PasswordStep, FirstNameStep, LastNameStep, Build {
        private String username;
        private String emailAddress;
        private String password;
        private String firstName;
        private String lastName;
        // TODO: rework
        private String isoCountryCode;

        @Override
        public EmailStep withUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public PasswordStep withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        @Override
        public FirstNameStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public LastNameStep withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public Build withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public Build withIsoCountryCode(String isoCountryCode) {
            this.isoCountryCode = isoCountryCode;
            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }
    }

    public interface UsernameStep {
        EmailStep withUsername(String username);
    }

    public interface EmailStep {
        PasswordStep withEmailAddress(String emailAddress);
    }

    public interface PasswordStep {
        FirstNameStep withPassword(String password);
    }

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
    }

    public interface LastNameStep {
        Build withLastName(String lastName);
    }

    public interface Build {
        Build withIsoCountryCode(String isoCountryCode);
        User build();
    }
}
