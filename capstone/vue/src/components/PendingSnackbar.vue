<template>
    <v-snackbar
      :timeout="timeout">
      {{ snackText }}
    </v-snackbar>
</template>

<script>
export default {
    data(){
        return {
            snackText: 'Pending Users to be Approved!',
            timeout: 200000000,
        }
    },
    created(){
        
    }

}
</script>

<style>

</style>