<template>
  <v-app>
    <v-container class="text-center">
      <br>
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
        >Home</v-btn>
      </router-link>
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

    <master-gantt></master-gantt>


    </v-container>
  </v-app>
</template>

<script>
  import ReportService from "../services/ReportService";
  import masterGantt from "../components/MasterGantt.vue";

  export default {
    name: "master-schedule",
    components: {
      masterGantt,
    },
    data() {
      return {
        masterSchedules: [],
        displaySchedules: [],
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
            start: new Date("2022-10-01").getTime(),
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
    methods: {},
    created() {
      ReportService.getMaster().then((results) => {
        this.masterSchedules = results.data.filter(
          (r) => r.start !== null
        );

        let idx = Math.max(...this.masterSchedules.map((e) => e.projectId));


        this.masterSchedules.forEach((e) => {

          const displayObj = {
            id: 0,
            // parentId: 0,
            start: 0,
            label: "",
            duration: 0,
            progress: 0,
            type: "",
            style: {
              base: {
                fill: "",
              },
            },
          };
          idx++;
          displayObj.start = new Date(e.start).getTime();
          displayObj.duration = e.durationDays * 24 * 60 * 60 * 1000;
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
          this.displaySchedules.push(displayObj);
        });
        this.$store.commit("SET_MASTER_SCHEDULE", this.displaySchedules);
      });
    },
  };
</script>

<style></style>
