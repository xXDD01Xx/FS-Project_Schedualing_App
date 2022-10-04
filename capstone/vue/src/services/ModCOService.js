import axios from "axios";

export default {
    listModCO(){
        return axios.get('/modco')
    }
};