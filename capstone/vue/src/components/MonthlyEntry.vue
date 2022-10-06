<template>
  <v-app v-show="populated">
    <v-container 
      v-for="item in monthlyItems"
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
            @blur="saveMonthlyItem(item)"
          >
          </v-text-field>
        </v-col>

        <v-col cols="12" sm="2">
          <v-text-field
            v-model="item.itemTasks"
            type="text"
            label="No. of Tasks"
            required
            @blur="saveMonthlyItem(item)"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
    <div class="text-center">
      <v-btn class="text-decoration-none" color="#8c090e" elevation="2" outlined
        @click="submitChanges">Submit Changes</v-btn>
    </div>
  </v-app>
</template>

<script>
import MonthlyService from "../services/MonthlyService.js";
  
  export default {
    data() {
      return {
        phaseDesign: false,
        phasePreConstruction: false,
        phaseConstruction: false,
      };
    },
    props: ["monthlyItems"],
    methods: {
      submitChanges(){
        this.$router.push({name: 'home'})
      },
      saveMonthlyItem(item){
          MonthlyService.updateMonthlyItem(item)
          .then((response)=>{
              if (response.status == 200 || response.status == 201) {
            // should indicate it was updated, maybe flash green?
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status == 400) {
            //
          } 
          })
      }
    },
    computed: {
      populated() {
        return this.monthlyItems.length > 0;
      },
    },
  };
</script>

<style></style>
