<template>
  <v-app>
    <v-container id="main-table">
      <br>
      <h2 class="text-center">Review Pending Users</h2>
      <br><br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr class="trow">
            <th class="text-center">Username</th>
            <th class="text-center">Date Account Created</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users" :key="user.username">
            <td>{{ user.username }}</td>
<!--            <td>{{ user.}}</td>-->
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-container>
    <br>
    <div class="text-center">
    <router-link class="text-decoration-none" :to="{path: '/changeUserStatus'}">
      <v-btn class="button"
             color=#8c090e
             elevation="2"
             outlined
      >Change User Status</v-btn>
    </router-link>
    </div>
    <br><br>
    <div class="text-center">
      <router-link class="text-decoration-none" :to="{path: '/home'}">
      <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Home</v-btn>
    </router-link>
    </div>
  </v-app>
</template>

<script>
export default {
data()
{
  return {
    user: this.$store.state.user
  }
}

}
</script>

<style>
#main-table {
  max-width: 60%;
  padding: 10px;
}

.trow {
  border-right: 4px;
  border-top: 4px;
}
</style>