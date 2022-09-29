<template>
  <v-app id="app">
    <v-container id="landing-container" class="text-center"  >
<!--      <v-col :align-self="align" :key="align">-->
      <h1 id="introduction">
        Welcome to the Front Line Project Schedule App
      </h1>
      <router-link class="text-decoration-none" :to="{path: '/login'}">
        <v-btn class="button"
        color=#76a1c0
        elevation="2"
        outlined>Login</v-btn>
      </router-link>
      <br>
      <router-link class="text-decoration-none" :to="{path: '/register'}">
        <v-btn class="button"
        color=#76a1c0
        elevation="2"
        outlined>Register</v-btn>
      </router-link>
<!--      </v-col>-->
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
  background-size: cover;}

#introduction {
  color: white;
}


</style>