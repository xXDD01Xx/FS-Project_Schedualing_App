<template>
  <v-app>
      <h1>{{mod_co}}</h1>
  </v-app>
</template>

<script>
import ModCOService from '../services/ModCOService.js'

export default {
    data(){
        return {
            mod_co: {}
        }
    },
    created(){
       const id = this.$route.params
       ModCOService.getModCO(id).then((response) =>{
           if (response.status == 200 || response.status == 201){
               this.mod_co = response.data;
           }
       })
    }

}
</script>

<style>

</style>