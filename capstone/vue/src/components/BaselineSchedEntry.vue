<template>
  <v-app>
      <v-container>
          <!-- <v-checkbox
              label="Design"
              v-model="phaseDesign">
          </v-checkbox>
          <v-checkbox
              label="Pre Construction"
              v-model="phasePreConstruction">
          </v-checkbox>
          <v-checkbox
              label="Construction"
              v-model="phaseConstruction">
          </v-checkbox> -->
        </v-container>
  </v-app>
</template>

<script>
//import ProjectService from '../services/ProjectService'
// import ScheduleService from '../services/ScheduleService'
//import ScheduleService from '@/services/ScheduleService.js'

export default {
    data(){
        return {
            phaseDesign: false,
            phasePreConstruction: false,
            phaseConstruction: false,
            contract: {}
        }
    },
    props: ['baselineItems'],
    // created(){
    //     ScheduleService.listBaselineItems(this.$store.state.project.id).then((response) =>{
    //         console.log('test')
    //         console.log(response.data)
    //         if (response.status == 200 || response.status == 201){
    //             this.baselineItems = response.data;
    //         }
    //     })
    // }

}
</script>

<style>

</style>