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
          <tr v-for="(sched, index) in masterSchedules" :key="index">
            <td>{{ sched.contractName }}</td>
            <td>{{ sched.projectName }}</td>
            <td>{{ sched.phase }}</td>
            <td>{{ sched.start }}</td>
            <td>{{ sched.end }}</td>
            <td>{{ sched.durationDays }}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </v-container>
    <gantt-elastic :options="getOptions" :tasks="getTasks">
      <gantt-header slot="header"></gantt-header>
    </gantt-elastic>
  </v-app>
</template>

<script>
  import ReportService from "../services/ReportService";
  import GanttElastic from "gantt-elastic";
import GanttHeader from "gantt-elastic-header";

  export default {
    name: "master-schedule",
  components: {
    GanttElastic,
    GanttHeader,
  },
    data() {
      return {
        masterSchedules: [],
        options: {
          taskMapping: {
            progress: "percent",
          },
          maxRows: 100,
          maxHeight: 500,
          title: {
            label: "Master Schedule",
            html: false,
          },
          row: {
            height: 24,
          },
          calendar: {
            hour: {
              display: true,
            },
          },
          chart: {
            progress: {
              bar: false,
            },
            expander: {
              display: true,
            },
          },
          taskList: {
            expander: {
              straight: false,
            },
            columns: [
              {
                id: 1,
                label: "ID",
                value: "id",
                width: 40,
              },
              {
                id: 2,
                label: "Project",
                value: "label",
                width: 200,
                expander: true,
                html: true,
              },
              {
                id: 3,
                label: "Type",
                value: "type",
                width: 68,
              },
            ],
          },
          locale: {
            name: "en",
            Now: "Now",
            "X-Scale": "Zoom-X",
            "Y-Scale": "Zoom-Y",
            "Task list width": "Task list",
            "Before/After": "Expand",
            "Display task list": "Task list",
          },
        },
        tasks: [
          {
            id: 1,
            label: "Project 1",
            start: new Date(2022, 9, 1, 0, 0, 0).getTime(),
            duration: 60 * 24 * 60 * 60 * 1000,
            type: "milestone",
          },
          {
            id: 2,
            label: "Project 2",
            start: new Date(2022, 11, 1, 0, 0, 0).getTime(),
            duration: 60 * 24 * 60 * 60 * 1000,
            type: "milestone",
          },
        ],
      };
    },
    computed: {
      getOptions() {
        return this.options;
      },
      getTasks() {
        return this.tasks;
      },
    },
    methods: {},
    created() {
      ReportService.getMaster().then((results) => {
        this.masterSchedules = results.data;
      });
    },
  };
</script>

<style></style>
