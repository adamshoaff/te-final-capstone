<template>
  <div>
    <h1>My Family's Activities</h1>
  </div>
</template>

<script>
import ActivityService from "@/services/ActivityService.js";
export default {
  name: "activity-info",
  data() {
    return {
      activities: [],
    };
  },
  created() {
    let activityPromise = ActivityService.getActivities(
      this.$store.state.user.username
    );
    activityPromise.then((response) => {
      this.activities = response.data;
    });
  },
};
</script>

<style>
</style>