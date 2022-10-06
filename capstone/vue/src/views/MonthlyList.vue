<template>
  <v-app>
    <v-container class="text-center">
      <br />

      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn
        >
      </router-link>
      <v-btn
        class="button"
        @click="$router.back()"
        color="#8c090e"
        elevation="2"
        outlined
        >Back</v-btn
      >

      <br /><br />

      <v-select
        label="Month/Year"
        :items="monthly"
        item-text="monthYear"
        item-value="monthYear"
        required
        @change="filterPrev"
        v-model="monthYearSelected"
      ></v-select>

      <h3>Same as Last Month? {{ filteredMonthly.samePrevMonth ? "Yes":"No"}}</h3>
      <br />
      <v-textarea
      :readonly="true"
        label="Schedule Notes"
        type="text"
        v-model="filteredMonthly.scheduleNotes"
        required
      ></v-textarea>
      <v-text-field
      :readonly="true"
        label="Percent Complete"
        type="text"
        v-model="filteredMonthly.pctComplete"
        required
      ></v-text-field>
      <v-text-field
      :readonly="true"
        label="Project SPI"
        type="text"
        v-model="filteredMonthly.scheduleProdIdx"
        required
      ></v-text-field>
      <v-textarea
      :readonly="true"
        label="Why did the Schedule Change?"
        type="text"
        v-model="filteredMonthly.whyOne"
        required
        v-if="!this.filteredMonthly.samePrevMonth"
      >
      </v-textarea>
      <v-textarea
      :readonly="true"
      v-if="!this.filteredMonthly.samePrevMonth"
        label="Why 2?"
        type="text"
        v-model="filteredMonthly.whyTwo"
      >
      </v-textarea>
      <v-textarea
      :readonly="true"
      v-if="!this.filteredMonthly.samePrevMonth"
        label="Why 3?"
        type="text"
        v-model="filteredMonthly.whyThree"
      >
      </v-textarea>
      <v-textarea
      :readonly="true"
      v-if="!this.filteredMonthly.samePrevMonth"
        label="Why 4?"
        type="text"
        v-model="filteredMonthly.whyFour"
      >
      </v-textarea>
      <v-textarea
      :readonly="true"
      v-if="!this.filteredMonthly.samePrevMonth"
        label="Why 5?"
        type="text"
        v-model="filteredMonthly.whyFive"
      >
      </v-textarea>
      <br />
    </v-container>
  </v-app>
</template>

<script>
import MonthlyService from "../services/MonthlyService";
export default {
  data() {
    return {
      monthly: [],
      filteredMonthly: {},
      monthYearSelected: "",
    };
  },
  created() {
    MonthlyService.getMonthlyList(this.$store.state.project.id)
      .then((response) => {
        if (response.status == 200 || response.status == 201) {
          this.monthly = response.data;
          console.log(this.monthly);
        }
      })
      .catch((error) => {
        alert(error);
      });
  },
  methods: {
    filterPrev() {
      this.filteredMonthly = this.monthly.find((findMonth) => {
        return findMonth.monthYear === this.monthYearSelected;
      });
    },
  },
};
</script>

<style>
</style>