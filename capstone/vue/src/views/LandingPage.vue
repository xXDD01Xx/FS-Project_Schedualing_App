<template>
  <v-app id="app">
    <v-container id="landing-container" class="text-center"  >
<!--      <v-col :align-self="align" :key="align">-->
      <h1 id="introduction">
        Welcome to the Front Line Project Schedule App
      </h1>
      <router-link v-show="loginBtn"
          class="text-decoration-none" :to="{path: '/login'}">
        <v-btn class="button"
        color=#76a1c0
        elevation="2"
        outlined>Login</v-btn>
      </router-link>
      <br>
      <router-link class="text-decoration-none" :to="{path: '/register'}">
        <v-btn v-show="registerBtn"
            class="button"
        color=#76a1c0
        elevation="2"
        outlined>Register</v-btn>
      </router-link>
      <br>
      <router-link class="text-decoration-none" :to="{path: '/home'}">
        <v-btn v-show="homeBtn"
            class="button"
               color=#76a1c0
               elevation="2"
               outlined
        >Home</v-btn>
      </router-link>
<!--      </v-col>-->
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "LandingPage",
  data() {
    return {
      loginBtn: false,
      registerBtn: false,
      homeBtn: false,
    }
  },
  mounted: function()
  {
    this.showButtons();
  },
  methods: {
    showButtons()
    {
      if(this.$store.state.token)
      {
        this.homeBtn = true;
      }
      else
      {
        this.loginBtn = true;
        this.registerBtn = true;
      }
    }
  }
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