import axios from "axios";

export default {
    listBaselineItems(id){
        return axios.get(`/schedule/${id}`)
    },

    updateBaselineItem(item){
        return axios.put('/schedule/update', item)
    }
}