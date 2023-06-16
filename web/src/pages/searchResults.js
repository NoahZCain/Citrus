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
        this.mount();
        this.addPlaceName();
    }
    async clientLoaded(){
        
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