<template>
  <v-app>
    <div class="home">
      <h1>Welcome, {{user.username}}</h1>
      <p>You must be authenticated to see this</p>
    </div>

  </v-app>
</template>

<script>
export default {
  name: "home",
  data(){
    return {
      user: this.$store.state.user
    }
  }
};
</script>
