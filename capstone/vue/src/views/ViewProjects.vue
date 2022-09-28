<template>
  <v-app>
    <v-container>
      <v-simple-table>
        <thead>
        <th>Project Name</th>
        <th>Date Received</th>
        <th>Contract Id</th>
        <th>Budget</th>
        </thead>
        <tbody>
        <tr v-for="project in projects" :key="project">
          <td>
            {{ project }}
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
// import ProjectService from "@/services/ProjectService";

export default {
  name: "ViewProjects",
  data()
  {
    return {
      projects: [1, 2, 3, 4, 5],
    }
  },
  // created() {
  //   ProjectService.listProjects().then((response) =>
  //   {
  //     if(response.status == 200 || response.status == 201)
  //     {
  //       this.projects = response.data;
  //     }
  //     //TODO filter?
  //   });
  // }
}
</script>

<style scoped>

</style>