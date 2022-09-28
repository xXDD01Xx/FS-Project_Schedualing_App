<template>
  <v-app>
    <v-container>
      <v-form
      ref="form"
      lazy-validation>
        <v-text-field
          v-model="project.name"
          label="Project Name"
          required></v-text-field>
        <v-select label="contract" v-model="contract" :items="items"></v-select>
        <v-text-field
          v-model="project.dateReceived"
          type="date"
          label="Date Received"
          required></v-text-field>
        <v-text-field
          v-model="project.budget"
          type="number"
          label="Budget"
          required></v-text-field>
        <!-- <v-text-field
          v-model="project.lastModified"
          type="date"
          label="Date Last Modified"
          required></v-text-field> -->
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
      contract: '',
      addProjectSuccess: false,
      addProjectSuccessMessage: 'Successfully Added Project',
      addProjectFailure: false,
      addProjectFailureMessage: 'Something Went Wrong',
      items: [
        'Travis County CIP Program Management',
        'Kyle CIP Program Management'
      ],
      project: {
        name: '',
        dateReceived: '',
        budget: '',
        contractId: '',
        // lastModified: '',
        //TODO Add contract Name? Add substantial/construction tasks
      }
    }
  },
  methods: {
    saveProject(){
      this.setCOntractId()
      // this.project.lastModified = new Date().getTimezoneOffset()
      console.log(this.project)
      ProjectService.addProject(this.project).then(response => {
        if (response.status == 200 || response.status == 201){
          this.addProjectSuccess = true;
          this.project = {};
        }
      }).catch((error) => {
        const response = error.response;
        if (response.status == 400){
          alert(response.data.message)
        }if (response.status == 401){
          alert(response.data.message)
        }if (response.status == 500){
          alert(response.data.message)
        }
      })
    },
    setCOntractId(){
      if (this.contract == 'Travis County CIP Program Management'){
        this.project.contractId = 1;
      }else if (this.contract == 'Kyle CIP Program Management'){
        this.project.contractId = 2;
      }
    },
  },
  computed: {
    // now(){
    //   return Date.now().getTimeZoneOffset()
    // }
  }

}
</script>

<style>

</style>