import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class ViewUser extends BindingClass{
    constructor(){
        super();
        this.bindClassMethods(['clientLoaded','mount','logout','redirectHomePage','addName','addEmail',
        ,'addGender', 'addInterests', 'addDateOfBirth','redirectEditUser'],this);
        this.dataStore = new DataStore;
        this.client = new CitrusClient();
        this.Header = new Header(this.dataStore);
    }
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const user = await this.client.getUser(identity.email);
        const name = identity.name;
        const email = identity.email;
        const gender = user.userModel.gender;
        const dob = user.userModel.dateOfBirth;
        const userInterests = user.userModel.interests;
        this.dataStore.set("email", email);
        this.dataStore.set("name", name);
        this.dataStore.set("interests", userInterests);
        this.dataStore.set("gender",gender);
        this.dataStore.set("dateOfBirth",dob);
        console.log(dob);
      }
    mount(){
        // document.getElementById('logout').addEventListener('click',this.redirectHomePage);
        this.client = new CitrusClient();
        
        document.getElementById('logout').addEventListener('click',this.logout);
        this.clientLoaded().then(() => {
            this.addName();
            this.addEmail();
            this.addInterests();
            this.addGender();
            this.addDateOfBirth();
            
        });
    }
     redirectHomePage(){
        window.location.href = '/landingPage.html';
    }

    async addName(){
        const nameStored = this.dataStore.get("name"); 
        const nameElement = document.getElementById("name"); 
        console.log(nameStored);
        console.log(nameElement);

        if (nameStored == null) {
            nameElement.innerText = "John Doe";
        } else {
            nameElement.innerText = nameStored;
        }
}
    async addGender() {
        const gender = this.dataStore.get("gender");
        const genderElement = document.getElementById("gender");
        
        if (gender == null) {
        genderElement.innerText = "Gender";
        } else {
        genderElement.innerText = gender;
        }
    }
    async addInterests() {
        const interests = this.dataStore.get("interests");
        const interestsElement = document.getElementById("userInterests");
    
        if (interests == null || interests.length === 0) { // Check if interests array is empty
        interestsElement.innerText = "Add your interests here!";
        } else {
        const interestsList = document.createElement("ul");
    
        interests.forEach((interest) => {
            const listItem = document.createElement("li");
            listItem.innerText = interest;
            interestsList.appendChild(listItem);
        });
    
        interestsElement.appendChild(interestsList);
        }
    }
  
    async addDateOfBirth(){
        const dob = this.dataStore.get("dateOfBirth");
        const dobElement = document.getElementById("dateOfBirth");
        
        if (dob == null) {
        dobElement.innerText = "DateOfBirth";
        } else {
        dobElement.innerText = dob;
        }
    }
  
    async addEmail(){
        const emailStored = this.dataStore.get("email");
        const emailElement = document.getElementById("email");
        console.log(emailStored);
        console.log(emailElement);

        if(emailStored == null){
            emailElement.innerText = "info@example.com"
        } else {
            emailElement.innerText = emailStored;
        }
    }
    async logout(){
            await this.client.logout();
            if(!this.client.isLoggedIn()){
                window.location.href ='/landingPage.html';
            }
        }
    
    async redirectEditUser() {
        window.location.href = '/editUser.html';
        console.log("Edit profile button");
    }
}
        const main = async () => {
            const viewUser = new ViewUser();
            viewUser.mount();
        };
        
        window.addEventListener('DOMContentLoaded', main)

