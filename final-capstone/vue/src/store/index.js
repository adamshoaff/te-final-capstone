import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    currentFamily: {},
    currentMember: {},
    members: {},
    currentBooks: [],
    currentActivity: {},
    token: currentToken || '',
    user: currentUser || {}
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    // ADD_NEW_MEMBER(state, member) {
    //   state.members.unshift(member);
    // }, 
    // ADD_MEMBERS(state, members) {
    //   state.members = members;
    // }
    SET_MEMBERS(state, data) {
      state.members = data;
    },
    SET_CURRENT_FAMILY(state, data) {
      state.currentFamily = data;
    },
    SET_CURRENT_BOOKS(state, data) {
      state.currentBooks = data;
    },
    SET_CURRENT_ACTIVITY(state, data) {
      state.currentActivity = data;
    },
    SET_CURRENT_MEMBER(state, data) {
      state.currentMember = data;
    }





  }
})
