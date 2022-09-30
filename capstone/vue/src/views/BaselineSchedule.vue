<template>
  <v-app>
    <v-container >
      <v-btn @click="check">click</v-btn>

      <v-form ref="form" lazy-validation>

        <v-text-field
            v-model="project.projectName"
            label="Project Name - From Project Creation"
            required
            :readonly="true"
        ></v-text-field>

        <v-text-field
            label="Contract - From Project Creation"
            type="text"
            v-model="name"
            :readonly="true"
            required
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
              label="Design" value="Design"
              @click="updateBaseline"
              v-model="phase">
          </v-checkbox>
          <v-checkbox
              label="Pre Construction" value="PreConstruction"
              v-model="phase">
          </v-checkbox>
          <v-checkbox
              label="Construction" value="Construction"
              v-model="phase">
          </v-checkbox>
          <p>{{phase}}</p>
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


        <BaselineSchedEntry :baselineItems="filteredBaseline"/>

        

        <v-btn
            class="button"
            color="#8c090e"
            elevation="2"
            outlined
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
      >Home
      </v-btn>
    </router-link>
  </v-app>
</template>

<script>
// import ProjectService from "@/services/ProjectService";
import PhaseDesignEntry from "@/components/PhaseDesignEntry";
import PhasePreConstructionEntry from "@/components/PhasePreConstructionEntry";
import PhaseConstructionEntry from "@/components/PhaseConstructionEntry";
import ScheduleService from '../services/ScheduleService.js'
import ProjectService from '../services/ProjectService.js'
import BaselineSchedEntry from '../components/BaselineSchedEntry.vue'

export default {
  components: {PhaseConstructionEntry, PhasePreConstructionEntry, PhaseDesignEntry, BaselineSchedEntry},
  data()
  {
    return {
      addProjectSuccess: false,
      addProjectSuccessMessage: "Successfully Added Project!",
      addProjectFailure: false,
      addProjectFailureMessage: "Something Went Wrong! Please Try again...",
      contract: '',
      name: '',
      phase: '',
      phaseDesign: false,
      phasePreConstruction: false,
      phaseConstruction: false,
      project: this.$store.state.project,
      baselineItems: [],
      filteredBaseline: [],
    };
  },
  methods: {
    check(){
      console.log('contract',this.contract)
      console.log('project',this.project)
    },
    updateBaseline(){
      console.log(this.project)
      this.filteredBaseline = this.baselineItems.filter((each) => {
        return this.phase == each.phaseDescription;
      })
      console.log(this.filteredBaseline)
    }
  },
  created(){
        const baseID = this.$route.params.id
        console.log('id',baseID)
        ScheduleService.listBaselineItems(baseID).then((response) =>{
            console.log('test')
            console.log(response.data)
            if (response.status == 200 || response.status == 201){
                this.baselineItems = response.data;
            }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400){
            alert('baseline',error)
          }
        })
        ProjectService.getContracts().then((response) =>{
            this.contract = response.data.filter((each) => {
                 return each.id == this.project.contractId
            })
            this.name = this.contract[0].contractName
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400){
            alert('contract',response)
          }
        })

    },
};


</script>

<style>

</style>