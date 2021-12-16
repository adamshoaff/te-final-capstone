<template>

  <div id="login" class="text-center">
  <!-- <div>
    <img src="../assets/Book-Icon"/>
    </div> -->

    <!-- <div class="logo">
      <h1>Curl Up Club</h1>
      </div> -->
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Welcome Back!</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
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
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style>
/* @import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
.logo {

  font-family: 'Lobster', cursive;
  display: grid;
  /* justify-content: center; */
  /* align-content: left;
  height:100px;
  border-radius:10px;
  border:1px solid (rgb216, 214,214);
  width: 800px;
  background-color: rgba(255, 255,255,0);
  margin: auto 0;
  padding:20px 20px 20px 30px;

} */ 
* {
  font-family: Arial, Helvetica, sans-serif;
}

form > h1 {
  margin: 3px;
}

div > form {
  display: grid;
  align-content: space-between;
  height: 310px;
  border-radius: 15px;
  border: 1px solid rgb(216, 214, 214);
  width: 200px;
  background-color: white;
  padding: 40px 40px 40px 20px;
}

div {
  padding: 130px 25px 25px 35px;
}

.text-center {
  height: 100vh;
  background-repeat: no-repeat; 
  background-image: url('../assets/logo_w_fam.png');
 background-size: 100%; 
}

</style>