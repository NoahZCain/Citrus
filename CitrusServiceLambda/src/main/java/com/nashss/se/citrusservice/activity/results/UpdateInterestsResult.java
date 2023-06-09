package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.UserModel;

public class UpdateInterestsResult {
    private final UserModel userModel;

    private UpdateInterestsResult(UserModel userModel){
        this.userModel = userModel;
    }
    public UserModel getUserModel(){
        return userModel;
    }

    @Override
    public String toString() {
        return "UpdateUserResult{" +
                "userModel=" + userModel +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{

        private UserModel userModel;

        public Builder withUserModel(UserModel userModel){
            this.userModel = userModel;
            return this;
        }
        public UpdateInterestsResult build(){
            return new UpdateInterestsResult(userModel);
        }
    }
}
