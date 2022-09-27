<template>
  <v-app>
    <v-app-bar
      app
      color=#02091b
      dark

    >
      <login-page />
      <div class="d-flex align-center">
        <img src="./assets/flag-logo.svg" height="100" width="100"/>
        <!-- <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        /> -->

        <!-- <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        /> -->
      </div>

      <v-spacer></v-spacer>

<!--      <router-link :to="{path: '/login'}" class="login">-->
<!--        <span class="mr-2">Login</span>-->
<!--        &lt;!&ndash; <v-icon>mdi-open-in-new</v-icon> &ndash;&gt;-->
<!--      </router-link>-->
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

import LoginPage from "@/components/LoginPage";

export default {
  name: 'App',

  components: {
    LoginPage,
  },

  data: () => ({
    //
  }),
};
</script>


<style scoped>
.login{
  color: white;
}
</style>