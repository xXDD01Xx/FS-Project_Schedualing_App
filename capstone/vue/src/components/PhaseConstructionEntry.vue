<template>
  <v-app>
    <v-container class="text-center">
      <h3>Construction Phase</h3>
      <v-form ref="form" lazy-validation>
        <v-text-field
            type="date"
            label="Construction NTP"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Substantial Complete"
            required
        ></v-text-field>

        <v-text-field
            label="Substantial Complete Tasks"
            placeholder="Number of tasks until substantial complete"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Construction Complete"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Construction Contract End Date/Financial Acceptance"
            required
        ></v-text-field>
      </v-form>
    </v-container>
  </v-app>
</template>

<style>

</style>