<template>
  <v-app>
    <v-container>
      <br>
      <h2 class="text-center">View Current Projects</h2>
      <br><br>
      <v-simple-table>
        <thead>
        <th>Project Name</th>
        <th>Date Received</th>
        <th>Last Modified</th>
        <th>Budget</th>
        <th>Construction Tasks</th>
        <th>Substantial Tasks</th>
        <th v-if="userOrAdmin">Edit Project</th>
        </thead>
        <tbody>
        <tr v-for="project in projects" :key="project.id">
          <td>
            {{ project.projectName }}
          </td>
          <td>
            {{ project.dateReceived }}
          </td>
          <td>
            {{ project.lastModified }}
          </td>
          <td>
            {{ project.budget }}
          </td>
          <td>
            {{ project.tasksConstruction }}
          </td>
          <td>
            {{ project.tasksSubstantial }}
          </td>
          <td v-if="userOrAdmin">
            <v-btn 
            elevation="2" class="text-decoration-none" 
            @click="editProject(project)">Edit</v-btn>
          </td>
        </tr>
        </tbody>
      </v-simple-table>
    </v-container>
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
import ProjectService from "@/services/ProjectService";

export default {
  name: "ViewProjects",
  data(){
    return {
      projects: [],
      showEdit: false,
    }
  },
  created(){
    ProjectService.listProjects().then((response) =>{
      if (response.status == 200 || response.status == 201)
      {
        this.projects = response.data;
      }
      //TODO filter?
    });
  },
  computed: {
    userOrAdmin()
    {
      return this.$store.state.user.authorities[0].name !== 'ROLE_VIEW';
    }
  },
  methods: {
    editProject(project)
    {
      this.$store.commit('SET_PROJECT', project);
      this.$router.push({name: 'EditProject', params: {id: project.id}})
    }
  }
}
</script>

<style scoped>
th {
  padding: 5px;
}


</style>