import axios from "axios";

const OfferService = (function () {

    const _createOffer = async (credentials) => {
        console.log(credentials)
        const request = await axios.post("http://localhost:8080/offer/",credentials)
        .then((response) => response.data)
        .catch((error) => error);

        console.log(request)

        return request

    };

    return {
        createOffer: _createOffer
    };
})();

export default OfferService;