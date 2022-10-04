<template>
  <v-app>
    <v-container>
      <v-simple-table>
        <tr>
          <th>Name</th>
          <th>Project</th>
          <th>Court Date</th>
          <th>Schedule Impacted?</th>
          <th>Budget Impacted?</th>
        </tr>
        <tr v-for="mod in modList" :key="mod.id">
          <td>{{mod.name}}</td>
          <!-- <td>{{mod.projectName}}</td> -->
          <td>{{mod.courtDate}}</td>
          <td>{{mod.scheduleImpacted}}</td>
          <td>{{mod.budgetImpacted}}</td>
          <td>
            <router-link>
              <!-- FINISH ROUTER LINK -->
              <v-btn>
                View Details
              </v-btn>
            </router-link>
          </td>
        </tr>
      </v-simple-table>
    </v-container>
    <router-link class="text-decoration-none" :to="{path: '/home'}">
      <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Home</v-btn>
    </router-link>
  </v-app>
</template>

<script>
import ModCOService from '../services/ModCOService.js'

export default {
  components: {},
  data(){
    return {
      modList: ''
    }
  },
  created(){
    ModCOService.listModCO().then((response) => {
      if (response.status == 200 || response.status == 201){
        this.modList = response.data;
      }
    })
  }

}
</script>

<style>

</style>