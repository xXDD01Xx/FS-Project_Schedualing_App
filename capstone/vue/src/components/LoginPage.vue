<!--<template>-->
<!--    <v-app>-->
<!--        <v-main>-->
<!--         <v-container fluid fill-height>-->
<!--            <v-layout align-center justify-center>-->
<!--               <v-flex xs12 sm8 md4>-->
<!--                  <v-card class=“elevation-12”>-->
<!--                     <v-toolbar dark color=“primary”>-->
<!--                        <v-toolbar-title>Login form</v-toolbar-title>-->
<!--                     </v-toolbar>-->
<!--                     <v-card-text>-->
<!--                     <form ref=“form” @submit.prevent="login()">-->
<!--                            <v-text-field-->
<!--                              v-model="username"-->
<!--                              name=“username”-->
<!--                              label=“Username”-->
<!--                              type=“text”-->
<!--                              placeholder=“username”-->
<!--                              required-->
<!--                           ></v-text-field>-->
<!--                            <v-text-field-->
<!--                              v-model="password"-->
<!--                              name=“password”-->
<!--                              label=“Password”-->
<!--                              type=“password”-->
<!--                              placeholder=“password”-->
<!--                              required-->
<!--                           ></v-text-field>-->
<!--                           <v-btn type=“submit” class="mt-4" color=“primary” value=“log in”>Login</v-btn>-->
<!--                      </form>-->
<!--                     </v-card-text>-->
<!--                  </v-card>-->
<!--               </v-flex>-->
<!--            </v-layout>-->
<!--         </v-container>-->
<!--      </v-main>-->
<!--    </v-app>-->
<!--</template>-->

<!--<script>-->
<!--import authService from "@/services/AuthService";-->

<!--export default {-->

<!--  name: "LoginPage",-->
<!--  data() {-->
<!--    return {-->
<!--      user: {-->
<!--      username: "",-->
<!--      password: "",-->
<!--    },-->
<!--      invalidCredentials: false,-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    login() {-->
<!--      authService-->
<!--          .login(this.user)-->
<!--          .then((response) => {-->
<!--            if (response.status == 200) {-->
<!--              this.$store.commit("SET_AUTH_TOKEN", response.data.token);-->
<!--              this.$store.commit("SET_USER", response.data.user);-->
<!--              this.$router.push("/");-->
<!--            }-->
<!--          })-->
<!--          .catch((error) => {-->
<!--            const response = error.response;-->

<!--            if (response.status === 401) {-->
<!--              this.invalidCredentials = true;-->
<!--            }-->
<!--          });-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style>-->

<!--</style>-->