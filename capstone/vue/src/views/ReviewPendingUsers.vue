<template>
  <v-app>
    <v-container id="main-table">
      <br>
      <h2 class="text-center">Review Pending Users</h2>
      <br><br>
      <v-simple-table class="table">
        <template v-slot:default>
          <thead>
          <tr class="trow">
            <th class="text-center">Username</th>
            <th id="status" class="text-center">Status</th>
            <th class="text-center">Approve</th>
            <th class="text-center">Deny</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in filteredUsers" :key="user.username" class="trow">
            <td class="text-center">{{ user.username }}</td>
            <td class="text-center">{{user.status}}</td>
            <td>
              <input
                  type="radio"
                  name="Approve User"
                  class="text-center"
                  value="Active"
                  v-model="user.status"
              />
            </td>
            <td>
              <input
                  type="radio"
                  name="Deny User"
                  class="text-center"
                  value="Not Approved"
                  v-model="user.status"
              />
            </td>       
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
import AuthService from "@/services/AuthService";

export default {
  data(){
    return {
      users: [],
      user: this.$store.state.user,
      filteredUsers: [],
    }
  },
  created() {
    AuthService.getAllUsers().then((response) =>{
      if (response.status == 200 || response.status == 201){
        this.users = response.data.filter(u => u.username !== this.user.username);
        this.filteredUsers = this.users.filter((user) =>{
          return user.status == "Needs Approval";
        })
      }
      console.log(this.filteredUsers) 
    });
    console.log(this.filteredUsers)
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

td{
  align-content: right;
}
</style>