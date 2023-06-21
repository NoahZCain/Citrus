import CitrusClient from "../api/citrusClient.js";
import BindingClass from "./util/bindingClass.js";
import Header from "../components/header.js";
import DataStore from "./util/DataStore.js";

class HomePage extends BindingClass {
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
  

    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    const searchResults = document.getElementById('searchResults');

    if (searchForm && searchInput && searchResults) {
      searchForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const searchCriteria = searchInput.value;
        await this.searchForPlaces(searchCriteria);
        console.log(searchCriteria);

        // Generate the URL with search criteria
        const url = `/searchResults.html?criteria=${encodeURIComponent(searchCriteria)}`;

        // Navigate to the search results page
        window.location.href = url;
      });
    }
  }

  async login() {
    await this.client.login();
  }

  async searchForPlaces(criteria) {
    try {
      const queryParams = new URLSearchParams();
      queryParams.set('placeName', criteria);

      const queryString = queryParams.toString();
      const response = await this.client.search(queryString, this.handleError);

      if (response && Array.isArray(response)) {
        const places = response;
        console.log(places);
        this.displaySearchResults(places, document.getElementById('searchResults'));
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
  const homePage = new HomePage();
  homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);