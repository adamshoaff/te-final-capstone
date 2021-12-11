<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <router-link :to="{ name: 'create-new-member' }">
      Add new Member
    </router-link>
    <family-info />
    <book-info />
    <router-link :to="{ name: 'create-new-book' }"> Add new Book </router-link>
  </div>
</template>

<script>
import FamilyInfo from "@/components/FamilyInfo.vue";
import FamilyService from "@/services/FamilyService.js";
import BookInfo from "@/components/BookInfo.vue";

export default {
  components: { FamilyInfo, BookInfo },
  name: "home",
  created() {
    FamilyService.getByUsername(this.$store.state.user.username).then((r) => {
      this.$store.commit("SET_CURRENT_MEMBER", r.data);
    });
  },
};
</script>
