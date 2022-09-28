import axios from "axios";

export default {
  login(user) {
    return axios.post("/login", user);
  },

  register(user) {
    return axios.post("/register", user);
  },

  changePassword(newPassword) {
    return axios.put("/changepassword", newPassword);
  },

  getAllUsers(){
    return axios.get("/getallusers")
  },

  saveChangeUserStatus(users){
    return axios.put("/updateuserstatus", users)
  }
};
