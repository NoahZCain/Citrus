import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class ViewUser extends BindingClass{
    constructor(){
        super();
        this.bindClassMethods(['clientLoaded','mount','logout','redirectHomePage','addName'],this);
        this.dataStore = new DataStore;
        this.Header = new Header(this.dataStore);
    }
    async clientLoaded(){
        const identity = await this.client.getIdentity();
        const name = identity.name;
        this.dataStore.set("userId",identity);
        this.dataStore.set("name",name);
      
    }
    mount(){
        document.getElementById('logout').addEventListener('click',this.redirectHomePage);
        this.client = new CitrusClient();
        this.clientLoaded().then(() => {
            this.addName();
        });
    }
     redirectHomePage(){
        window.location.href = "/landingPage.html";
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
    async logout(){
            await this.client.logout();
            if(!this.client.isLoggedIn()){
                window.location.href ="/landingPage.html";
            }
        }
    }
        const main = async () => {
            const viewUser = new ViewUser();
            viewUser.mount();
        };
        
        window.addEventListener('DOMContentLoaded', main)

