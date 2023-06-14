import axios from "axios";
import BindingClass from "../pages/util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the Citrus Service.
  */
export default class CitrusClient extends BindingClass {

    constructor(props = {}) {
        
        super();
        
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'isLoggedIn', 'getUser'];
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
    

    /**
     * Search for a place.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The place that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
            // const queryParams = new URLSearchParams({ id: criteria })
            // const queryString = queryParams.toString();

        const response = await this.axiosClient.get(`place/search/${criteria}`);
        
            console.log(response.data.places);
            return response.data.places;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error("errorFromApi " + errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
        console.error("errorCallback " + errorCallback)
            errorCallback(error);
        }
    }
}
