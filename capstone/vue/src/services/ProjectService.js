import axios from "axios";

export default {
    listProjects(){
        return axios.get("/projects")
    },

    addProject(project){
        return axios.post("/project/new", project)
    }
}