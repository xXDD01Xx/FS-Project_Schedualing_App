<template>
  <v-app v-show="populated">
    <v-container
      v-for="item in baselineItems"
      :key="item.id"
      id="item-container"
    >
      <v-row>
        <v-col cols="6" sm="3"> </v-col>
        <v-col cols="6">
          <h4>{{ item.itemDescription }}</h4>
        </v-col>
        <v-col cols="6" sm="3"> </v-col>
      </v-row>

      <v-row>
        <v-col cols="12" sm="2"></v-col>
        <v-col cols="12" sm="2"></v-col>
        <v-col cols="12" sm="2">
          <v-text-field
            v-model="item.itemDate"
            label="Date"
            type="date"
            required
            @blur="saveBaselineItem(item)"
          >
          </v-text-field>
        </v-col>

        <v-col cols="12" sm="2">
          <v-text-field
            v-model="item.itemTasks"
            type="text"
            label="No. of Tasks"
            required
            @blur="saveBaselineItem(item)"
          ></v-text-field>
        </v-col>
        
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
  import ScheduleService from "../services/ScheduleService.js";

  export default {
    data() {
      return {
        phaseDesign: false,
        phasePreConstruction: false,
        phaseConstruction: false,
        contract: {},
      };
    },
    props: ["baselineItems"],
    methods: {
      check() {
        console.log(this.baselineItems);
      },
      saveBaselineItem(item) {
        ScheduleService.updateBaselineItem(item)
          .then((response) => {
            if (response.status == 200 || response.status == 201) {
              //
            }
          })
          .catch((error) => {
            const response = error.response;
            if (response.status == 400) {
              console.log(response.data.message);
            }
          });
      },
    },
    computed: {
      populated() {
        return this.baselineItems.length > 0;
      },
    },
  };
</script>

<style>
  .item-container {
    max-width: 50%;
    width: 50%;
  }
</style>
