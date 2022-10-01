import axios from "axios";

export default {
  addMonthly(picker, id) {
    return axios.post(
      `/monthlyschedule/new?projectId=${id}&monthYear=${picker}`
    );
  },

  getMonthly(id) {
    return axios.post(`monthlyschedules/${id}`);
  },
  getMonthlyItems(id) {
    return axios.post(`/monthlyscheduleitems/${id}`);
  },
  updateMonthlyItem(item) {
    return axios.post(`/monthlyschedule/update`, item);
  },
};
