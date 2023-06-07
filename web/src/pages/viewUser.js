import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class ViewUser extends BindingClass{
    constructor(){
        super();
        this.bindClassMethods(['clientLoaded','mount','logout','redirectHomePage','addName','addEmail'],this);
        this.dataStore = new DataStore;
        this.Header = new Header(this.dataStore);
    }
    async clientLoaded(){
        const identity = await this.client.getIdentity();
        const name = identity.name;
        const email = identity.email;
        this.dataStore.set("email",email);
        this.dataStore.set("name",name);
        console.log(email);
    }
    mount(){
        // document.getElementById('logout').addEventListener('click',this.redirectHomePage);
        this.client = new CitrusClient();
        
        document.getElementById('logout').addEventListener('click',this.logout);
        this.clientLoaded().then(() => {
            this.addName();
            this.addEmail();
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
    }
        const main = async () => {
            const viewUser = new ViewUser();
            viewUser.mount();
        };
        
        window.addEventListener('DOMContentLoaded', main)

