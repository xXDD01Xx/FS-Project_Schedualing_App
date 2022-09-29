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
              <router-link class="text-decoration-none" :to="{path: '/baselineSchedule'}">
              <v-btn class="button"
              color=#8c090e
              elevation="2"
              outlined
              >Baseline Schedule</v-btn>
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
export default {
  name: "home",
  components: {},

  data(){
    return {
      user: this.$store.state.user
    }
  },
  methods: {}
};
</script>


<style scoped>
/*#home-container{*/
/*  display: flexbox;*/
/*  flex-direction: column;*/
/*  justify-content: center;*/
/*}*/
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