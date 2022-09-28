<template>
  <v-app>
    <v-app-bar app color="#02091b" dark>
      <div class="d-flex align-center">
        <img src="./assets/flag-logo.svg" height="100" width="100" />
      </div>
      <v-spacer></v-spacer>
      <v-btn v-if="this.$store.state.token" @click="handleLogout">Logout</v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
  export default {
    name: "App",

    components: {},

    data: () => ({
      //
    }),
    methods: {
      handleLogout() {
        this.$store.commit("LOGOUT");
        this.$router.push({ name: "login" });
      },
    },
  };
</script>

<style scoped>
  .login {
    color: white;
  }
</style>
