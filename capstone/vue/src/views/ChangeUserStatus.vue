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
              :key="user"
            >
              <td>{{user}}</td>
              <td>active</td>
              <td><input type="radio" name="Approve User" class="button" 
              value="approved" v-model="user.status"></td>
              <td><input type="radio" name="Deny User" class="button"
              value="denied" v-model="user.status"></td>
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
export default {
  data(){
    return {
      users: [
        1,
        2,
        3,
        4,
        5,
        6
      ]
    }
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