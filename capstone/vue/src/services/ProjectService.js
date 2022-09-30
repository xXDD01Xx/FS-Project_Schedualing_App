import axios from "axios";

export default {
    listProjects(){
        return axios.get("/projects")
    },

    addProject(project){
        return axios.post("/project/new", project)
    },

    deleteProject(id){
        return axios.delete(`/project/${id}/delete`)
    },

    updateProject(project){
        return axios.put("/project/update", project)
    },

    getContracts(){
        return axios.get("/contracts")
    },

    

}