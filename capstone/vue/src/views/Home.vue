<template>
  <v-app>
    <div class="home">
      <!-- <h1>Welcome, {{user.username}}</h1> -->
      <p>You must be authenticated to see this</p>
      <router-link :to="{path: '/login'}">Login</router-link>
    </div>

  </v-app>
</template>

<script>
export default {
  name: "home"
};
</script>
