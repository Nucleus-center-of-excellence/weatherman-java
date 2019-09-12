export const enum methodType {
    get = 'GET',
    getAccuweather = 'GETAccuweather',
    post = 'POST',
    put = 'PUT',
    patch = 'PATCH',
    delete = 'DELETE'
}

export const enum url {
    forgotpassword = 'forgotpassword',
    login = 'authenticate',
    addUser = 'register',
    getWeather = 'getweather',
    getLatLongFromCityName = 'https://cors-anywhere.herokuapp.com/http://dataservice.accuweather.com/locations/v1/cities/search?apikey=JUutZdJ8j0EwP9APDW3Aon7uWHRsAVvG&q=',
    checkLinkExpiration = 'check-link-expiration',
    resetPassword = 'reset-password',
    postVote = 'uservote',
    getFavProvider = 'summaryprovider',
    getAvgOfProviders = 'averageprovider',
    getGMapKey = 'gmapkey'
}

export const enum routerPath {
    resetpassword = '/resetPassword',
    home = '/home',
    loginSignUp = '/loginSignup'
}
