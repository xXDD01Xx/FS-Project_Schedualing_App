<template>
  <v-app>
      
        <template>
            <v-row justify="center">
                <v-date-picker
                v-model="picker"
                type="month"
                ></v-date-picker>
            </v-row>
        </template>









      <router-link class="text-decoration-none" :to="{path: '/monthlyUpdate'}">
        <v-btn class="button"
               color=#8c090e
               elevation="2"
               outlined
        >Back</v-btn>
      </router-link>
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
export default {

}
</script>

<style>

</style>