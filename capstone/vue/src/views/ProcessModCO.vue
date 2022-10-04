<template>
  <v-app>
    <v-container>
            <v-form ref="form" lazy-validation>
                <v-text-field
                    v-model="mod_co.modCoName"
                    label="Mod/CO Name"
                    required
                    :readonly="true"
                ></v-text-field>

                <v-text-field
                    label="Court Date"
                    type="date"
                    v-model="mod_co.courtDate"
                    :readonly="true"
                ></v-text-field>

                <v-text-field
                    v-model="mod_co.type"
                    type="text"
                    :items="type"
                    label="Type"
                    required
                    :readonly="true"
                ></v-text-field>

                <v-text-field
                    v-model="sched"
                    type="text"
                    label="Will the Schedule be Impacted?"
                    required
                    :readonly="true"
                ></v-text-field>

                <v-textarea v-show="mod_co.scheduleImpacted"
                    label="How will the Schedule be Impacted?"
                    type="text"
                    v-model="mod_co.howSchedImpact"
                    :readonly="true"
                ></v-textarea>
                <v-textarea v-show="mod_co.scheduleImpacted"
                    label="Why will the Schedule be Impacted?"
                    type="text"
                    v-model="mod_co.whySchedImpact"
                    :readonly="true"
                ></v-textarea>

                <v-text-field
                    v-model="budget"
                    type="text"
                    label="Will The Budget be Impacted?"
                    required
                    :readonly="true"
                ></v-text-field>

                <v-textarea v-show="mod_co.budgetImpacted"
                    label="How will the Budget be Impacted?"
                    type="text"
                    v-model="mod_co.howBudgetImpact"
                    :readonly="true"
                ></v-textarea>
                <v-textarea v-show="mod_co.budgetImpacted"
                    label="Why will the Budget be Impacted?"
                    type="text"
                    v-model="mod_co.whyBudgetImpact"
                    :readonly="true"
                ></v-textarea>


                <v-textarea
                    label="Why are we submitting a Mod/Co?"
                    type="text"
                    v-model="mod_co.whySubmit"
                    required
                    :readonly="true"
                ></v-textarea>
                <v-textarea
                    label="Why 2?"
                    type="text"
                    v-model="mod_co.whyTwo"
                    :readonly="true"
                ></v-textarea>
                <v-textarea
                    label="Why 3?"
                    type="text"
                    v-model="mod_co.whyThree"
                    :readonly="true"
                ></v-textarea>
                <v-textarea
                    label="Why 4?"
                    type="text"
                    v-model="mod_co.whyFour"
                    :readonly="true"
                ></v-textarea>
                <v-textarea
                    label="Why 5?"
                    type="text"
                    v-model="mod_co.whyFive"
                    :readonly="true"
                ></v-textarea>
            </v-form>
        </v-container>
        <v-container>
            <v-btn class="text-decoration-none" color="#8c090e" elevation="2" outlined
            @click="approve(mod_co.id)"
            >Approve</v-btn>
            <v-btn class="text-decoration-none" color="#8c090e" elevation="2" outlined
            @click="deny(mod_co.id)"
            >Deny</v-btn>

        </v-container>

    <div class="text-center">
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn
        >
      </router-link>
    </div>
  </v-app>
</template>

<script>
import ModCOService from '../services/ModCOService.js'

export default {
    data(){
        return {
            mod_co: '',
            budget: '',
            sched: '',
        }
    },
    methods: {
        approve(id){
            const status = true;
            console.log(status)
            ModCOService.changeStatus(id, status).then((response) =>{
                if (response.status == 200 || response.status == 201){
                    this.$router.push({name: 'home'})
                }
            })
        },
        deny(id){
            const status = false;
            ModCOService.changeStatus(id, status).then((response) =>{
                if (response.status == 200 || response.status == 201){
                    this.$router.push({name: 'home'})
                }
            })
        }
    },
    created(){
       const id = this.$route.params.id
       ModCOService.getModCO(id).then((response) =>{
           if (response.status == 200 || response.status == 201){
               this.mod_co = response.data;
               console.log(this.mod_co)
           }
        if (this.mod_co.scheduleImpacted == true){
           this.sched = 'Yes'
       }else if (this.mod_co.scheduleImpacted == false){
           this.sched = 'No'
       }
       if (this.mod_co.budgetImpacted == true){
           this.budget = 'Yes'
       }else if (this.mod_co.budgetImpacted == false){
           this.budget = 'No'
       }
       })
    }

}
</script>

<style>

</style>