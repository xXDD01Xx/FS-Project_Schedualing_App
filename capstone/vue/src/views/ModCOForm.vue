<template>
  <v-app>
  <v-container>
      <br />
      <h2 class="text-center">View Current Projects</h2>
      <br /><br />
      <v-simple-table>
        <thead>
          <th>Project Name</th>
          <th>Date Received</th>
          <th>Last Modified</th>
          <th>Budget</th>
          <th>Construction Tasks</th>
          <th>Substantial Tasks</th>
          <th>Log Mod/CO</th>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.id">
            <td>
              {{ project.projectName }}
            </td>
            <td>
              {{ new Date(project.dateReceived + " 05:00:00").toLocaleDateString(`en-US`, {dateStyle:"short"}) }}
            </td>
            <td>
              {{ new Date(project.lastModified).toLocaleString(`en-US`, opts) }}
            </td>
            <td>
              {{ project.budget.toLocaleString("en-US", {style:"currency", currency:"USD"}) }}
            </td>
            <td>
              {{ project.tasksConstruction }}
            </td>
            <td>
              {{ project.tasksSubstantial }}
            </td>
            <td>
              <v-btn
                elevation="2"
                class="text-decoration-none"
                @click="editProject(project)"
                >Log Mod/CO</v-btn
              >
            </td>
          </tr>
        </tbody>
      </v-simple-table>
    </v-container>
    <div class="text-center">
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn
        >
      </router-link>
    </div>


  <router-link class="text-decoration-none" :to="{path: '/home'}">
    <v-btn class="button"
           color=#8c090e
           elevation="2"
           outlined
    >Home</v-btn>
  </router-link>
  </v-app>
</template>

<script>
import ProjectService from '../services/ProjectService.js'

export default {
  name: "ModCOForm",
  data() {
      return {
        projects: [],
        showEdit: false,
        opts: { dateStyle: "short", timeStyle: "short" },
      };
    },
    created() {
      ProjectService.listProjects().then((response) => {
        if (response.status == 200 || response.status == 201) {
          this.projects = response.data;
        }
        //TODO filter?
      });
    },
    methods: {
      editProject(project) {
        this.$store.commit("SET_PROJECT", project);
        this.$router.push({ name: "ModCOFormByID", params: { id: project.id } });
      },
    },
}
</script>

<style scoped>

</style>