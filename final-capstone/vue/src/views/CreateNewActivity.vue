<template>
  <div class="new-activity">
    <h1>Add new Activity:</h1>

    <add-new-activity />
  </div>
</template>

<script>
import AddNewActivity from "../components/AddNewActivity.vue";
export default {
  components: { AddNewActivity },
};
</script>

<style scoped>
.new-activity {
  background-color:#302d59;
}
</style>