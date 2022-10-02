<template>
  <v-app
    ><br />
    <h2 class="text-center">Select a Month</h2>
    <br />
    <v-container class="text-center">
      <v-date-picker v-model="picker"  color="#8c090e" type="month"></v-date-picker>
      <v-btn
        class="text-decoration-none text-center"
        color="#8c090e"
        elevation="2"
        outlined
        @click="addMonthly(picker)"
        >Confirm</v-btn
      >
    <!-- <v-container row  v-show="displayMonthly">
      CENTER BUTTONS
      <h2 class="text-center">Same as last Month?</h2>
      LOOKS LIKE SHIT, FIX LATER
      <v-checkbox label="Yes"></v-checkbox>
      <v-checkbox v-model="sameAsLastMonth" label="No">No</v-checkbox>
    </v-container> -->

    <!-- <v-container v-show="sameAsLastMonth">
      <monthly-schedule :monthlyId="id"/>
    </v-container> -->
  
    
      <br /><br />
        <router-link
          class="text-decoration-none"
          :to="{ path: '/monthlyUpdate' }"
        >
          <v-btn class="button" color="#8c090e" elevation="2" outlined
            >Back</v-btn
          >
        </router-link>
        <router-link class="text-decoration-none" :to="{ path: '/home' }">
          <v-btn class="button" color="#8c090e" elevation="2" outlined
            >Home</v-btn
          >
        </router-link>
     
    </v-container>
  </v-app>
</template>

<script>
import MonthlyService from "../services/MonthlyService.js";
// import MonthlySchedule from '../views/MonthlySchedule.vue'

export default {
  components: {},
  data() {
    return {
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      id: "",
      displayMonthly: false,
      sameAsLastMonth: false,
    };
  },
  methods: {
    addMonthly(picker) {
      console.log(this.picker);
      const time = picker + "-01";
      console.log(time);
      MonthlyService.addMonthly(time, this.$store.state.project.id).then(
        (response) => {
          if (response.status == 200 || response.status == 201) {
            this.id = response.data;
            // this.loadMonthly(this.id)
            this.$router.push({name: 'MonthlySchedule', params:{id: this.id}});
          }
        }
      );
    },
    // loadMonthly(id){
    //   MonthlyService.getMonthly(id).then((response) => {
    //     if (response.status == 200 || response.status == 201){
    //       console.log(response.data)
    //       this.displayMonthly = true;
    //     }
    //   })
    // }
  },
};
</script>

<style>
</style>