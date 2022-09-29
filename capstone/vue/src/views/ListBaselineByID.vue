<template>
  <v-app>
      <v-simple-table>
          <thead>
              <th>Item Phase</th>
              <th>Item Date</th>
              <th>Item Tasks</th>
              <th>Item Description</th>
          </thead>
          <tbody>
              <tr v-for="item in baselineItems" :key="item" :item="item"></tr>
                <td>{{item.phaseDescription}}</td>
                <td>{{item.itemDate}}</td>
                <td>{{item.itemTasks}}</td>
                <td>{{item.itemDescription}}</td>
          </tbody>
      </v-simple-table>
  </v-app>
</template>

<script>
import ScheduleService from '../services/ScheduleService.js'

export default {
    name: 'ListBaselineByID',
    data(){
        return {
            baselineItems: []
        }
    },
    created(){
        ScheduleService.listBaselineItems(this.$store.state.project.id).then((response) =>{
            if (response.status == 200 || response.status == 201){
                this.baselineItems = response.data;
            }
        })
    }

}
</script>

<style>

</style>