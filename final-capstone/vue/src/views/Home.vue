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
        <button class="button">Add new Member</button>
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
  margin-top:10px;
  padding: 5px;
  border-radius: 50px;
  background-color:#fedb6d;
}
.welcome {
  grid-area: welcome-a;
}
.my-family {
  grid-area: my-family-a;
}
.family-book {
  grid-area: family-book-a;
}
.activity {
  grid-area: activity-a;
}
.home {
  display: grid;
  grid-gap: 1rem 10rem 1rem 10rem;
  grid-template-rows: 1fr 1fr 1fr;
  justify-items: center;
  grid-template-areas:
    "welcome-a welcome-a welcome-a"
    "my-family-a family-book-a activity-a";
}
</style>