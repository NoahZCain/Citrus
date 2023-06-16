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
      
          // Generate the URL with search criteria
          // const url = `/search-results.html?criteria=${encodeURIComponent(searchCriteria)}`;
      
          // Navigate to the search results page
          window.location.href = '/searchResults.html';
        });
        
    }

    async login(){
        await this.client.login();
    }


    async searchForPlaces(criteria) {
        try {
          const queryParams = new URLSearchParams();
          queryParams.set('placeName', criteria);

          const queryString = queryParams.toString();
          const response = await this.client.search(queryString,this.handleError);
          
      
          if (response && Array.isArray(response)) { // Update the response handling
            const places = response;
            console.log(places);
            return places;
          }
          
        } catch (error) {
          console.error('Search error:', error);
          throw error;
        }
      }
      displaySearchResults(places, searchResults) {
        searchResults.innerHTML = '';
      
        if (Array.isArray(places) && places.length > 0) {
          places.forEach((place) => {
            const listItem = document.createElement('li');
            listItem.textContent = place.placeName;
            searchResults.appendChild(listItem);
        });
          
        } else {
          const message = document.createElement('p');
          message.textContent = 'No places found.';
          searchResults.appendChild(message);
        }
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