<template>
    <v-app>
        <v-container>
            <br>
            <h2 class="text-center">Log Modification/Change Order</h2>
             <br>
            <v-form ref="form" lazy-validation>
                <v-text-field
                    v-model="project.projectName"
                    label="Project Name"
                    required
                    :readonly="true"
                ></v-text-field>

                <v-text-field
                    label="Contract Name"
                    type="text"
                    :readonly="true"
                    v-model="name"
                ></v-text-field>

                <v-text-field
                    v-model="project.dateReceived"
                    type="date"
                    label="Date Received"
                    required
                    :readonly="true"
                ></v-text-field>
                <v-text-field
                    v-model="project.budget"
                    type="number"
                    label="Budget"
                    placeholder="$"
                    required
                    :readonly="true"
                ></v-text-field>
            </v-form>
        </v-container>
        <v-container>
            <v-form ref="form" lazy-validation>
                <v-text-field
                    v-model="mod_co.modCoName"
                    label="Mod/CO Name"
                    required
                ></v-text-field>

                <v-text-field
                    label="Court Date"
                    type="date"
                    v-model="mod_co.courtDate"
                ></v-text-field>

                <v-select
                    v-model="mod_co.type"
                    type="text"
                    :items="type"
                    label="Type"
                    required
                ></v-select>

                <v-select
                    v-model="scheduleImpacted"
                    type="boolean"
                    :items="yesAndNo"
                    label="Will the Schedule be Impacted?"
                    required
                    @change="setModCoScheduleImpact"
                ></v-select>

                <v-textarea v-show="mod_co.scheduleImpacted"
                    label="How will the Schedule be Impacted?"
                    type="text"
                    v-model="mod_co.howSchedImpact"
                ></v-textarea>
                <v-textarea v-show="mod_co.scheduleImpacted"
                    label="Why will the Schedule be Impacted?"
                    type="text"
                    v-model="mod_co.whySchedImpact"
                ></v-textarea>

                <v-select
                    v-model="budgetImpacted"
                    :items="yesAndNo"
                    type="boolean"
                    label="Will The Budget be Impacted?"
                    required
                    @change="setModCoBudgetImpact"
                ></v-select>
                <!-- PLEASE FIX BLUR -->

                <v-textarea v-show="mod_co.budgetImpacted"
                    label="How will the Budget be Impacted?"
                    type="text"
                    v-model="mod_co.howBudgetImpact"
                ></v-textarea>
                <v-textarea v-show="mod_co.budgetImpacted"
                    label="Why will the Budget be Impacted?"
                    type="text"
                    v-model="mod_co.whyBudgetImpact"
                ></v-textarea>


                <v-textarea
                    label="Why are we submitting a Mod/Co?"
                    type="text"
                    v-model="mod_co.whySubmit"
                    required
                ></v-textarea>
                <v-textarea
                    label="Why 2?"
                    type="text"
                    v-model="mod_co.whyTwo"
                ></v-textarea>
                <v-textarea
                    label="Why 3?"
                    type="text"
                    v-model="mod_co.whyThree"
                ></v-textarea>
                <v-textarea
                    label="Why 4?"
                    type="text"
                    v-model="mod_co.whyFour"
                ></v-textarea>
                <v-textarea
                    label="Why 5?"
                    type="text"
                    v-model="mod_co.whyFive"
                ></v-textarea>
            </v-form>

        </v-container>
            <div class="text-center">
                <br>
            <v-btn
                class="button"
                color="#8c090e"
                elevation="2"
                outlined
                @click="logModCO"
                >Submit Modification/Change Order</v-btn
            ></div>
            <div v-show="addProjectSuccess">{{ addProjectSuccessMessage }}</div>
            <div v-show="addProjectFailure">{{ addProjectFailureMessage }}</div>
        
            <div class="text-center">
              <v-container class="text-center">
                <br /><br />
                <router-link
                    class="text-decoration-none"
                    :to="{ path: '/viewProjects' }">
                  <v-btn class="button" color="#8c090e" elevation="2" outlined
                  >Back</v-btn>
                </router-link>
                <router-link class="text-decoration-none" :to="{ path: '/home' }">
                  <v-btn class="button" color="#8c090e" elevation="2" outlined
                  >Home</v-btn
                  >
                </router-link>
              </v-container>
        </div>
    </v-app>
</template>

<script>
import ModCOService from '../services/ModCOService.js';
import ProjectService from "../services/ProjectService.js";

  export default {
    data() {
      return {
        addProjectSuccess: false,
        addProjectSuccessMessage: "Successfully Added Project!",
        addProjectFailure: false,
        addProjectFailureMessage: "Something Went Wrong! \n Please Try again...",
        contract: [],
        yesAndNo: [
            'Yes',
            'No'
        ],
        type: [
            'Modification',
            'Change Order'
        ],
        scheduleImpacted: '',
        budgetImpacted: '',
        name: '',
        placeholder: '',
        project: {
          id: '',
          projectName: "",
          dateReceived: "",
          budget: "",
          contractId: "",
          //TODO Add contract Name? Add substantial/construction tasks
        },
        mod_co: {
            modCoName: '',
            projectId: '',
            courtDate: '',
            scheduleImpacted: '',
            whySchedImpact: '',
            howSchedImpact: '',
            budgetImpacted: '',
            whyBudgetImpact: '',
            howBudgetImpact: '',
            whySubmit: '',
            whyTwo: '',
            whyThree: '',
            whyFour: '',
            whyFive: '',
            approved: '',
            approvedTimestamp: '',
        }
      };
    },
    methods: {
        logModCO(){
            const id = this.project.id;
            this.mod_co.projectId = id;
            console.log(this.mod_co)
             ModCOService.addModCO(this.mod_co).then((response) => {
                if (response.status == 200 || response.status == 201){
                    this.placeholder = response.data
                    this.$router.push({name: 'home'})
                }
            })
            .catch((error) => {
                alert(error)
            })
            console.log('out',this.placeholder)
        },
        setModCoScheduleImpact(){
            if (this.scheduleImpacted == "Yes"){
                this.mod_co.scheduleImpacted = true;
            }else if (this.scheduleImpacted == 'No'){
                this.mod_co.scheduleImpacted = false;
            }
        },
        setModCoBudgetImpact(){
            if (this.budgetImpacted == "Yes"){
                this.mod_co.budgetImpacted = true;
            }else if (this.budgetImpacted == 'No'){
                this.mod_co.budgetImpacted = false;
            }
        }
     },
    created() {
        this.project = this.$store.state.project;
        console.log(this.project)
        ProjectService.getContracts().then((response) => {
            this.contract = response.data.filter((each) =>{
                return each.id == this.project.contractId
            })
            this.name = this.contract[0].contractName;
            console.log(this.name)
      });
    },
  }
</script>

<style>

</style>