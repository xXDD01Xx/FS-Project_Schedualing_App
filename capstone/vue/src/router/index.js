import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LandingPage from '../views/LandingPage.vue'
import ChangePassword from '../views/ChangePassword.vue'
import AddProject from '../views/AddProject.vue'
import BaselineSchedule from '../views/BaselineSchedule.vue'
import ChangeUserStatus from '../views/ChangeUserStatus.vue'
import MasterSchedule from '../views/MasterSchedule.vue'
import ModsChangeOrders from '../views/ModsChangeOrders.vue'
import MonthlyUpdate from '../views/MonthlyUpdate.vue'
import ReviewPendingUsers from '../views/ReviewPendingUsers.vue'
import StatusReports from '../views/StatusReports.vue'
import ViewProjects from "@/views/ViewProjects";

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'landingPage',
      component: LandingPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/changePassword',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/addProject',
      name: 'AddProject',
      component: AddProject,
      meta: {
        requiresAuth: true
      }
    },{
      path: '/baselineSchedule',
      name: 'BaselineSchedule',
      component: BaselineSchedule,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/changeUserStatus',
      name: 'ChangeUserStatus',
      component: ChangeUserStatus,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/master',
      name: 'MasterSchedule',
      component: MasterSchedule,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/mods-co',
      name: 'ModsChangeOrders',
      component: ModsChangeOrders,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/monthlyUpdate',
      name: 'MonthlyUpdate',
      component: MonthlyUpdate,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/reviewPendingUsers',
      name: 'ReviewPendingUsers',
      component: ReviewPendingUsers,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/statusReports',
      name: 'StatusReports',
      component: StatusReports,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/viewProjects',
      name: 'ViewProjects',
      component: ViewProjects,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
