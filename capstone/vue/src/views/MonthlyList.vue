<template>
  <v-app>
    <v-container class="text-center">
      <br />

      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn >
      </router-link>
      <v-btn
        class="button"
        @click="$router.back()"
        color="#8c090e"
        elevation="2"
        outlined
        >Back</v-btn
      >

      <br><br>
      <h5>
        {{monthly}}
      </h5>
      <v-select
          label="Month/Year"
          :items="monthly"
          item-text="monthYear"
          item-value="monthYear"
          required
          v-model="monthly.monthYear"
        ></v-select>

       
        <h3>Same as Last Month?  {{monthly.samePrevMonth}}</h3>
       
    </v-container>
  </v-app>
</template>

<script>
import MonthlyService from "../services/MonthlyService";
export default {
  data() {
    return {
      monthly: [],
    };
  },
  created() {
    MonthlyService.getMonthlyList(this.$store.state.project.id)
      .then((response) => {
        if (response.status == 200 || response.status == 201) {
          this.monthly = response.data;
          console.log(this.monthly)
        }
      })
      .catch((error) => {
        alert(error);
      });
  },
};
</script>

<style>
</style>