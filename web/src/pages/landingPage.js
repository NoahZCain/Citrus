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
        try{
            const response = await this.client.search({ id: criteria});
            const places = response.data.places;

        if (places.length === 0) {
        console.log('No places found.');
        // Perform any additional actions or return a specific value for empty search
        } else {
            console.log(places);
            return places;
        }
            console.log(places);
                return places;
            }   catch (error){
                console.error('Search error:', error);
                throw error;
            }
        }
        displaySearchResults(places, searchResults) {
        searchResults.innerHTML = '';

            console.log(places);
            console.log(searchResults);
            places.forEach((place) => {
              const listItem = document.createElement('li');
              listItem.textContent = place.name;
              searchResults.appendChild(listItem);
            });
          
            const message = document.createElement('p');
            message.textContent = 'No places found.';
            searchResults.appendChild(message);
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