<template>
  <v-app>
    <v-container class="text-center">
      <h3>Design Phase</h3>
      <v-form ref="form" lazy-validation>
      <v-text-field
          type="date"
          label="Design NTP"
          required
      ></v-text-field>

      <v-text-field
          type="date"
          label="30% Completion"
          required
      ></v-text-field>

      <v-text-field
        type="text"
        label="30% Tasks"
        required
        placeholder="Number of tasks until 30% complete...">
      </v-text-field>

      <v-text-field
          type="date"
          label="60% Completion"
          required
      ></v-text-field>

      <v-text-field
        label="60% Tasks"
        required
        placeholder="Number of tasks from 30% - 60% complete...">
      </v-text-field>

      <v-text-field
          type="date"
          label="90% Completion"
          required
      ></v-text-field>

      <v-text-field
        label="90% Tasks"
        required
        placeholder="Number of tasks from 60% - 90% complete...">
      </v-text-field>

      <v-text-field
          type="date"
          label="100% Completion"
          required
      ></v-text-field>

      <v-text-field
        label="100% Tasks"
        required
        placeholder="Number of tasks from 90% - 100%">
      </v-text-field>

      <v-text-field
          type="date"
          label="Design Contract End Date"
          required
      ></v-text-field>
    </v-form>
    </v-container>
  </v-app>
</template>