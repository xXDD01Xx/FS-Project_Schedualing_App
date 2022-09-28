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
        <v-select label="contractName" v-model="con" :items="contracts" item-text="contractName"
        item-value="contractId" :reduce="contracts => contracts.contractId" ></v-select>
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
      con: {},
      addProjectSuccess: false,
      addProjectSuccessMessage: 'Successfully Added Project',
      addProjectFailure: false,
      addProjectFailureMessage: 'Something Went Wrong',
      contracts: [],
      // items: [1,2,3,4,5],
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
      // this.project.contractId = this.setContractId()
      // this.project.lastModified = new Date().getTimezoneOffset()
      console.log(this.project)
      console.log(this.con)
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
    // setContractId(){
    //   const c = this.contracts.filter(contract => {
    //     this.contract !== contract.contractName
    //   });
    //   console.log(c)
    //   const id = c[0].contractId
    //   return id;
    // }
  },
  created(){
    ProjectService.getContracts().then((response) => {
      this.contracts = response.data;
      console.log(this.contracts)
    })
  }
}
</script>

<style>

</style>