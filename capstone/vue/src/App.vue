<template>
  <v-app>
    <v-app-bar
      app
      color=#02091b
      dark

    >
      <div class="d-flex align-center">
        <img src="./assets/flag-logo.svg" height="100" width="100"/>
      </div>
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>


export default {
  name: 'App',

  components: {
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