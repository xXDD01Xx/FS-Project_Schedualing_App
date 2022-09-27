<template>
  <v-app>
    <div class="home">
      <h1>Welcome, {{user.username}}</h1>
      <v-container id="home-container">
          <div>
            <h3>Schedule Management</h3>
            <v-container id="master-container">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Master Schedule</v-btn>
            </v-container>
            <v-container id="schedule-container">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Baseline Schedule</v-btn>
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Monthly Update</v-btn>
           </v-container>
            <v-container id="pending-mod-co-container">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Pending Modifications/Change Orders</v-btn>
          </v-container>
          <v-container id="status-report-container">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Status Reports</v-btn>
          </v-container>
          <v-container id="add-project-container">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Add Project</v-btn>
          </v-container>
          </div>
          <div v-if="user.authorities[0].name == 'ROLE_ADMIN'">
            <h3>Admin Management</h3>
            <v-container id="admin-container">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Review Pending Users</v-btn>
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Change User Status</v-btn>
            </v-container>
          </div>
          <div>
            <h3>Edit Account</h3>
            <v-container id="account-container">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Change Password</v-btn>
            </v-container>
          </div>
        
      </v-container>
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
  },
  methods: {}
};
</script>


<style scoped>
#home-container{
  display: flexbox;
  flex-direction: column;
  justify-content: center;
}
.button {
    margin: 10px;
}
</style>