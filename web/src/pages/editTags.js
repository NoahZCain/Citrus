import CitrusClient from "../api/citrusClient";
import BindingClass from "./util/bindingClass";
import DataStore from "./util/DataStore";

class EditTags extends BindingClass {
  constructor() {
    super();
    this.bindClassMethods(['updateTags', 'mount', 'populateForm'], this);
    this.dataStore = new DataStore();
    this.client = new CitrusClient();
  }

  async displayAccessibilityTags(placeId) {
    const placeResponse = await this.client.getPlace(placeId);
    const place = placeResponse.placesModel;

    if (place) {
      const tagsList = document.getElementById('tagsList');

      // Clear existing tags
      tagsList.innerHTML = '';

      // Display the current tags
      place.accessibilityInfo.forEach((tag) => {
        const li = document.createElement('li');
        li.textContent = tag;
        tagsList.appendChild(li);
      });
    }
  }

  async updateTags() {
    const urlParams = new URLSearchParams(window.location.search);
    const placeId = urlParams.get('placeId');

    const tagsToAddInput = document.getElementById('addTags');
    const tagsToRemoveInput = document.getElementById('removeTags');

    const tagsToAdd = tagsToAddInput.value.split(',').map((tag) => tag.trim());
    const tagsToRemove = tagsToRemoveInput.value.split(',').map((tag) => tag.trim());

    try {
      if (tagsToAdd.length > 0) {
        await this.client.addPlaceTag(placeId, tagsToAdd);
        console.log(`Successfully added tags: ${tagsToAdd}`);
      }

      if (tagsToRemove.length > 0) {
        await this.client.removePlaceTag(placeId, tagsToRemove);
        console.log(`Successfully removed tags: ${tagsToRemove}`);
      }

      // Update the displayed tags
      await this.displayAccessibilityTags(placeId);
    } catch (error) {
      console.error('Error updating tags:', error);
    }
  }

  populateForm() {
    const urlParams = new URLSearchParams(window.location.search);
    const placeId = urlParams.get('placeId');
    
    // Display the current tags
    this.displayAccessibilityTags(placeId);
  }

  mount() {
    document.getElementById("tagsForm").addEventListener("submit", (event) => {
      event.preventDefault();
      this.updateTags();
    });
  }
}

const main = async () => {
  const editTags = new EditTags();
 
  editTags.populateForm();
  editTags.mount();
};

window.addEventListener("DOMContentLoaded", main);
