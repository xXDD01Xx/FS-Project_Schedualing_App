<template>
  <v-app>
    <v-container>
      <v-form
      ref="form"
      lazy-validation>
        <v-text-field
          v-model="project.name"
          label="Name"
          required></v-text-field>
        <v-text-field
          v-model="project.dateReceived"
          type="date"
          label="Date Received"
          required></v-text-field>
        <v-text-field
          v-model="project.budget"
          type="currency"
          label="Budget"
          required></v-text-field>
        <v-text-field
          v-model="project.lastModified"
          type="date"
          label="Date Last Modified"
          required></v-text-field>
        <v-btn class="button"
          color=#8c090e
          elevation="2"
          outlined
          @click="saveProject">Submit</v-btn>
      </v-form>
      <div v-show="addProjectSuccess"> {{addProjectSuccessMessage}}</div>
      <div v-show="addProjectFailure">{{addProjectFailureMessage}}</div>
    </v-container>
    <router-link class="text-decoration-none" :to="{path: '/home'}">
      <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Home</v-btn>
    </router-link>
      
  </v-app>
</template>

<script>
import ProjectService from '../services/ProjectService.js'

export default {
  data() {
    return {
      addProjectSuccess: false,
      addProjectSuccessMessage: 'Successfully Added Project',
      addProjectFailure: false,
      addProjectFailureMessage: 'Something Went Wrong',
      project: {
        name: '',
        dateReceived: '',
        budget: '',
        lastModified: '',
        //TODO Add contract Name? Add substantial/construction tasks
      }
    }
  },
  methods: {
    saveProject(){
      ProjectService.addProject(this.project).then(response => {
        if (response.status == 200 || response.status == 201){
          this.addProjectSuccess = true;
          this.project = {};
        }else {
          this.addProjectFailure = true;
        }
      })
    }
  }

}
</script>

<style>

</style>