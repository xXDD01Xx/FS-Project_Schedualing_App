<template>
  <v-app>
    <v-container>
      <v-simple-table>
        <thead>
          <th>Name</th>
          <th>Court Date</th>
          <th>Schedule Impacted?</th>
          <th>Budget Impacted?</th>
          <th>View Details</th>
        </thead>
        <tbody>
          <tr v-for="mod in modList" :key="mod.id">
            <td>{{mod.modCoName}}</td>
            <td>{{mod.courtDate}}</td>
            <td>{{mod.scheduleImpacted}}</td>
            <td>{{mod.budgetImpacted}}</td>
            <td>
              <v-btn
              elevation="2"
              class="text-decoration-none"
              @click="viewModCo(mod)">
                View
              </v-btn>
            </td>
          </tr>
        </tbody>
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
  methods: {
    viewModCo(mod){
      this.$router.push({name: 'ProcessModCO', params: {id: mod.id}})
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