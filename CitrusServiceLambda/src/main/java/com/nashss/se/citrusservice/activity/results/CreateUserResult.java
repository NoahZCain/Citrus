package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.UserModel;

public class CreateUserResult {
    private final UserModel userModel;

    private CreateUserResult(UserModel userModel){
        this.userModel = userModel;
    }
    public UserModel getUserModel(){
        return userModel;
    }

    @Override
    public String toString() {
        return "CreateUserResult{" +
                "userModel=" + userModel +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private UserModel userModel;

        public Builder withUser(UserModel userModel){
            this.userModel = userModel;
            return this;
        }
        public CreateUserResult build(){
            return new CreateUserResult(userModel);
        }
    }
}
