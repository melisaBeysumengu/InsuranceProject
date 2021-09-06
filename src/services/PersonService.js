import axios from "axios";

const PersonService = (function () {

    const _createUser = async (credentials) => {
        const request = await axios.post("http://localhost:8080/person/",credentials)
        .then((response) => response.data.tcNumber)
        .catch((error) => error);
        return request
    };

    const _getUser = async (credentials) => {
        console.log(credentials)
        const request = await axios.get(`http://localhost:8080/person/${credentials.tcNumber}`,credentials)
        .then((response) => response.data)
        .catch((error) => error);
        console.log(request)
        return request

    };

    return {
        createUser: _createUser,
        getUser: _getUser
    };
})();

export default PersonService;