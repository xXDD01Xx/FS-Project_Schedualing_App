<template>
  <v-app>
    <v-container>
      <v-form ref="form" lazy-validation>
        <!-- project name -->
        <!-- month year -->
        <!-- schedule notes -->
        <!-- percent complete -->
        <!-- same as last month: yes/no, if no display items and whys -->

        <v-text-field
          v-model="project.projectName"
          label="Project Name"
          required
          :readonly="true"
        ></v-text-field>

        <v-text-field
          label="Month/Year"
          type="text"
          v-model="monthly.monthYear"
          :readonly="true"
          required
        ></v-text-field>

        <v-text-field
          label="Schedule Notes"
          type="text"
          v-model="monthly.scheduleNotes"
          :readonly="true"
          required
        ></v-text-field>

        <v-text-field
          label="Percent Complete"
          type="text"
          v-model="monthly.pctComplete"
          :readonly="true"
          required
        ></v-text-field>

        <v-radio-group v-model="monthly.samePrevMonth" row>
          <v-radio label="Yes" value=true></v-radio>
          <v-radio label="No" value=false></v-radio>
        </v-radio-group>

      </v-form>
    </v-container>
    <v-container class="check-container">
      <v-checkbox
        label="Design"
        value="Design"
        @click="updateBaseline"
        v-model="phase"
      >
      </v-checkbox>
      <v-checkbox
        label="Pre Construction"
        value="Pre-Construction"
        @click="updateBaseline"
        v-model="phase"
      >
      </v-checkbox>
      <v-checkbox
        label="Construction"
        value="Construction"
        @click="updateBaseline"
        v-model="phase"
      >
      </v-checkbox>
    </v-container>
    <h2 class="text-center">{{ phase }}</h2>
    <MonthlyEntry :monthlyItems="filteredMonthlyItems" />
    <div class="text-center">
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home
        </v-btn>
      </router-link>
    </div>
  </v-app>
</template>

<script>
  import MonthlyService from "../services/MonthlyService.js";
  import MonthlyEntry from "../components/MonthlyEntry.vue";

  export default {
    components: { MonthlyEntry },
    data() {
      return {
        addProjectSuccess: false,
        addProjectSuccessMessage: "Successfully Added Project!",
        addProjectFailure: false,
        addProjectFailureMessage: "Something Went Wrong! Please Try again...",
        contract: "",
        monthYear: "",
        phase: "",
        phaseDesign: false,
        phasePreConstruction: false,
        phaseConstruction: false,
        project: this.$store.state.project,
        monthly: [],
        monthlyItems: [],
        filteredMonthlyItems: [],
      };
    },
    methods: {
      allDone() {
        alert("Changes Saved");
      },
      updateBaseline() {
        this.filteredMonthlyItems = this.monthlyItems.filter((each) => {
          return this.phase == each.phaseDescription;
        });
      },
    },
    created() {
      const monthlyId = this.$route.params.id;
      console.log("id", monthlyId);
      MonthlyService.getMonthly(monthlyId)
        .then((response) => {
          if (response.status == 200 || response.status == 201) {
            this.monthly = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400) {
            alert("monthly ", error);
          }
        });
      MonthlyService.getMonthlyItems(monthlyId)
        .then((response) => {
          if (response.status == 200 || response.status == 201) {
            this.monthlyItems = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400) {
            alert("monthly ", error);
          }
        });
    },
  };
</script>

<style>
  .check-container {
    display: flex;
    flex-direction: row;
  }
</style>
