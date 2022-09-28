<template>
  <v-app>
    <router-link :to="{ path: '/home' }">
      <v-btn class="button" color="#8c090e" elevation="2" outlined>Home</v-btn>
    </router-link>
    <v-container id="form-container">
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
    </v-container>

    <v-btn
      class="button"
      color="#8c090e"
      elevation="2"
      outlined
      type="submit"
      @click="updatePass"
      >Update Password</v-btn
    >
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
      };
    },
    methods: {
      updatePass() {
        this.user.username = this.$store.state.user;
        this.user.password = this.pass.password2;
        return authService.changePassword(this.user).then((response) => {
          if (response.status === 201) {
            console.log("Good stuff");
          }
        });
      },
    },
  };
</script>

<style></style>
