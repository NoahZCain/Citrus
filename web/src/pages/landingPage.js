import CitrusClient from "../api/citrusClient.js";
import BindingClass from "./util/bindingClass.js";
import Header from "../components/header.js";
import DataStore from "./util/DataStore.js";

class LandingPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'login', 'searchForPlaces', 'displaySearchResults'], this);
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

        const searchForm = document.getElementById('searchForm');
        const searchInput = document.getElementById('searchInput');
        const searchResults = document.getElementById('searchResults');
      
        searchForm.addEventListener('submit', async (event) => {
          event.preventDefault();
      
          const searchCriteria = searchInput.value;
          const places = await this.searchForPlaces(searchCriteria);
      
          this.displaySearchResults(places, searchResults);
        });
    }

    async login(){
        await this.client.login();
        
    }
    async searchForPlaces(criteria) {
        try {
          const places = await this.client.search(criteria);
          return places;
        } catch (error) {
          console.error('Search error:', error);
        }
    }
    displaySearchResults(places, searchResults) {
        searchResults.innerHTML = '';
      
        places.forEach((place) => {
          const listItem = document.createElement('li');
          listItem.textContent = place.name;
          searchResults.appendChild(listItem);
        });
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