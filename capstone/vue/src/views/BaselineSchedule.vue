<template>
  <v-app>
    <v-container >

      <v-form ref="form" lazy-validation>

        <v-text-field
            v-model="project.projectName"
            label="Project Name - From Project Creation"
            required
            :readonly="true"
        ></v-text-field>

        <v-text-field
            label="Contract - From Project Creation"
            :items="contracts"
            item-text="contractName"
            item-value="id"
            v-model="project.contractId"
            :readonly="true"
        ></v-text-field>

        <v-text-field
            v-model="project.dateReceived"
            type="date"
            label="Date Received - From Project Creation"
            required
            :readonly="true"
        ></v-text-field>
        <v-text-field
            v-model="project.budget"
            type="number"
            label="Budget - From Project Creation"
            placeholder="$"
            required
            :readonly="true"
        ></v-text-field>

        <v-container>
          <v-checkbox
              label="Phase Design"
              v-model="phaseDesign">
          </v-checkbox>
          <v-checkbox
              label="Pre Construction Design"
              v-model="phasePreConstruction">
          </v-checkbox>
          <v-checkbox
              label="Construction Design"
              v-model="phaseConstruction">
          </v-checkbox>
        </v-container>
        <PhaseDesignEntry
            v-show="phaseDesign">
        </PhaseDesignEntry>
        <PhasePreConstructionEntry
            v-show="phasePreConstruction">
        </PhasePreConstructionEntry>
        <PhaseConstructionEntry
            v-show="phaseConstruction">
        </PhaseConstructionEntry>
        <v-btn
            class="button"
            color="#8c090e"
            elevation="2"
            outlined
            @click="saveProject"
        >Submit Baseline Schedule
        </v-btn>
      </v-form>
      <div v-show="addProjectSuccess">{{ addProjectSuccessMessage }}</div>
      <div v-show="addProjectFailure">{{ addProjectFailureMessage }}</div>
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
import ProjectService from "@/services/ProjectService";
import PhaseDesignEntry from "@/components/PhaseDesignEntry";
import PhasePreConstructionEntry from "@/components/PhasePreConstructionEntry";
import PhaseConstructionEntry from "@/components/PhaseConstructionEntry";

export default {
  components: {PhaseConstructionEntry, PhasePreConstructionEntry, PhaseDesignEntry},
  data()
  {
    return {
      addProjectSuccess: false,
      addProjectSuccessMessage: "Successfully Added Project!",
      addProjectFailure: false,
      addProjectFailureMessage: "Something Went Wrong! Please Try again...",
      contracts: [],
      phaseDesign: false,
      phasePreConstruction: false,
      phaseConstruction: false,
      project: this.$store.state.project,
    };
  },
  methods: {
    saveProject()
    {
      console.log(this.project);
      console.log(this.con);
      ProjectService.addProject(this.project)
          .then((response) =>
          {
            if (response.status == 200 || response.status == 201)
            {
              this.addProjectSuccess = true;
              this.project = {};
              this.$router.push("/baselineSchedule");
            }
          })
          .catch((error) =>
          {
            const response = error.response;
            if (response.status == 400)
            {
              alert(this.addProjectFailureMessage);
            }
            if (response.status == 401)
            {
              alert(this.addProjectFailureMessage);
            }
            if (response.status == 500)
            {
              alert(this.addProjectFailureMessage);
            }
          });
    },

  },
  created()
  {
  },
};

</script>

<style>

</style>