import axios from "axios";

export default {
    listProjects(){
        return axios.get("/projects")
    }
}