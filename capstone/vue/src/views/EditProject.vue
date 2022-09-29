<template>
<v-app>
  <v-container>
  <p>{{ this.$store.state.project }}</p>


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
export default {
  name: "EditProject",
}
</script>

<style scoped>

</style>