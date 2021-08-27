import axios from "axios";

const PersonService = (function () {
    const headers = {
        'Content-Type': 'application/json'
    }

    const _createUser = async (credentials) => {
        // let user = null;
        // try {
        //     const response = await axios.post(
        //         "http://localhost:8080/person/",
        //         credentials
        //     );
        //     if (response && response.data) {
        // console.log(response);
        //         user = response.data;
        //     }
        // } catch (error) {
        //     console.log(error);
        // }

        // return user;
        console.log(credentials)
        const request = await axios.post("http://localhost:8080/person/",credentials)
        .then((response) => {console.log(response.data)})
        .catch((error) => error);

        console.log(request)

        return request

    };

    return {
        createUser: _createUser
    };
})();

export default PersonService;