import CitrusClient from "../api/citrusClient";
import DataStore from "./util/DataStore";

class EditUser {
  constructor() {
    this.dataStore = new DataStore();
    this.client = new CitrusClient();
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
    this.dataStore.set("gender", gender);
    this.dataStore.set("dateOfBirth", dob);
    this.dataStore.set("interests", userInterests);
  }

  async updateUser() {
    const name = document.getElementById("name").value;
    const gender = document.getElementById("gender").value;
    const dob = document.getElementById("dateOfBirth").value;
    const interests = document.getElementById("interests").value;

    this.dataStore.set("name", name);
    this.dataStore.set("gender", gender);
    this.dataStore.set("dateOfBirth", dob);
    this.dataStore.set("interests", interests);

    const email = this.dataStore.get("email");
    const user = {
      userModel: {
        firstName: name.split(" ")[0],
        lastName: name.split(" ")[1],
        gender,
        dateOfBirth: dob,
        interests: interests.split(","),
      },
    };

    await this.client.updateUser(email, user);

    // Redirect to the view user page after updating
    window.location.href = '/viewUser.html';
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

  populateForm() {
    const name = this.dataStore.get("name");
    const gender = this.dataStore.get("gender");
    const dob = this.dataStore.get("dateOfBirth");
    const interests = this.dataStore.get("interests");

    document.getElementById("name").value = name || "";
    document.getElementById("gender").value = gender || "";
    document.getElementById("dateOfBirth").value = dob || "";
    document.getElementById("interests").value = interests ? interests.join(", ") : "";
  }
}

const main = async () => {
  const editUser = new EditUser();
  editUser.mount();
};

window.addEventListener('DOMContentLoaded', main);
