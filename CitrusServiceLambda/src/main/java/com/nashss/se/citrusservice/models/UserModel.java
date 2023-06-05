package com.nashss.se.citrusservice.models;

import java.util.Objects;
import java.util.Set;
//CHECKSTYLE:OFF
public class UserModel {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String dateOfBirth;
    private final Set<String> interests;


    public UserModel(String userId, String firstName,
                     String lastName,
                     String gender, String dateOfBirth,
                     Set<String> interests) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.interests = interests;

    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Set<String> getInterests() {
        return interests;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return userId.equals(that.userId) && interests.equals(that.interests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, interests);
    }


    public static UserModel.Builder builder() {
        return new UserModel.Builder();
    }

    public static class Builder {
        private String userId;
        private String firstName;
        private String lastName;
        private String gender;
        private String dateOfBirth;
        private Set<String> interests;


        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withInterests(Set<String> interests) {
            this.interests = interests;
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, firstName,
                    lastName, gender, dateOfBirth, interests);
        }
    }
}
