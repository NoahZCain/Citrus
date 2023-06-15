import CitrusClient from "../api/citrusClient";
import Header from "../components/header";
import DataStore from "./util/DataStore";
import BindingClass from "./util/bindingClass";

class SearchResults extends BindingClass{
    constructor(){
        super();
        this.bindClassMethods(['clientLoaded','mount','logout'],this);
        this.dataStore = new DataStore;
        this.header = new Header(this.dataStore);
    }
    async clitentLoaded(){
        const identity = await this.client.getIdentity();
        const place = identity.placeName;
        const address = identity.placeAddress;
    }
    mount(){
        this.client = new CitrusClient();
    }
    async addPlaceName(){
        const nameStored = this.dataStore.get("placeName");
        const nameElement = document.getElementById("placeName");
        console.log(nameStored);
        console.log(nameElement);
    }

}