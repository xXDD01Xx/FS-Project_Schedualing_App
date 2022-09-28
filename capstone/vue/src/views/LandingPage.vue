<template>
  <v-app id="app">
    <v-container id="landing-container" class="text-center">
      <router-link :to="{path: '/login'}">
        <v-btn class="button"
        color=#76a1c0
        elevation="2"
        outlined>Login</v-btn>
      </router-link>
      <br>
      <router-link :to="{path: '/register'}">
        <v-btn class="button"
        color=#76a1c0
        elevation="2"
        outlined>Register</v-btn>
      </router-link>
    </v-container>
    <v-container>
      <!-- <v-img id="logo" src="../assets/flag-logo.svg"/> -->
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "LandingPage"
}
</script>

<style scoped>
#app{
  background: url('../assets/hero-1.jpeg') no-repeat center center fixed !important;
  background-repeat: no-repeat;
  background-size: cover;
}
/* #logo {

} */

</style>