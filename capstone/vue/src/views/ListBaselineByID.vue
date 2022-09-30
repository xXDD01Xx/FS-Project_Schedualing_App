<template>
  <v-app>
      <v-simple-table>
          <thead>
              <th class="desc">Item</th>
              <th class="phase">Phase</th>
              <th class="task">Tasks</th>
              <th class="date">Date</th>
          </thead>
          <!-- <h4>{{baselineItems}}</h4> -->
          <tbody>
              <tr v-for="item in baselineItems" :key="item.id" :item="item">
                <td class="desc">{{item.itemDescription}}</td>
                <td class="phase">{{item.phaseDescription || 0}}</td>
                <td class="task">{{item.itemTasks}}</td>
                <td class="date">{{item.itemDate || 0}}</td>
              </tr>
          </tbody>
      </v-simple-table>
     <div class="text-center">
      <router-link class="text-decoration-none" :to="{path: '/home'}">
        <v-btn class="button"
               color=#8c090e
               elevation="2"
               outlined
        >Home</v-btn>
      </router-link>
    </div>
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
    methods: {},
    created(){
        ScheduleService.listBaselineItems(this.$store.state.project.id).then((response) =>{
            console.log('test')
            console.log(response.data)
            if (response.status == 200 || response.status == 201){
                this.baselineItems = response.data;
            }
        })
    }

}
</script>

<style>
.desc{
    max-width: 20%;
}

</style>