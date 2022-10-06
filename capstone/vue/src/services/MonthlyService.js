import axios from "axios";

export default {
  addMonthly(picker, id) {
    return axios.post(
      `/monthlyschedule/new?projectId=${id}&monthYear=${picker}`
    );
  },

  updateMonthly(monthly){
    return axios.put(`/monthlyschedule/update`, monthly)
  },

  getMonthly(id) {
    return axios.get(`monthlyschedules/${id}`);
  },

  getMonthlyItems(id) {
    return axios.get(`/monthlyscheduleitems/${id}`);
  },
  
  updateMonthlyItem(item) {
    return axios.put(`/monthlyschedule/items/update/`, item);
  },

  getMonthlyList(id){
    return axios.get(`/monthlybyproject/${id}`);
  }
};
