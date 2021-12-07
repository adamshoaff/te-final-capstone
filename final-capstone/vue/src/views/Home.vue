<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <family-info />
  </div>
</template>

<script>
//redo all of this
import FamilyInfo from "@/components/FamilyInfo.vue";

export default {
  components: { FamilyInfo },
  name: "home",
};
</script>
