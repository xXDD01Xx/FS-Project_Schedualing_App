<template>
  <v-app
    ><div><br /></div>
    <h2 class="text-center">Monthly Update</h2>
    <div><br /></div>
    <v-container class="text-center">
      <v-date-picker
        v-model="monthly.monthYear"
        :allowed-dates="allowedMonths"
        color="#8c090e"
        type="month"
      ></v-date-picker>
      <br />
      <v-radio-group row v-model="flipper" mandatory>
        <h3>Same as Last Month?</h3>
        <v-tab></v-tab>
        <v-radio label="Yes" value="true" @click="flipTheFlipper"></v-radio>
        <v-radio label="No" value="false" @click="flipTheFlipper"></v-radio>
      </v-radio-group>
      <br />
      <v-textarea
        label="Schedule Notes"
        type="text"
        v-model="monthly.scheduleNotes"
        required
      ></v-textarea>
      <v-text-field
        label="Percent Complete"
        type="text"
        v-model="monthly.pctComplete"
        required
      ></v-text-field>
      <v-text-field
        label="Project SPI"
        type="text"
        v-model="monthly.scheduleProdIdx"
        required
      ></v-text-field>
      <!-- <v-text-field
        label="Substantial Tasks"
        type="text"
        v-model="monthly.tasksSubstantial"
        required
      ></v-text-field
      ><v-text-field
        label="Construction Tasks"
        type="text"
        v-model="monthly.tasksConstruction"
        required
      ></v-text-field> -->
      <v-textarea
        label="Why did the Schedule Change?"
        type="text"
        v-model="monthly.whyOne"
        required
        v-if="!this.monthly.samePrevMonth"
      >
      </v-textarea>
      <v-textarea
        v-if="!this.monthly.samePrevMonth"
        label="Why 2?"
        type="text"
        v-model="monthly.whyTwo"
      >
      </v-textarea>
      <v-textarea
        v-if="!this.monthly.samePrevMonth"
        label="Why 3?"
        type="text"
        v-model="monthly.whyThree"
      >
      </v-textarea>
      <v-textarea
        v-if="!this.monthly.samePrevMonth"
        label="Why 4?"
        type="text"
        v-model="monthly.whyFour"
      >
      </v-textarea>
      <v-textarea
        v-if="!this.monthly.samePrevMonth"
        label="Why 5?"
        type="text"
        v-model="monthly.whyFive"
      >
      </v-textarea>
      <br />
      <v-btn
        class="text-decoration-none text-center"
        color="#8c090e"
        elevation="2"
        outlined
        @click="addMonthly()"
        >Confirm</v-btn
      >
    </v-container>

    <v-container class="text-center">
      <br /><br />
      <router-link
        class="text-decoration-none"
        :to="{ path: '/monthlyUpdate' }"
      >
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Back</v-btn
        >
      </router-link>
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined
          >Home</v-btn
        >
      </router-link>
    </v-container>
  </v-app>
</template>

<script>
  import MonthlyService from "../services/MonthlyService.js";

  export default {
    data() {
      return {
        flipper: "",
        monthly: {
          id: "",
          monthYear: new Date(new Date().setDate(1)).toISOString().substr(0, 7),
          projectId: this.$store.state.project.id,
          scheduleNotes: "",
          pctComplete: "",
          scheduleProdIdx: "",
          tasksSubstantial: "",
          tasksConstruction: "",
          samePrevMonth: true,
          whyOne: "",
          whyTwo: "",
          whyThree: "",
          whyFour: "",
          whyFive: "",
        },
        // picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        // .toISOString()
        // .substr(0, 10),
        id: "",
        displayMonthly: false,
        sameAsLastMonth: false,
        monthlyItems: [],
        hasMonthReports: [],
      };
    },
    created() {
      MonthlyService.getMonthlyList(this.$store.state.project.id)
        .then((response) => {
          if (response.status === 200 || response.status === 201) {
            this.hasMonthReports = response.data.map((e) =>
              e.monthYear.substr(0, 7)
            );
          }
        })
        .catch((error) => alert(error));
    },
    methods: {
      flipTheFlipper() {
        if (this.flipper == "true") {
          this.monthly.samePrevMonth = true;
        } else if (this.flipper == "false") {
          this.monthly.samePrevMonth = false;
        }
      },
      addMonthly() {
        const projId = this.monthly.projectId;
        const time = this.monthly.monthYear + "-01";
        this.monthly.monthYear = time;
        MonthlyService.addMonthly(time, projId)
          .then((response) => {
            if (response.status == 200 || response.status == 201) {
              this.monthly.id = response.data;
            }
            MonthlyService.updateMonthly(this.monthly)
              .then((response) => {
                if (response.status == 200 || response.status == 201) {
                  if (!this.monthly.samePrevMonth) {
                    this.$router.push({
                      name: "MonthlySchedule",
                      params: { id: this.monthly.id },
                    });
                  } else {
                    this.$router.push({ name: "home" });
                  }
                }
              })
              .catch((error) => {
                alert(error);
              });
          })
          .catch((error) => {
            alert(error);
          });
      },
      allowedMonths(val) {
        return !this.hasMonthReports.includes(val);
      },
    },
  };
</script>

<style></style>
