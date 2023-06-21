import axios from "axios";
import BindingClass from "../pages/util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the Citrus Service.
  */
export default class CitrusClient extends BindingClass {

    constructor(props = {}) {
        
        super();
        
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'isLoggedIn', 'getUser', 'search','getPlace','getPlaceByName'
    ,'updateUser','addPlaceTag','removePlaceTag'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }
    async isLoggedIn(){
        return this.authenticator.isUserLoggedIn();
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }
    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the User for the given ID.
     * @param id Unique identifier for a user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The User's metadata.
     */
    async getUser(userId, errorCallback) {
        try {
        console.log(userId + " userId");
            const token = await this.getTokenOrThrow("Only authenticated users can view a profile.");
            const response = await this.axiosClient.get(`user/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    
    async getPlace(placeId, errorCallback) {
        try {
       
          const response = await this.axiosClient.get(`place/${placeId}`);
          return response.data;  
        } catch (error) {
          this.handleError(error, errorCallback);
        }
      }

    /**
     * Search for a place.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The place that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
          const queryParams = new URLSearchParams({ placeName : criteria });
          const queryString = queryParams.toString();
          console.log(queryString);
      
          const response = await this.axiosClient.get(`place/search?${queryString}`);
          console.log(response);
        
        if (response && response.data && response.data.places) {
            const places = response.data.places;
            return places; 
          } else {
            throw new Error('Invalid response data');
          }
        } catch (error) {
          this.handleError(error, errorCallback);
        }
      }
      
      async updateUser(email, user, errorCallback) {
        try {
          const token = await this.getTokenOrThrow("Only authenticated users can update their profile.");
          await this.axiosClient.put(`user/${email}`, user, {
            headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json'
            }
          });
        } catch (error) {
          this.handleError(error, errorCallback);
        }
      }


      async getPlaceByName(placeName, errorCallback) {
        try {
          const token = await this.getTokenOrThrow("Only authenticated users can view a place by name.");
          const response = await this.axiosClient.get(`place/search`, {
            headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json'
            },
            params: {
              placeName: placeName
            }
          });
          
          if (response && response.data && response.data.places) {
            const places = response.data.places;
            return places; 
          } else {
            throw new Error('Invalid response data');
          }
        } catch (error) {
          this.handleError(error, errorCallback);
        }
      }
      async addPlaceTag(placeId,tag, errorCallback) {
        try {
          const token = await this.getTokenOrThrow("Only authenticated users can add tags to a place.");
          console.log(token);
          
          const payload = {
            placeId: placeId,
            accessibilityTags: tag
          };
      
          const response = await this.axiosClient.post('/place/addTags', payload, {
            headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json'
            },
            
          });
           console.log(response.data);
       
        } catch (error) {
          
          if (errorCallback && typeof errorCallback === 'function') {
            errorCallback(error);
          } else {
            console.error('Error adding place tag:', error);
          }
        }
      }
      
      async removePlaceTag(placeId, tag, errorCallback) {
        try {
          const token = await this.getTokenOrThrow("Only authenticated users can remove tags from a place.");

          const payload = {
            placeId: placeId,
            tagsToRemove: tag 
          };

          const response = await this.axiosClient.put(`/place/removeTags`, payload, {
            headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json'
            },
          });
      
          console.log(response.data); 
        } catch (error) {
          this.handleError(error, errorCallback);
        }
      }
      
      handleError = (error, errorCallback) => {
        console.error(error);
    
        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
          console.error("errorFromApi " + errorFromApi);
          error.message = errorFromApi;
        }
    
        if (errorCallback) {
          console.error("errorCallback " + errorCallback);
          errorCallback(error);
        }
      };
    }