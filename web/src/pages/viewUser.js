import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class ViewUser extends BindingClass {
  constructor() {
    super();
    this.bindClassMethods(
      ["clientLoaded", "mount", "logout", "redirectEditUser",
    "redirectLandingPage"],
      this
    );
    this.dataStore = new DataStore();
    this.client = new CitrusClient();
    this.Header = new Header(this.dataStore);
  }

  async clientLoaded() {
    const identity = await this.client.getIdentity();
    const user = await this.client.getUser(identity.email);
    const fName = user.userModel.firstName;
    const lName = user.userModel.lastName;
    console.log(fName);
    console.log(lName);
    const email = identity.email;
    const gender = user.userModel.gender;
    const dob = user.userModel.dateOfBirth;
    const userInterests = user.userModel.interests;
    this.dataStore.set("email", email);
    this.dataStore.set("firstName",fName);
    this.dataStore.set("lastName",lName);
    this.dataStore.set("interests", userInterests);
    this.dataStore.set("gender", gender);
    this.dataStore.set("dateOfBirth", dob);
  }

  mount() {
    this.clientLoaded().then(() => {
      this.addName();
      this.addEmail();
      this.addInterests();
      this.addGender();
      this.addDateOfBirth();
    });
    
    document.getElementById("logout").addEventListener("click", this.logout);
    document.getElementById("editProfileButton").addEventListener("click", this.redirectEditUser);
    document.getElementById("backToLanding").addEventListener("click", this.redirectLandingPage);
  }

  async addName() {
    const firstName = this.dataStore.get("firstName");
    const lastName = this.dataStore.get("lastName");
    const nameElement = document.getElementById("name");
  
    if (firstName == null || lastName == null) {
      nameElement.innerText = "John Doe";
    } else {
      nameElement.innerText = `${firstName} ${lastName}`;
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

    if (interests == null || interests.length === 0) {
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

  async addDateOfBirth() {
    const dob = this.dataStore.get("dateOfBirth");
    const dobElement = document.getElementById("dateOfBirth");

    if (dob == null) {
      dobElement.innerText = "DateOfBirth";
    } else {
      dobElement.innerText = dob;
    }
  }

  async addEmail() {
    const emailStored = this.dataStore.get("email");
    const emailElement = document.getElementById("email");

    if (emailStored == null) {
      emailElement.innerText = "info@example.com";
    } else {
      emailElement.innerText = emailStored;
    }
  }

  async logout() {
    await this.client.logout();
    if (!this.client.isLoggedIn()) {
      window.location.href = "/landingPage.html";
    }
  }

  redirectEditUser() {
    window.location.href = "/editUser.html";
  }

  redirectLandingPage() {
    window.location.href = "/homePage.html";
  }
}

const main = async () => {
  const viewUser = new ViewUser();
  viewUser.mount();
};

window.addEventListener("DOMContentLoaded", main);
