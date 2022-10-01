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

export default {
  data() {
    return {
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      id: "",
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
            console.log(this.id);
          }
        }
      );
    },
  },
};
</script>

<style>
</style>