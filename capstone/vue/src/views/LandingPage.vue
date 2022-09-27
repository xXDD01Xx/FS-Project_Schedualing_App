<template>
  <v-app>
    <router-link :to="{path: '/login'}">Login</router-link>
    <br>
    <router-link :to="{path: '/register'}">Register</router-link>
  </v-app>
</template>

<script>
export default {
  name: "LandingPage"
}
</script>

<style scoped>

</style>