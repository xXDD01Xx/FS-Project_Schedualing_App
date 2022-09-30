<template>
  <v-app>
    <v-container id="main-table">
      <br>
      <h2 class="text-center">Change User Status</h2>
      <br><br>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr class="trow">
            <th class="text-left">Username</th>
            <th class="text-left">Status</th>
            <th class="text-left">Approve User</th>
            <th class="text-left">Deny User</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users" :key="user.username">
            <td>{{ user.username }}</td>
            <td>{{ user.status }}</td>
            <td>
              <input
                  type="radio"
                  name="Approve User"
                  class="button"
                  value="Active"
                  v-model="user.status"
              />
            </td>
            <td>
              <input
                  type="radio"
                  name="Deny User"
                  class="button"
                  value="Not Approved"
                  v-model="user.status"
              />
            </td>
          </tr>
          </tbody>

          <div v-if="submitError">{{ submitErrorMessage }}</div>
          <div v-if="submitSuccess">{{ submitSuccessMessage }}</div>
        </template>
      </v-simple-table>
    </v-container>
    <br>
    <div class="text-center">
      <v-btn class="button"
             @click="submitAll"
             color="#8c090e"
             elevation="2" outlined>
        Submit
      </v-btn>
      <br><br>
    </div>
    <div class="text-center">
    <router-link :to="{ path: '/home' }" class="text-decoration-none">
      <v-btn color="#8c090e"
             elevation="2"
             outlined>
        Home
      </v-btn>
    </router-link>
    </div>
  </v-app>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  data()
  {
    return {
      users: [],
      submitError: false,
      submitSuccess: false,
      submitErrorMessage: "Error",
      submitSuccessMessage: "Success",
      user: this.$store.state.user
    };
  },
  methods: {
    submitAll()
    {
      let u = this.users;
      AuthService.saveChangeUserStatus(u).then((response) =>
      {
        if (response.status == 200)
        {
          this.submitSuccess = true;
        } else if (response.status == 400)
        {
          this.submitError = true;
        }
      });
    },
  },
  created()
  {
    if (this.$store.state.users == [])
    {
      AuthService.getAllUsers().then((response) =>
      {
        if (response.status == 200 || response.status == 201)
        {
          this.users = response.data.filter(u => u.username !== this.user.username);
          this.$store.commit("ADD_ALL_USERS", this.users);
        } else
        {
          this.users = this.$store.state.users;
        }
      });
    }
  },
};
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