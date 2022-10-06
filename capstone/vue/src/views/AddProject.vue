<template>
  <v-app>
    <v-container>
      <br>
      <h2 class="text-center">Add Project</h2>
      <br>
      <v-form ref="form">
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
          required
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
        <div class="text-center">
          <br>
        <v-btn
          class="button"
          color="#8c090e"
          elevation="2"
          outlined
          @click="saveProject"
          >Create Baseline Schedule</v-btn
        ></div>
      </v-form>
      <div v-show="addProjectSuccess">{{ addProjectSuccessMessage }}</div>
      <div v-show="addProjectFailure">{{ addProjectFailureMessage }}</div>
    </v-container>
    <div class="text-center">
      <br><br>
    <router-link class="text-decoration-none" :to="{ path: '/home' }">
      <v-btn class="button" color="#8c090e" elevation="2" outlined>Home</v-btn>
    </router-link>
    </div>
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
              this.$store.commit('SET_PROJECT', pro);
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
      });
    },
  };
</script>

<style></style>
