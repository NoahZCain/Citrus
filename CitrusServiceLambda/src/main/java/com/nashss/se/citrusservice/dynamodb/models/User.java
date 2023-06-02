package com.nashss.se.citrusservice.dynamodb.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "user")
public class User {
    private  String userId;
    private  String firstName;
    private  String lastName;
    private  String gender;
    private  String dateOfBirth;
    private  Set<String> userInterests;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute(attributeName = "dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @DynamoDBAttribute(attributeName = "userInterests")
    public Set<String> getUserInterests(){
        return userInterests;
    }
    public void setUserInterests(Set<String> userInterests){
        this.userInterests = userInterests;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && gender.equals(user.gender) && dateOfBirth.equals(user.dateOfBirth) && userInterests.equals(user.userInterests);
    }
    @Override
    public int hashCode(){
        return Objects.hash(userId,firstName,lastName,gender,dateOfBirth,userInterests);
    }
}