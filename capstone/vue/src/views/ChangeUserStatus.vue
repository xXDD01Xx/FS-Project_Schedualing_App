<template>
  <v-app>
    <v-container id="main-table">
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr class="trow">
              <th class="text-left">
                Name
              </th>
              <th class="text-left">
                Status
              </th>
              <th class="text-left">
                Approve User
              </th>
              <th class="text-left">
                Deny User
              </th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="user in users"
              :key="user.username"
            >
              <td>{{user.username}}</td>
              <td>{{user.status}}</td>
              <td><input type="radio" name="Approve User" class="button" 
              value="Active" v-model="user.status"></td>
              <td><input type="radio" name="Deny User" class="button"
              value="Denied" v-model="user.status"></td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-container>
      <router-link :to="{path: '/home'}">
      <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Home</v-btn>
    </router-link>
  </v-app>
</template>

<script>
import AuthService from '../services/AuthService'
export default {
  data(){
    return {
      users: []
    }
  },
  methods: {
    submitAll(){
      let u = this.users;
      AuthService.saveChangeUserStatus(u).then(response => {
        if (response.status == 201){
          //
        }else if (response.status == 400){
          //
        }
      })
    }
  },
  created(){
    AuthService.getAllUsers().then(response => {
      console.log(response.status)
      if (response.status == 200 || response.status == 201){
        this.users = response.data;
        console.log(this.users)
      }
    })
  }

}
</script>

<style>
#main-table{
  max-width: 60%;
  padding: 10px;
}
.trow{
  border-right: 4px;
  border-top: 4px;
}

</style>