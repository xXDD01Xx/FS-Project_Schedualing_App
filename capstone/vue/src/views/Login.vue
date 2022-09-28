<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid Username and Password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <v-container id="form-container">
        <v-row>
          <v-text-field
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
            solo-inverted
        /></v-row>
        <v-row>
          <v-text-field
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
            solo-inverted
          />
        </v-row>
      </v-container>
      <router-link class="text-decoration-none" :to="{ name: 'register' }">
        <v-btn class="button"
            color=#8c090e
            elevation="2"
            outlined
        >Create New Account</v-btn>
      </router-link>

      <v-btn class="button"
          color=#8c090e
          elevation="2"
          outlined
          type="submit"
          @click="check"
      >Sign in</v-btn>
    </form>
  </div>
</template>

<script>
  import authService from "../services/AuthService";

  export default {
    name: "login",
    components: {},
    data() {
      return {
        user: {
          username: "",
          password: "",
        },
        invalidCredentials: false,
      };
    },
    methods: {
      check(){
        console.log(this.user)
      },
      login() {
        authService
          .login(this.user)
          .then((response) => {
            if (response.status == 200) {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              this.$router.push({name: 'home'});
            }
          })
          .catch((error) => {
            const response = error.response;

            if (response.status === 401){
              this.invalidCredentials = true;
              alert(response.data.message)
            }
            if(response.status === 400) {
              this.invalidCredentials = true;
              alert(response.data.message)
            }
          });
      },
    },
  };
</script>

<style scoped>
  .for-control {
    border: 5px;
    color: blue;
  }

  #form-container {
    width: 40%
  }

  .button {
    margin: 10px;
  }

  /*.loginRegisterButtons {}*/
</style>
