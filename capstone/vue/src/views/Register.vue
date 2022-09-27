<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-4 font-weight-normal">Create New Account</h1>
      <h3 class="h1 mb-2 font-weight-normal">New Accounts Reviewed Before Approval</h3>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
        <v-container id="form-container">
        <label for="username" class="sr-only"></label>
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
          />
        </v-row>
        <label for="password" class="sr-only"></label>
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
        <v-row>
        <v-text-field
              type="password"
              id="confirmPassword"
              class="form-control"
              placeholder="Confirm Password"
              v-model="user.confirmPassword"
              required
              solo-inverted
          />
        </v-row>
        <div>
          <input type="radio" 
          name="user-radio" 
          class="button" 
          value="user"
          v-model="user.role"> User
          <input type="radio" 
          name="admin-radio" 
          class="button" 
          value="admin"
          v-model="user.role"
          @click="check"
          > Admin
        </div>
      </v-container>
      <router-link :to="{ name: 'login' }">
        <v-btn class="button"
            color=#8c090e
            elevation="2"
            outlined
           >Back to Login
        </v-btn></router-link>
      <v-btn class="button"
          color=#8c090e
             elevation="2"
             outlined
             type="submit">
        Submit - Create Account
      </v-btn>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data()
  {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        status: 'Needs Approval'
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    check(){
      console.log(this.user);
    },
    register()
    {
      if (this.user.password != this.user.confirmPassword)
      {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else
      {
        authService
            .register(this.user)
            .then((response) =>
            {
              if (response.status == 201)
              {
                this.$router.push({
                  path: '/login',
                  query: {registration: 'success'},
                });
              }
            })
            .catch((error) =>
            {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400)
              {
                this.registrationErrorMsg = 'Bad Request: Validation Errors';
              }
            });
      }
    },
    clearErrors()
    {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#form-container {
  width: 40%
}

.button {
  margin: 10px;
}

#radio-buttons{
  justify-content: center;
  padding: 5px;
}

</style>
