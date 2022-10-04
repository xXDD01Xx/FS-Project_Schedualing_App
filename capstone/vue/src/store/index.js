import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));
const currentProject = JSON.parse(localStorage.getItem("project"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
    state: {
        token: currentToken || "",
        user: currentUser || {},
        users: [],
        project: currentProject || {},
        masterSchedule: [],
        schedLoaded: false,
    },
    mutations: {
        SET_AUTH_TOKEN(state, token) {
            state.token = token;
            localStorage.setItem("token", token);
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        },
        ADD_ALL_USERS(state, user) {
            state.users = user;
        },
        SET_USER(state, user) {
            state.user = user;
            localStorage.setItem("user", JSON.stringify(user));
        },
        LOGOUT(state) {
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            state.token = "";
            state.user = {};
            axios.defaults.headers.common = {};
        },
        SET_PROJECT(state, project) {
            state.project = project;
            localStorage.setItem("project", JSON.stringify(project));
        },

        EMPTY_PROJECT(state) {
            state.project = {};
        },

        SET_MASTER_SCHEDULE(state, sched) {
            state.masterSchedule = sched;
            state.schedLoaded = true;
        },
    },
    getters: {
        getSched(state) {
            return state.masterSchedule;
        },
    },
    actions: {
        SET_MASTER_SCHEDULE({ commit }) {
            commit('SET_MASTER_SCHEDULE');
        },
    },
  strict: true,
});
