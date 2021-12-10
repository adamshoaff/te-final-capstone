<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <router-link :to="{ name: 'create-new-member' }">
      Add new Member
    </router-link>
    <family-info />
  </div>
</template>

<script>
import FamilyInfo from "@/components/FamilyInfo.vue";

export default {
  components: { FamilyInfo },
  name: "home",
};
</script>
