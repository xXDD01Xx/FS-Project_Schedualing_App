<template>
  <v-app>
    <v-container class="text-center">
      <h3>Pre Construction</h3>
      <v-form ref="form" lazy-validation>
        <v-text-field
            type="date"
            label="Utility Relocation Start"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Utility Relocation End"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Right-of-Way Start"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="Right-of-Way End"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="IFB Start"
            required
        ></v-text-field>

        <v-text-field
            type="date"
            label="IFB End"
            required
        ></v-text-field>

      </v-form>
    </v-container>
  </v-app>
</template>


<script>

export default({
  setup() {
    
  },
})
</script>

<style scoped>

</style>
