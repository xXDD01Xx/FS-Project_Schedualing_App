import axios from "axios";

export default {
    listModCO(){
        return axios.get('/modco')
    },

    addModCO(mod){
        return axios.post('/modco/new', mod)
    },

    getModCO(id){
        return axios.get(`/modco/getModCO/${id}`)
    },

    changeStatus(id, status){
        return axios.put(`/modco/${id}/changeStatus`, status)
    }



};