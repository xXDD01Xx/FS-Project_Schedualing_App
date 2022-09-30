<template>
  <v-app v-show="populated">
    <v-container v-for="item in baselineItems" :key="item.id">
        <h4>{{item.itemDescription}}</h4>
        <v-text-field
            v-model="item.itemDate"
            label="Date"
            type="date"
            required
            @blur="saveBaselineItem(item)"
        ></v-text-field>
        <v-text-field
            v-model="item.itemTasks"
            type="text"
            label="Tasks"
            required
            @blur="saveBaselineItem(item)"
        ></v-text-field>
    </v-container>
  </v-app>
</template>

<script>
import ScheduleService from '../services/ScheduleService.js'

export default {
    data(){
        return {
            phaseDesign: false,
            phasePreConstruction: false,
            phaseConstruction: false,
            contract: {},
        }
    },
    props: ['baselineItems'],
    methods: {
        check(){
            console.log(this.baselineItems)
        },
        saveBaselineItem(item){
            ScheduleService.updateBaselineItem(item).then((response) =>{
                if (response.status == 200 || response.status == 201){
                    //
                }
            })
            .catch((error) => {
                const response = error.response;
                if (response.status == 400){
                    console.log(response.data.message)
                }
            })
        }
    },
    computed: {
        populated(){
            return this.baselineItems.length > 0;
        }
    }

}
</script>

<style>

</style>