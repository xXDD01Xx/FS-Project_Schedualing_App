import axios from "axios";

export default {
  getMaster() {
    return axios.get(`/master`);
  },
  getStatusReport() {
    return axios.get(`/statusreport`);
  },
};
