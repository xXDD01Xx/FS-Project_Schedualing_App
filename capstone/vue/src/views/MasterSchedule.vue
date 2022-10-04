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
      <gantt-header slot="header" :options="getOptions"></gantt-header>
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
        displaySchedules: [
          // {
          //   id: Number,
          //   parentId: Number,
          //   start: Date,
          //   label: String,
          //   duration: Number,
          //   progress: Number,
          //   type: String,
          //   style: {
          //     base: {
          //       fill: String,
          //     },
          //   },
          // },
        ],
        options: {
          times: {
            timeZoom: 22,
          },
          title: {
            label: "Master Schedule",
            html: false,
          },
          maxRows: 100,
          maxHeight: 500,
          row: {
            height: 16,
          },
          calendar: {
            hour: {
              display: true,
            },
          },
          chart: {
            progress: {
              bar: true,
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
            id: 2,
            parentId: 29,
            start: new Date(2022, 9, 1, 0, 0, 0).getTime(),
            label: "Design",
            duration: 20 * 24 * 60 * 60 * 1000,
            progress: 0,
            type: "milestone",
            style: {
              base: { fill: "blue" },
            },
          },
          {
            id: 3,
            parentId: 29,
            start: new Date(2022, 9, 21, 0, 0, 0).getTime(),
            label: "Pre-Construction",
            duration: 20 * 24 * 60 * 60 * 1000,
            progress: 0,
            type: "milestone",
            style: {
              base: { fill: "orange" },
            },
          },
          {
            id: 4,
            parentId: 29,
            start: new Date(2022, 10, 11, 0, 0, 0).getTime(),
            label: "Construction",
            duration: 20 * 24 * 60 * 60 * 1000,
            progress: 0,
            type: "milestone",
            style: {
              base: { fill: "purple" },
            },
          },
          {
            id: 29,
            start: new Date(2022, 9, 1, 0, 0, 0).getTime(),
            label: "Project 1",
            duration: 60 * 24 * 60 * 60 * 1000,
            progress: 50,
            type: "project",
            style: {
              base: { fill: "red" },
            },
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
        this.masterSchedules = results.data.filter((r) => r.start !== null);
        console.log(this.masterSchedules);
        let idx = Math.max(...this.masterSchedules.map((e)=>e.projectId));
        this.masterSchedules.forEach((e) => {
          // if (e.phase === "Project") {
          //   this.displaySchedules.id = e.projectId;
          // }
          const displayObj = {
            id: Number,
            parentId: Number,
            start: Date,
            label: String,
            duration: Number,
            progress: Number,
            type: String,
            style: {
              base: {
                fill: String,
              },
            },
          };
          idx++;
          if (e.phase === "Project") {
            displayObj.id = e.projectId;
            displayObj.label = e.projectName;
            displayObj.progress = e.pctComplete;
            displayObj.type = "project";
            displayObj.style.base.fill = "red";
          } else {
            displayObj.id = idx;
            displayObj.parentId = e.projectId;
            displayObj.label = e.phase;
            displayObj.progress = 0;
            displayObj.type = "milestone";
            displayObj.style.base.fill =
              e.phase === "Design"
                ? "blue"
                : e.phase === "Pre-Construction"
                ? "orange"
                : e.phase === "Construction"
                ? "purple"
                : "";
          }
          displayObj.start = e.start;
          displayObj.duration = e.durationDays;
          this.displaySchedules.push(displayObj);
        });

        console.log(this.displaySchedules);
      });
    },
  };
</script>

<style></style>
