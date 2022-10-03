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
          v-model="monthly.date"
          :readonly="true"
          required
        ></v-text-field>

        <v-text-field
          label="Schedule Notes"
          type="text"
          v-model="monthly.scheduleNotes"
          required
        ></v-text-field>

        <v-text-field
          label="Percent Complete"
          type="text"
          v-model="monthly.pctComplete"
          required
        ></v-text-field>

        <v-radio-group row v-model="flipper">
          <h3>Same as Last Month?</h3>
          <v-radio label="Yes" value=true @click="flipTheFlipper"></v-radio>
          <v-radio label="No" value=false @click="flipTheFlipper"></v-radio>
        </v-radio-group>

      </v-form>
    </v-container>
    <v-container class="check-container" v-show="!monthly.samePrevMonth">
      <v-checkbox
        label="Design"
        value="Design"
        @click="filterMonthly"
        v-model="phase"
      >
      </v-checkbox>
      <v-checkbox
        label="Pre Construction"
        value="Pre-Construction"
        @click="filterMonthly"
        v-model="phase"
      >
      </v-checkbox>
      <v-checkbox
        label="Construction"
        value="Construction"
        @click="filterMonthly"
        v-model="phase"
      >
      </v-checkbox>
    </v-container>
    <v-container v-show="!monthly.samePrevMonth">
      <h2 class="text-center">{{ phase }}</h2>
      <MonthlyEntry :monthlyItems="filteredMonthlyItems" />
    </v-container>
    <div class="text-center" v-show="flipper == 'true'">
      <v-btn class="text-decoration-none" color="#8c090e" elevation="2" outlined
        @click="submitChanges">Submit Changes</v-btn>
    </div>
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
        project: this.$store.state.project,
        monthly: {},
        monthlyItems: [],
        filteredMonthlyItems: [],
        flipper: '',
      };
    },
    methods: {
      flipTheFlipper(){
        if (this.flipper == 'true'){
          this.monthly.samePrevMonth = true;
        }else if (this.flipper == 'false'){
          this.monthly.samePrevMonth = false;
        }
      },
      submitChanges(){
        this.$router.push({name: 'home'})
      },
      allDone() {
        alert("Changes Saved");
      },
      filterMonthly() {
        this.filteredMonthlyItems = this.monthlyItems.filter((each) => {
          return this.phase === each.phaseDescription;
        });
        console.log(this.filteredMonthlyItems)
      },
    },
    created() {
      const monthlyId = this.$route.params.id;
      console.log("id", monthlyId);
      MonthlyService.getMonthly(monthlyId)
        .then((response) => {
          if (response.status == 200 || response.status == 201) {
            this.monthly = response.data;
            // this.monthly.samePrevMonth = true;
            console.log('mon sched', this.monthly)
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
            console.log('month items',this.monthlyItems)
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400) {
            alert("monthly items", error);
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
