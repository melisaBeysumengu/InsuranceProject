import axios from "axios";

const VehicleService = (function () {
    const headers = {
        'Content-Type': 'application/json'
    }

    const _createVehicle = async (credentials) => {
        console.log(credentials)
        console.log("vegicle");
        const request = await axios.put(`http://localhost:8080/person/vehicle/${credentials.tcNumber}`, credentials)
        .then((response) => {console.log(response.data)})
        .catch((error) => error);

        console.log(request)

        return request

    };

    return {
        createVehicle: _createVehicle
    };
})();

export default VehicleService;