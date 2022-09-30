import axios from "axios";

export default {
    listBaselineItems(id){
        return axios.get(`/schedule/${id}`)
    },
}