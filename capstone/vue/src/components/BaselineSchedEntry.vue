<template>
  <v-app v-show="populated">
    <v-container dense
      v-for="item in baselineItems"
      :key="item.id"
      id="item-container"
      ><br />
      <v-row dense="true">
        <v-col cols="6" sm="3"> </v-col>
        <v-col cols="6">
          <h4>{{ item.itemDescription }}</h4>
        </v-col>
        <v-col cols="6" sm="3"> </v-col>
      </v-row>

      <v-row dense="true">
          <v-spacer></v-spacer>
        <v-col cols="12" sm="2">
          <v-text-field
            style="width:38%"
            v-model="item.itemDate"
            v-bind:style="{
              backgroundColor: item.itemDate ? 'lightgreen' : '',
              height: '12px',
              opacity: '80%',
            }"
            label="Date"
            type="date"
            required
            @blur="saveBaselineItem(item)"
          >
          </v-text-field>
        </v-col>

        <v-col cols="12" sm="2">
          <v-text-field
            style="width:35%"
            v-model="item.itemTasks"
            v-bind:style="{
              backgroundColor: item.itemTasks ? 'lightgreen' : '',
              height: '12px',
              opacity: '80%',
            }"
            type="text"
            label="Number of Tasks"
            required
            @blur="saveBaselineItem(item)"
          ></v-text-field>
        </v-col>
          <v-spacer></v-spacer>
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

  @keyframes fadeOut {
    0% {
      background-color: green;
    }
    100% {
      background-color: white;
    }
  }

  .changedata {
    animation: fadeOut 2.5s ease;
  }

</style>
