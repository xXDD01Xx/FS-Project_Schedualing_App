<template>
  <v-container>
    <gantt-elastic
      :options="options"
      :tasks="tasks"
    >
      <gantt-header slot="header" :options="options"></gantt-header>
    </gantt-elastic>
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
        <tr
          v-for="(task, index) in tasks"
          :key="index"
        >
          <td>{{ task.contractName }}</td>
          <td>{{ task.projectName }}</td>
          <td>{{ task.phase }}</td>
          <td>{{ task.start }}</td>
          <td>{{ task.end }}</td>
          <td>{{ task.durationDays }}</td>
        </tr>
      </tbody>
    </v-simple-table>
  </v-container>
</template>

<script>
  import GanttElastic from "gantt-elastic";
  import GanttHeader from "gantt-elastic-header";
  export default {
    name: "master-gantt",
    components: {
      GanttElastic,
      GanttHeader,
    },
    // props: ["options"],
    data() {
      return {
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
      };
    },
    computed:{
        tasks(){
            return this.$store.getters.getSched;
        }
    }
  };
</script>

<style></style>
