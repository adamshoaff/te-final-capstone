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
      <button class="button">
        Add new Member
        </button>
      </router-link>
    </div>
    <div class="book-card">
      <book-info />
      <router-link :to="{ name: 'create-new-book' }" class="create-new-book">
        <button class="button">
        Add new Book
        </button>
      </router-link>
    </div>
    <div class="activity-card">
      <activity-info />
      <router-link
        :to="{ name: 'create-new-activity' }"
        class="create-new-activity"
      >
      <button class="button">
        Add new Activity
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
import FamilyInfo from "@/components/FamilyInfo.vue";
import FamilyService from "@/services/FamilyService.js";
import BookInfo from "@/components/BookInfo.vue";
import BookService from "@/services/BookService.js";
import ActivityInfo from "@/components/ActivityInfo.vue";
import ActivityService from "@/services/ActivityService.js";

export default {
  components: { FamilyInfo, BookInfo, ActivityInfo },
  name: "home",
  created() {
    FamilyService.getByUsername(this.$store.state.user.username).then((r) => {
      const family = r.data;
      this.$store.commit("SET_CURRENT_FAMILY", family);

      const member = family.members.find(
        (m) => m.userid == this.$store.state.user.userid
      );
      this.$store.commit("SET_CURRENT_MEMBER", member);

      ActivityService.getActivities(
        this.$store.state.currentFamily.familyId
      ).then((r) => {
        this.$store.commit("SET_CURRENT_ACTIVITY", r.data);
      });
    });
    BookService.getBooks(this.$store.state.user.username).then((r) => {
      this.$store.commit("SET_CURRENT_BOOKS", r.data);
    });
  },
};
</script>
<style>
.button {
  border-radius: 50px;
  background-color:#31c3e6;
  margin-top:20px;
}
.welcome {
  grid-area: welcome-a;
  text-align: center;
  padding: 40px 0;
  height: 75px;
  border-radius: 15px;
  border: 1px solid rgb(216, 214, 214);
  width: 500px;
  background-color: white;
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
  padding:30px 25px 25px 30px;
  display: grid;
  background-color: #fedb6d;
  grid-gap: 1px;
 grid-template-columns:1fr 1fr 1fr ; 
  grid-template-rows: 2fr 5fr 1fr;
  /* justify-items: center; */
  grid-template-areas:
   "welcome-a welcome-a welcome-a" 
    "my-family-a family-book-a activity-a";
}
div.family-card {
  /* align-items: stretch; */
  /* background-color:rgba(255,255,255,1); */
  
  padding:30px 25px 25px 30px;
}
div.book-card {
  padding:30px 25px 25px 30px;
}
div.activity-card {
  padding:30px 25px 25px 30px;
}
</style>