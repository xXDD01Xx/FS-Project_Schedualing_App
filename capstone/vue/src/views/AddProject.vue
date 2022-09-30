<template>
  <v-app>
    <v-container>
      <v-form ref="form" lazy-validation>
        <v-text-field
          v-model="project.projectName"
          label="Project Name"
          required
        ></v-text-field>

        <v-select
          label="Contract Name"
          :items="contracts"
          item-text="contractName"
          item-value="id"
          v-model="project.contractId"
        ></v-select>

        <v-text-field
          v-model="project.dateReceived"
          type="date"
          label="Date Received"
          required
        ></v-text-field>
        <v-text-field
          v-model="project.budget"
          type="number"
          label="Budget"
          placeholder="$"
          required
        ></v-text-field>
        <v-btn
          class="button"
          color="#8c090e"
          elevation="2"
          outlined
          @click="saveProject"
          >Create Baseline Schedule</v-btn
        >
      </v-form>
      <div v-show="addProjectSuccess">{{ addProjectSuccessMessage }}</div>
      <div v-show="addProjectFailure">{{ addProjectFailureMessage }}</div>
    </v-container>
    <router-link class="text-decoration-none" :to="{ path: '/home' }">
      <v-btn class="button" color="#8c090e" elevation="2" outlined>Home</v-btn>
    </router-link>
  </v-app>
</template>

<script>
  import ProjectService from "../services/ProjectService.js";

  export default {
    data() {
      return {
        addProjectSuccess: false,
        addProjectSuccessMessage: "Successfully Added Project!",
        addProjectFailure: false,
        addProjectFailureMessage: "Something Went Wrong! \n Please Try again...",
        contracts: [],
        project: {
          projectName: "",
          dateReceived: "",
          budget: "",
          contractId: "",
          //TODO Add contract Name? Add substantial/construction tasks
        },
      };
    },
    methods: {
      saveProject() {
        let pro = ''
        ProjectService.addProject(this.project)
          .then((response) => {
            if (response.status == 200 || response.status == 201) {
              this.addProjectSuccess = true
              pro = response.data;
              console.log('pro', pro)
              this.$store.commit('SET_PROJECT', pro);
              console.log('store project',this.$store.state.project)
              this.project = {};
              this.$router.push({name: 'BaselineSchedule', params: {id: this.$store.state.project.id}});
            }
          })
          .catch((error) => {
            const response = error.response;
            if (response.status == 400) {
              alert(this.addProjectFailureMessage);
            }
            if (response.status == 401) {
              alert(this.addProjectFailureMessage);
            }
            if (response.status == 500) {
              alert(this.addProjectFailureMessage);
            }
          });
      },
    },
    created() {
      ProjectService.getContracts().then((response) => {
        this.contracts = response.data;
        console.log(this.contracts);
      });
    },
  };
</script>

<style></style>
