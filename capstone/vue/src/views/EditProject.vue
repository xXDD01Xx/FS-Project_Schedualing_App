<template>
  <v-app>
    <div><br><br></div>
    <v-container>
      <h2 class="text-center">{{ this.$store.state.project.projectName }}</h2><br>
      <v-text-field type="text" label="Name"
      v-model="project.projectName"
      required></v-text-field>

      <v-text-field type="date" label="Date Received" 
      v-model="project.dateReceived"
      required></v-text-field>

      <v-text-field type="number" label="Budget"
      v-model="project.budget"
      required></v-text-field>

      <v-text-field type="text" label="Construction Tasks" 
      v-model="project.tasksConstruction"
      required></v-text-field>

      <v-text-field type="text" label="Substantial Tasks" 
      v-model="project.tasksSubstantial"
      required></v-text-field><br>
      <div class="text-center">
    <v-btn
    class="button" color="#76a1c0" elevation="2"
    outlined type="submit" @click="updateProject"
    >Submit Changes</v-btn>

    <v-btn
    class="button" color="error" elevation="2"
    outlined v-model="areYouSure" @click="toggleDelete"
    >Delete Project</v-btn>

    <v-btn v-show="areYouSure"
    class="button" color="error" elevation="2"
    outlined type="submit" @click="deleteProject"
    >Yes, Delete</v-btn>

    <v-btn v-show="areYouSure"
    class="button" color="#76a1c0" elevation="2"
    outlined type="submit" @click="toggleDelete"
    >Cancel</v-btn>
      <br><br>
      </div>
    </v-container>
    <div class="text-center">
      <v-btn class="button" color="#8c090e" elevation="2" outlined @click="$router.back()"
      >Back
      </v-btn>
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
        >Home</v-btn
        >
      </router-link>
    </div>
  </v-app>
</template>

<script>
import ProjectService from '../services/ProjectService'
export default {
  data(){
    return{
      project: this.$store.state.project,
      areYouSure: false,
    }
  },
  name: "EditProject",
  methods: {
    toggleDelete(){
      this.areYouSure = !this.areYouSure;
    },
    updateProject(){
      ProjectService.updateProject(this.project).then((response) => {
        if (response.status == 200 || response.status == 201){
          //TODO add nice success message
          this.$store.commit('EMPTY_PROJECT')
          alert('Edited');
          this.$router.push({name: 'home'})
        }
      })
    },
    deleteProject(){
      let id = this.project.id;
      console.log(this.project)
      console.log(this.project.id)
      ProjectService.deleteProject(id).then((response) =>{
        if (response.status == 200 || response.status == 204){
          this.$store.commit('EMPTY_PROJECT');
          alert('Deleted');
          this.$router.push({name: 'home'});
        }
      })
      .catch((error) => {
        const response = error.message;
        if (response.status == 400){
          alert(response.message)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>