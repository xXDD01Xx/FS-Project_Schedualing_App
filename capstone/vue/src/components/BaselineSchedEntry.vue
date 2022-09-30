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
          <v-btn @click="check">click</v-btn>
        </v-container>
  </v-app>
</template>

<script>


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
    methods: {
        check(){
            console.log(this.baselineItems)
        }
    }

}
</script>

<style>

</style>