import CitrusClient from "../api/citrusClient.js";
import DataStore from "./util/DataStore.js";

class EditTags {
  constructor() {
    this.dataStore = new DataStore();
    this.client = new CitrusClient();
  }

  async displayAccessibilityTags(placeId) {
    try {
      const placeResponse = await this.client.getPlace(placeId);
      const place = placeResponse.placesModel;

      if (place && place.accessibilityInfo) {
        const tagsList = document.getElementById('tagsList');

        // Clear existing tags
        tagsList.innerHTML = '';

        // Display the current tags
        place.accessibilityInfo.forEach((tag) => {
          const li = document.createElement('li');
          li.textContent = tag;
          tagsList.appendChild(li);
        });
      } else {
        // Handle the case when place or place.accessibilityInfo is null
        console.error('Invalid place or missing accessibility information');
      }
    } catch (error) {
      console.error('Error displaying place details:', error);
    }
  }

  async addTags(placeId, tags) {
    try {
      await this.client.addPlaceTag(placeId, tags, this.handleError);
      console.log(`Successfully added tags: ${tags}`);
      console.log(placeId,tags);

      // Update the displayed tags
      await this.displayAccessibilityTags(placeId);
    } catch (error) {
      console.error('Error adding tags:', error);
    }
  }

  async removeTags(placeId, tags) {
    try {
      await this.client.removePlaceTag(placeId, tags, this.handleError);
      console.log(`Successfully removed tags: ${tags}`);

      // Update the displayed tags
      await this.displayAccessibilityTags(placeId);
    } catch (error) {
      console.error('Error removing tags:', error);
    }
  }

  populateForm() {
    const urlParams = new URLSearchParams(window.location.search);
    const params = Object.fromEntries(urlParams.entries());
    const placeId = params.placeId;
  
    if (placeId) {
      // Display the current tags
      this.displayAccessibilityTags(placeId);
    } else {
      console.error('Missing placeId in URL parameters');
    }
  }

  mount() {
    const addTagsForm = document.getElementById('addTagsForm');
    const removeTagsForm = document.getElementById('removeTagsForm');
    const self = this;
  
    addTagsForm.addEventListener('submit', async function(event) {
      event.preventDefault();
  
      const urlParams = new URLSearchParams(window.location.search);
      const placeIdToAddTags = urlParams.get('placeId');
      const addTagsInput = document.getElementById('addTags');
      const tagsToAdd = addTagsInput.value.split(',').map((tag) => tag.trim());
  
      if (tagsToAdd.length > 0) {
        await self.addTags(placeIdToAddTags, tagsToAdd);
      }
    });
  
    removeTagsForm.addEventListener('submit', async function(event) {
      event.preventDefault();
  
      const urlParams = new URLSearchParams(window.location.search);
      const placeIdToRemoveTags = urlParams.get('placeId');
      const removeTagsInput = document.getElementById('removeTags');
      const tagsToRemove = removeTagsInput.value.split(',').map((tag) => tag.trim());
  
      if (tagsToRemove.length > 0) {
        await self.removeTags(placeIdToRemoveTags, tagsToRemove);
      }
    });
  
    // Set the action URL of the forms to preserve the placeId parameter
    const urlParams = new URLSearchParams(window.location.search);
    const placeId = urlParams.get('id');
    addTagsForm.action = `editTags.html?placeId=${placeId}`;
    removeTagsForm.action = `editTags.html?placeId=${placeId}`;
  }
  
}  
const main = async () => {
  const editTags = new EditTags();
 
  editTags.populateForm();
  editTags.mount();
};

window.addEventListener('DOMContentLoaded', main);
