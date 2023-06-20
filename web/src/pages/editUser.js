import CitrusClient from "../api/citrusClient";
import BindingClass from "./util/bindingClass";
import DataStore from "./util/DataStore";

class EditUser extends BindingClass {
  constructor() {
    super();
    this.bindClassMethods(["clientLoaded", "updateUser", "mount", "populateForm"], this);
    this.dataStore = new DataStore();
    this.client = new CitrusClient();
  }

  async clientLoaded() {
    const identity = await this.client.getIdentity();
    const user = await this.client.getUser(identity.email);
    const firstName = user.userModel.firstName;
    const lastName = user.userModel.lastName;
    const email = identity.email;
    const gender = user.userModel.gender;
    const dob = user.userModel.dateOfBirth;
    const userInterests = user.userModel.interests;
    this.dataStore.set("email", email);
    this.dataStore.set("firstName", firstName);
    this.dataStore.set("lastName", lastName);
    this.dataStore.set("gender", gender);
    this.dataStore.set("dateOfBirth", dob);
    this.dataStore.set("interests", userInterests);
  }

  async updateUser() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const name = firstName + " " + lastName;
    const gender = document.getElementById("gender").value;
    const dateOfBirth = document.getElementById("dateOfBirth").value;
    const interests = document.getElementById("interests").value.split(",").map((interest) => interest.trim());
    const userId = this.dataStore.get("email");
  
    const user = {
      userId,
      firstName,
      lastName,
      gender,
      dateOfBirth,
      userInterests: interests,
    };
  
    await this.client.updateUser(userId, user);
  
    // Fetch the updated user data from the server
    const updatedUser = await this.client.getUser(userId);
  
    // Update the stored values in the EditUser class
    const updatedFirstName = updatedUser.firstName;
    const updatedLastName = updatedUser.lastName;
    const updatedGender = updatedUser.gender;
    const updatedDateOfBirth = updatedUser.dateOfBirth;
    const updatedInterests = updatedUser.userInterests;
  
    this.dataStore.set("firstName", updatedFirstName);
    this.dataStore.set("lastName", updatedLastName);
    this.dataStore.set("gender", updatedGender);
    this.dataStore.set("dateOfBirth", updatedDateOfBirth);
    this.dataStore.set("interests", updatedInterests);
  
    // Redirect to the profile page after updating
    window.location.href = "/profile.html";
  }
  
  
  populateForm() {
    const firstName = this.dataStore.get("firstName");
    const lastName = this.dataStore.get("lastName");
    const gender = this.dataStore.get("gender");
    const dob = this.dataStore.get("dateOfBirth");
    const interests = this.dataStore.get("interests");
  
    document.getElementById("firstName").value = firstName || "";
    document.getElementById("lastName").value = lastName || "";
    document.getElementById("gender").value = gender || "";
    document.getElementById("dateOfBirth").value = dob || "";
    document.getElementById("interests").value = interests ? interests.join(", ") : "";
  }

  mount() {
    this.clientLoaded().then(() => {
      this.populateForm();
    });

    document.getElementById("updateUserForm").addEventListener("submit", (event) => {
      event.preventDefault();
      this.updateUser();
    });
  }

}

const main = async () => {
  const editUser = new EditUser();
  await editUser.clientLoaded();
  editUser.populateForm();
  editUser.mount();
};

window.addEventListener("DOMContentLoaded", main);
