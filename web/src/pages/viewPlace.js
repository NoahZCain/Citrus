import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class ViewPlace extends BindingClass {
  constructor() {
    super();
    this.client = new CitrusClient();
    this.header = new Header();
    this.dataStore = new DataStore();
  }

  async displayPlaceDetails(placeId) {
    try {
      const place = await this.client.getPlace(placeId);
      console.log(placeId);
      const placeDetailsElement = document.getElementById('placeDetails');

      const placeName = document.createElement('h2');
      placeName.textContent = place.placeName;
      placeDetailsElement.appendChild(placeName);

      const address = document.createElement('p');
      address.textContent = `Address: ${place.placeAddress}`;
      placeDetailsElement.appendChild(address);

      const accessibilityInfo = document.createElement('p');
      accessibilityInfo.textContent = `Accessibility Info: ${place.accessibilityInfo.join(', ')}`;
      placeDetailsElement.appendChild(accessibilityInfo);

      // Add any additional place details you want to display

      // Add event listener for the "Edit Accessibility Tags" button
      const editTagsButton = document.getElementById('editTagsButton');
      editTagsButton.addEventListener('click', () => {
        // Redirect to the editTags.html page passing the place ID
        window.location.href = `/editTags.html?placeId=${place.placeId}`;
      });
    } catch (error) {
      console.error('Error displaying place details:', error);
    }
  }
}

// Retrieve the place ID from the query parameters
const urlParams = new URLSearchParams(window.location.search);
const placeId = urlParams.get('id');

if (placeId) {
  const viewPlace = new ViewPlace();
  viewPlace.displayPlaceDetails(placeId);
} else {
  console.error('Missing id parameter');
}