<template>
  <div class="home">
    <h1>Home</h1>
    <p>Welcome to your dashboard!</p>
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
<style></style>