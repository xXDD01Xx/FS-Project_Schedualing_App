<template>
  <v-app>
      <h2 class="text-center">{{this.$store.state.project.projectName}}</h2>
      <v-container class=check-container>
          <v-checkbox
              label="Design" value="Design"
              @click="updateBaseline"
              v-model="phase">
          </v-checkbox>
          <v-checkbox
              label="Pre Construction" value="Pre-Construction"
              @click="updateBaseline"
              v-model="phase">
          </v-checkbox>
          <v-checkbox
              label="Construction" value="Construction"
              @click="updateBaseline"
              v-model="phase">
          </v-checkbox>
        </v-container>
        <h2>{{phase}}</h2>
      <v-simple-table>
          <thead>
              <th class="desc">Item</th>
              <th class="phase">Phase</th>
              <th class="task">Tasks</th>
              <th class="date">Date</th>
          </thead>
          <tbody>
              <tr v-for="item in filteredBaseline" :key="item.id" :item="item">
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
            baselineItems: [],
            phase: '',
            filteredBaseline: []
        }
    },
    methods: {
        updateBaseline(){
            this.filteredBaseline = this.baselineItems.filter((each) => {
                return this.phase == each.phaseDescription;
            })
        }
    },
    created(){
        ScheduleService.listBaselineItems(this.$store.state.project.id).then((response) =>{
            if (response.status == 200 || response.status == 201){
                this.baselineItems = response.data;
                console.log(this.baselineItems)
            }
        })
        .catch((error) => {
            const response = error.data;
            if (response.state == 400){
                alert(response)
            }
        })
    }

}
</script>

<style>
.desc{
    max-width: 20%;
}
.check-container{
    display: flex;
    max-width: 40%;
    flex-direction: row;
    justify-content: center;
    justify-content: space-evenly;
}

</style>