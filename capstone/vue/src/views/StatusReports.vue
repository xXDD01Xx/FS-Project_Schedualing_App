<template>
  <v-app>

    <v-simple-table>
      <thead>
        <th>Contract</th>
        <th>Project</th>
        <th>Phase</th>
        <th>Monthly Report</th>
        <th>Percent Complete</th>
        <th>SPI</th>
        <th>Project Notes</th>
      </thead>
      <tbody>
        <tr v-for="(stat, index) in statusReport" :key="index">
          <td>{{ stat.contractName }}</td>
          <td>{{ stat.projectName }}</td>
          <td>{{ stat.phase }}</td>
          <td>{{ stat.monthYear }}</td>
          <td>{{ stat.pctComplete }}</td>
          <td>{{ stat.schedProdIdx }}</td>
          <td>{{ stat.scheduleNotes }}</td>
        </tr>
      </tbody>
    </v-simple-table>
    <div class="text-center">
      <br><br>
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
        >Home</v-btn
        >
      </router-link>
    </div>
  </v-app>
</template>

<script>
  import ReportService from "../services/ReportService";
  export default {
    name: "status-reports",
    data() {
      return {
        statusReport: [],
      };
    },
    computed: {},
    methods: {},
    created() {
      ReportService.getStatusReport().then((results) => {
        this.statusReport = results.data;
      });
    },
  };
</script>

<style></style>
