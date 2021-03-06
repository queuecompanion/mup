package com.queuecompanion.mup.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.queuecompanion.mup.dto.validators.PasswordMatches;
import com.queuecompanion.mup.dto.validators.ValidPassword;
import com.queuecompanion.mup.dto.validators.ValidUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonDeserialize(builder = RegisterRequest.Builder.class)
@PasswordMatches
public class RegisterRequest {
    @ValidUsername
    private final String username;

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address input")
    private final String emailAddress;

    @ValidPassword
    private final String password;

    private final String matchingPassword;

    @NotBlank(message = "First name is required")
    @Size(max = 30)
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 30)
    private final String lastName;

    // TODO: rework
    private final String isoCountryCode;

    private RegisterRequest(Builder builder) {
        this.username = builder.username;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.matchingPassword = builder.matchingPassword;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.isoCountryCode = builder.isoCountryCode;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isoCountryCode='" + isoCountryCode + '\'' +
                '}';
    }

    // step builder - https://blog.jayway.com/2012/02/07/builder-pattern-with-a-twist/
    public static UsernameStep builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder implements UsernameStep, EmailStep, PasswordStep, MatchingPasswordStep, FirstNameStep, LastNameStep, Build {
        private String username;
        private String emailAddress;
        private String password;
        private String matchingPassword;
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
        public MatchingPasswordStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public FirstNameStep withMatchingPassword(String matchingPassword) {
            this.matchingPassword = matchingPassword;
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
        public RegisterRequest build() {
            return new RegisterRequest(this);
        }
    }

    public interface UsernameStep {
        EmailStep withUsername(String username);
    }

    public interface EmailStep {
        PasswordStep withEmailAddress(String emailAddress);
    }

    public interface PasswordStep {
        MatchingPasswordStep withPassword(String password);
    }

    public interface MatchingPasswordStep {
        FirstNameStep withMatchingPassword(String matchingPassword);
    }

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
    }

    public interface LastNameStep {
        Build withLastName(String lastName);
    }

    public interface Build {
        Build withIsoCountryCode(String isoCountryCode);
        RegisterRequest build();
    }
}
