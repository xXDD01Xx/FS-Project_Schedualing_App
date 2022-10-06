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
            <th id="status" class="text-center">Role</th>
            <th id="status" class="text-center">Status</th>
            <th class="text-center">Approve/Deny</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in filteredUsers" :key="user.username" class="trow">
            <td class="text-center">{{ user.username }}</td>
            <td class="text-center">{{ user.authorities[0].name.substr(5) }}</td>
            <td class="text-center">{{user.status}}</td>
            <v-radio-group v-model="user.status" row>
                <v-radio label="Approve User" value="Active" ></v-radio>
                <v-radio label="Deny User" value="Not Approved"></v-radio>
                <v-radio label="Inactive" value="Inactive"></v-radio>
                <v-radio label="Needs Approval" value="Needs Approval"></v-radio>
              </v-radio-group>
            <!-- <td>
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
            </td>        -->
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-container>
    <div><br></div>
    <div class="text-center">
      <v-btn @click="submitAll">Submit Changes</v-btn>
      <div v-if="submitError">{{ submitErrorMessage }} </div>
      <div v-if="submitSuccess">{{ submitSuccessMessage }}</div>


    <!-- <router-link class="text-decoration-none" :to="{path: '/changeUserStatus'}">
      <v-btn class="button"
             color=#8c090e
             elevation="2"
             outlined
      >Change User Status</v-btn>
    </router-link> -->
    </div>
    <div><br><br></div>
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
      submitError: false,
      submitSuccess: false,
      submitErrorMessage: "Error",
      submitSuccessMessage: "Success",
    }
  },
  methods: {
    submitAll()
    {
      let u = this.users;
      AuthService.saveChangeUserStatus(u).then((response) =>{
        if (response.status == 200){
          this.submitSuccess = true;

        } else if (response.status == 400){
          this.submitError = true;
        }
      });
    },
  },
  created() {
    AuthService.getAllUsers().then((response) =>{
      if (response.status == 200 || response.status == 201){
        this.users = response.data.filter(u => u.username !== this.user.username);
        this.filteredUsers = this.users.filter((user) =>{
          return user.status == "Needs Approval";
        })
      }
    });
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

/*td{*/
/*  align-content: flex-end;*/
/*}*/
</style>