import axios from "axios";

export default{
    addMonthly(picker, id){
        return axios.post(`/monthlyschedule/new?projectId=${id}&monthYear=${picker}`)
    }
}