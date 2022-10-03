import axios from "axios";

export default {
  addMonthly(picker, id) {
    return axios.post(
      `/monthlyschedule/new?projectId=${id}&monthYear=${picker}`
    );
  },

  updateMonthly(id, monthly){
    return axios.put(`/monthlyschedule/update/${id}`, monthly)
  },

  getMonthly(id) {
    return axios.get(`monthlyschedules/${id}`);
  },

  getMonthlyItems(id) {
    return axios.get(`/monthlyscheduleitems/${id}`);
  },
  
  updateMonthlyItem(item) {
    return axios.put(`/monthlyschedule/update`, item);
  },
};
