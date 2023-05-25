# Design Document



## _Citrus_

## 1. Problem Statement
Often times, people with disabilities find themselves not going out as much due to the lack of
accessibility information provided by businesses. Citrus aims to improve this by not only recommending  
the user places based on their specific accessibility needs, but also by certain criteria provided
by the user(eg. Restaurants,Music Venues etc.). Citrus also aims to alleviate worry by implementing a user based verification/validation functionality to help clarify the accessibility accommodations that places offer.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. How the user validated/verified info be stored? Will it be added to the original venue table? or be stored in a separate table all together?
2.
3.

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. _As a user I want to be able to create a profile._
U1.1. _As a user I want to be able to edit my profile._
U2. _As a user I want to search places near me by general term (restaurant,music etc.)_
U3. _As a user I want to search places based on accessibility accommodations (filter)_
U4. _As a user I want to add/verify accessibility tags to a place that is recommended._
U5._(extension) As a user I want to see what places I have contributed tags to_
U6._(extension) As a user I want to leave a comment on any extra accessibility information a place might have_
U7.
U8.
U9.

## 4. Project Scope

_Clarify which parts of the problem you intend to solve. It helps reviewers know what questions to ask to make sure you are solving for what you say and stops discussions from getting sidetracked by aspects you do not intend to handle in your design._

### 4.1. In Scope
_Creating and viewing your profile. Searching Points of Interests, by name, PoI type, or accessibility tags. Adding and verifying accessibility tags to PoI._

### 4.2. Out of Scope
_Directions to PoI, Friend list, seeing other profiles who have contributed_

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._



# 6. API

## 6.1. Public Models

// ProfileModel
String userId;
Set <String> userInterests;

//PlaceOfInterestModel
String placeName;
String placeId;
String placeAddress;
Set<String> accessibilityInfo;
Set<String> placeTypes;

//UserModel
String fullName;
String emailAddress;
String gender;
String dateOfBirth;


## 6.2.  _Get / View Profile
* Accepts 'GET' RQs to /profile/:userId
* Accepts an userId and returns a list of userInterests added by that user.
    * if the given user has not created any list -> empty list will be returned;

## 6.3 _Create Profile
* Accepts 'POST' RQs to /profile/
* Accepts data to create a new profile with user details such as fullName,
  emailAddress, gender, dateOfBirth. Returns a new profile with a unique userId;

## 6.3 _Update Profile
* Accepts 'PUT' RQs to /profile/:?userId=userId&isNew=false
* Accepts data to update a profile. Returns the updated
  profile.

## 6.4 _Update Profile_Interests List
* Accepts 'PUT' RQs to /profile/:userId
* Accepts data to update a list of interests. Returns the updated
  list of interests.

## 6.5 Get PointOfInterest
* Accepts 'GET' requests to /places/:placeId
* Accepts an pointId and Returns the corresponding POI details : placeName, address, type,
  and list of accessibility info tags.
    * If the POI is not found, will throw an 'PlaceNotFoundException'

## 6.6 Create Accessibility Tags to POI
* Accepts a 'POST' request to /places/:placeId/createTags/
* Accepts data to create accessibility tags.
  Returns the new POI, with updated Accessibility Tags.

## 6.7 Update Accessibility Tags to POI
* Accepts a 'PUT' request to /places/:placeId/updateTags/
* Accepts data to update the accessibility tags.
  Returns the new POI, with updated Accessibility Tags.


## 6.11 Get User Endpoint

* Accepts GET requests to /User/:?userId=userId&email=email&dob=dob
* Accept userId,email, and Date of Birth then return the User information.

## 6. Create User Endpoint
* Accepts 'POST' requests to '/User/createUser/:?userId=userId&email=email&dob=dob
* Accept userId,email, and Date of Birth then create the User with given info.


# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._
`PointOfInterest`
``` 
pointName //  string;
pointId // partition key , string;
pointAddress // string;
pointTypes // Set<String>;
accessibilityInfo // Set<String>;

```
`User`
```
userId // partition key, string
firstName // string;
lastName // string;
emailAddress // string;
gender // string;
dateOfBirth // LocalDate;
userInterests // Set <String>;
```