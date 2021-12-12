<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <family-info />
    <router-link :to="{ name: 'create-new-member' }">
      Add new Member
    </router-link>
    <book-info />
    <router-link :to="{ name: 'create-new-book' }"> Add new Book </router-link>
    <activity-info />
    <router-link :to="{ name: 'create-new-activity' }">
      Add new Activity
    </router-link>
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
      this.$store.commit("SET_CURRENT_MEMBER", r.data);
    });
    BookService.getBooks(this.$store.state.user.username).then((r) => {
      this.$store.commit("SET_CURRENT_BOOK", r.data);
    });
    ActivityService.getActivities(this.$store.state.user.username).then((r) => {
      this.$store.commit("SET_CURRENT_ACTIVITY", r.data);
    });
  },
};
</script>
