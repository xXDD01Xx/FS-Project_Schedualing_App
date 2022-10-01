<template>
  <v-app>
    <div class="home">
      <v-container id="home-container" class="text-center" >
      <h1>Welcome, {{user.username}}</h1>
          <div>
            <h2>Schedule</h2>
            <v-container id="master-container" class="my-2">
              <router-link class="text-decoration-none" :to="{path: '/master'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Master Schedule</v-btn>
              </router-link>
            </v-container>
          <v-container id="report-container" class="my-2">
            <router-link class="text-decoration-none" :to="{path: '/statusReports'}">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Status Reports</v-btn>
            </router-link>
            <router-link class="text-decoration-none" :to="{path: '/viewProjects'}">
              <v-btn class="button"
                     color=#8c090e
                     elevation="2"
                     outlined
              >View Projects</v-btn>
            </router-link>
            <router-link class="text-decoration-none" :to="{path: '/listBaseline'}">
              <v-btn class="button"
                     color=#8c090e
                     elevation="2"
                     outlined
              >List Baseline Items</v-btn>
            </router-link>
          </v-container>
          </div>
          <div v-if="user.authorities[0].name !== 'ROLE_VIEW'">
            <h2>Schedule Management</h2>
            <v-container id="schedule-container" class="my-2">
              <router-link class="text-decoration-none" :to="{path: '/baseline'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Edit Baseline Schedule</v-btn>
              </router-link>
              <router-link class="text-decoration-none" :to="{path: '/monthlyUpdate'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Monthly Update</v-btn>
              </router-link>
           </v-container>
           <v-container id="pending-mod-co-container" class="my-2">
             <router-link class="text-decoration-none" :to="{path: '/mods-co'}">
             <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Pending Modifications/Change Orders</v-btn>
             </router-link>
          </v-container>
          <v-container id="add-project-container" class="my-2">
            <router-link class="text-decoration-none" :to="{path: '/addProject'}">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Add Project</v-btn>
            </router-link>
          </v-container>
          </div>
          <div v-if="user.authorities[0].name === 'ROLE_ADMIN'">
            <h2>Admin Management</h2>
            <v-container id="admin-container" class="my-2">
              <router-link class="text-decoration-none" :to="{path: '/reviewPendingUsers'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Review Pending Users</v-btn>
            <PendingSnackbar v-if="pending"/>
              </router-link>
              <router-link class="text-decoration-none" :to="{path: '/changeUserStatus'}">
            <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Change User Status</v-btn>
              </router-link>
            </v-container>
          </div>
          <div>
            <h2>Edit Account</h2>
            <v-container id="account-container" class="my-2">
              <router-link class="text-decoration-none" :to="{path: '/changePassword'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
            >Change Password</v-btn>
              </router-link>
            </v-container>
          </div>
        
      </v-container>
    </div>
  </v-app>
</template>

<script>
import PendingSnackbar from '../components/PendingSnackbar.vue'
import AuthService from '../services/AuthService.js'

export default {
  name: "home",
  components: {PendingSnackbar},

  data(){
    return {
      user: this.$store.state.user,
      filteredUsers: [],
      pending: false,
    }
  },
  methods: {},
  created(){
    AuthService.getAllUsers().then((response) =>{
        if (response.status == 200 || response.status == 201){
          this.filteredUsers = response.data.filter((user) =>{
            return user.status == "Needs Approval";
          })
        }
        if (this.filteredUsers.length > 0){
          this.pending = true;
          //alert('Pending Users Need Review')
      } 
      });
      console.log(this.filteredUsers)
      if (this.filteredUsers.length > 0){
        //alert('Pending Users Need Review')
      }
  }
};
</script>


<style scoped>
.button {
    margin: 10px;
}
h1 {
  padding-top: 20px;
  padding-bottom: 10px;
}
h2 {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>