import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class SearchResults extends BindingClass {
  constructor() {
    super();
    this.client = new CitrusClient();
    this.header = new Header();
    this.dataStore = new DataStore();
  }

  async displayResults(searchCriteria) {
    try {
      const places = await this.client.search(searchCriteria);
      const placeAttributes = document.getElementById('placeAttributes');
  
      if (Array.isArray(places) && places.length > 0) {
        places.forEach(place => {
          const placeName = document.createElement('h2');
          placeName.textContent = place.placeName;
          placeAttributes.appendChild(placeName);
  
          const address = document.createElement('p');
          address.textContent = `Address: ${place.placeAddress}`;
          placeAttributes.appendChild(address);
  
          const accessibilityInfo = document.createElement('p');
          accessibilityInfo.textContent = `Accessibility Info: ${place.accessibilityInfo.join(', ')}`;
          placeAttributes.appendChild(accessibilityInfo);
  
          const placeTypes = document.createElement('p');
          placeTypes.textContent = `Place Types: ${place.placeTypes}`;
          placeAttributes.appendChild(placeTypes);
  
          const moreInfoButton = document.createElement('button');
          moreInfoButton.textContent = 'More Info';
          moreInfoButton.addEventListener('click', () => {

           window.location.href = `/viewPlace.html?id=${place.placeId}`;
          });
          placeAttributes.appendChild(moreInfoButton);
  
          const lineBreak = document.createElement('hr');
          placeAttributes.appendChild(lineBreak);
        });
      } else {
        const message = document.createElement('p');
        message.textContent = 'No places found.';
        placeAttributes.appendChild(message);
      }
    } catch (error) {
      console.error('Error displaying search results:', error);
    }
  }
}


const urlParams = new URLSearchParams(window.location.search);
const searchCriteria = urlParams.get('criteria');

const searchResults = new SearchResults();
searchResults.displayResults(searchCriteria);
