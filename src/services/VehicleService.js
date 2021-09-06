import axios from "axios";

const VehicleService = (function () {

    const _createVehicle = async (credentials) => {
        const request = await axios.put(`http://localhost:8080/person/vehicle/${credentials.tcNumber}`, credentials)
        .then((response) => {console.log(response.data)})
        .catch((error) => error);

        return request

    };

    return {
        createVehicle: _createVehicle
    };
})();

export default VehicleService;