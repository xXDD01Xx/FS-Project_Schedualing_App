<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <br>
      <div v-show="signedInDiv">
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
      <br>
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
      </div>
      <div v-show="loggedInDisplay">
        <h2>Already Signed In! </h2>
        <br>
        <h3> Please Logout to Login</h3>
      </div>
      <br>
      <router-link class="text-decoration-none" :to="{ name: 'register' }">
        <v-btn v-show="registerBtn"
            class="button"
            color=#8c090e
            elevation="2"
            outlined
        >Create New Account</v-btn>
      </router-link>

      <v-btn v-show="loginBtn"
          class="button"
          color=#8c090e
          elevation="2"
          outlined
          type="submit"
          @click="check"
      >Sign in</v-btn>
    </form>
    <br>
    <router-link class="text-decoration-none" :to="{path: '/home'}">
      <v-btn v-show="homeBtn"
             class="button"
             color=#76a1c0
             elevation="2"
             outlined
      >Home</v-btn>
    </router-link>
  </div>
</template>

<script>
  import authService from "../services/AuthService";

  export default {
    name: "login",
    components: {},
    data() {
      return{
        homeBtn: false,
        loginBtn: false,
        registerBtn: false,
        signedInDiv: true,
        loggedInDisplay: false,

        user: {
          username: "",
          password: "",
        },
        invalidCredentials: false,
      };
    },
    mounted: function()
    {
      this.showButtons();
    },
    methods: {
      showButtons() {
        if(this.$store.state.token)
        {
          this.homeBtn = true;
          this.loggedInDisplay = true;
          this.signedInDiv = false;
        }
        else
        {
          this.loginBtn = true;
          this.registerBtn = true;
        }
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
