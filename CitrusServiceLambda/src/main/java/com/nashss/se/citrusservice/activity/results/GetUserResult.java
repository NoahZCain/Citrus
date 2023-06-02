package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.UserModel;

public class GetUserResult {
    private final UserModel userModel;

    public GetUserResult(UserModel userModel){
        this.userModel = userModel;
    }

    public UserModel getUserModel(){
        return userModel;
    }

    @Override
    public String toString() {
        return "GetUserResult{" +
                "userModel=" + userModel +
                '}';
    }
    public static class Builder {
        private UserModel userModel;

        public Builder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public GetUserResult build() {
            return new GetUserResult(userModel);
        }
    }
        public static Builder builder(){
            return new Builder();
        }
    }

