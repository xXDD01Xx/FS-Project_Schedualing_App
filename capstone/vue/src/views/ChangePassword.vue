<template>
  <v-app>

    <v-container id="form-container" v-if="!showSuccess">
      <br>
      <h2 class="text-center">Change User Password</h2>
      <br><br>
      <v-row>
        <v-text-field
          type="password"
          id="password1"
          class="form-control"
          placeholder="New password"
          v-model="pass.password1"
          required
          autofocus
          solo-inverted
      /></v-row>
      <v-row>
        <v-text-field
          type="password"
          id="password2"
          class="form-control"
          placeholder="Re-enter new password"
          v-model="pass.password2"
          required
          solo-inverted
        />
      </v-row>
      <br>
      <div class="text-center">
      <v-btn
        class="button"
        color="#8c090e"
        elevation="2"
        outlined
        type="submit"
        @click="updatePass"
        >Update Password</v-btn
      >
      <br><br>
      <router-link class="text-decoration-none" :to="{ path: '/home' }">
        <v-btn class="button" color="#8c090e" elevation="2" outlined>Home</v-btn>
      </router-link>
      </div>
    </v-container>
    <v-alert type="success" v-if="showSuccess">Password changed!</v-alert>
  </v-app>
</template>

<script>
  import authService from "../services/AuthService";
  export default {
    name: "change-password",
    data() {
      return {
        pass: {
          password1: "",
          password2: "",
        },
        user: {
          username: "",
          password: "",
        },
        showSuccess: false,
      };
    },
    methods: {
      updatePass() {
        this.user.username = this.$store.state.user.username;
        this.user.password = this.pass.password2;
        return authService.changePassword(this.user).then((response) => {
          if (response.status === 200) {
            this.showSuccess = true;
          } else {
            console.log(response);
          }
        });
      },
    },
  };
</script>

<style></style>
