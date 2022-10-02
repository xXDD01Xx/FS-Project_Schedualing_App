<template>
  <v-app>
    <v-container class="text-center">
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn
        >
      </router-link>
      <v-simple-table>
        <thead>
          <th>Contract</th>
          <th>Project</th>
          <th>Phase</th>
          <th>Start</th>
          <th>End</th>
          <th>Duration</th>
        </thead>
        <tbody>
          <tr v-for="(sched,index) in masterSchedules" :key="index">
            <td>{{sched.contractName}}</td>
            <td>{{sched.projectName}}</td>
            <td>{{sched.phase}}</td>
            <td>{{sched.start}}</td>
            <td>{{sched.end}}</td>
            <td>{{sched.durationDays}}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </v-container>
  </v-app>
</template>

<script>
  import ReportService from "../services/ReportService";
  export default {
    name: "master-schedule",
    data() {
      return {
        masterSchedules: [],
      };
    },
    computed: {},
    methods: {},
    created() {
      ReportService.getMaster()
      .then((results) => {
        this.masterSchedules=results.data;
      });
    },
  };
</script>

<style></style>
