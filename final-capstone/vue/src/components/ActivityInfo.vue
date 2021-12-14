<template>
  <div>
    <h1>Last Reading Activities:</h1>
    <div
      class="activities"
      v-for="activity in activities"
      v-bind:key="activity.activityId"
    >
      {{ activity.readingMinutes }} minutes - {{ activity.readingFormat }}
    </div>
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
      this.$store.state.user.username,
      this.$store.state.currentFamily.familyId
    );
    console.log(this.$store.state.currentFamily.familyId);
    activityPromise.then((response) => {
      this.activities = response.data;
    });
  },
};
</script>

<style>
</style>