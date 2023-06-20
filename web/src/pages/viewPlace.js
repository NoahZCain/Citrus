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
      const placeResponse = await this.client.getPlace(placeId);
      const place = placeResponse.placesModel;
  
      if (place) {
     
  
      const placeName = document.createElement('p');
      placeName.textContent = `Place Name: ${place.placeName}`;
      placeAttributes.appendChild(placeName);

      const accessibilityInfo = document.createElement('p');
      accessibilityInfo.textContent = `Accessibility Info: ${place.accessibilityInfo.join(', ')}`;
      placeAttributes.appendChild(accessibilityInfo);

      const address = document.createElement('p');
      address.textContent = `Address: ${place.placeAddress}`;
      placeAttributes.appendChild(address);

      const placeTypes = document.createElement('p');
      placeTypes.textContent = `Place Types: ${place.placeTypes.join(', ')}`;
      placeAttributes.appendChild(placeTypes);

 	const editTagsButton = document.getElementById('editTagsButton');
        editTagsButton.addEventListener('click', () => {
        window.location.href = `/editTags.html?placeId=${place.placeId}`;
        });
      } else {
        console.error('Place not found');
      }
    } catch (error) {
      console.error('Error displaying place details:', error);
    }
  }
  
}
  
const main = async () => {
  const urlParams = new URLSearchParams(window.location.search);
  const placeId = urlParams.get('id');

  if (placeId) {
    const viewPlace = new ViewPlace();
    await viewPlace.displayPlaceDetails(placeId);
  } else {
    console.error('Missing id parameter');
  }
};

window.addEventListener("DOMContentLoaded", main);
