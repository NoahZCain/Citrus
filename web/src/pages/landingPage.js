import CitrusClient from "../api/citrusClient.js";
import BindingClass from "./util/bindingClass.js";
import Header from "../components/header.js";
import DataStore from "./util/DataStore.js";

class LandingPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'login'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.client = new CitrusClient();
        this.clientLoaded();
    }

    async clientLoaded() {}

    async mount() {
        const loggedIn = await this.client.isLoggedIn();
        console.log("HERE", loggedIn);
        if(loggedIn == true){
            window.location.href= '/profile.html';
        }
        

        
        document.getElementById('login').addEventListener('click', this.login);
        // document.getElementById('sign-up').addEventListener('click', this.login);


    }

    async login(){
        await this.client.login();
        
    }



}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPage = new LandingPage();
    landingPage.mount();
};

window.addEventListener('DOMContentLoaded', main);