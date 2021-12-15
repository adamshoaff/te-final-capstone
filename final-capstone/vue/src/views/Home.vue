<template>
  <div class="home">
    <div class="welcome">
      <h1>Welcome to your Dashboard!</h1>
    </div>
    <div class="family-card">
      <family-info />
      <router-link
        :to="{ name: 'create-new-member' }"
        class="create-new-member"
      >
        Add new Member
      </router-link>
    </div>
    <div class="book-card">
      <book-info />
      <router-link :to="{ name: 'create-new-book' }" class="create-new-book">
        Add new Book
      </router-link>
    </div>
    <div class="activity-card">
      <activity-info />
      <router-link
        :to="{ name: 'create-new-activity' }"
        class="create-new-activity"
      >
        Add new Activity
      </router-link>
    </div>

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
.welcome {
  grid-area: welcome-a;
  text-align: center;
  padding: 40px 0;
  height: 75px;
}
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
.my-family {
  grid-area: my-family-a;
  background-color: white;
  color: #302d59;
}
.family-book {
  grid-area: family-book-a;
  background-color: white;
  color: #302d59;
}
.activity {
  grid-area: activity-a;
  background-color: white;
  color: #302d59;
}
.home {
  display: grid;
  background-color: #fedb6d;
  grid-gap: 1px;
  grid-template-rows: 1fr 1fr 1fr;
  justify-items: center;
  grid-template-areas:
    "welcome-a welcome-a welcome-a"
    "my-family-a family-book-a activity-a";
}

div {
  padding: 30px 25px 25px 35px;
}

.text-center {
  height: 100vh;
  background-repeat: no-repeat; 
  background-image: url('../assets/family.jpg');
 background-size: 100%; 
}

</style>