<template>
  <div class="activity">
    <h2>Last Reading Activities</h2>
    <div
      class="activities"
      v-for="activity in activities"
      v-bind:key="activity.activityId"
    >
      <span class="bookTitle"> {{ findBook(activity.bookId) }}</span>
      <span class="readingMinutes">
        | {{ activity.readingMinutes }} minutes
      </span>
      <span class="readingFormat"> | {{ activity.readingFormat }}</span>
    </div>
  </div>
</template>

<script>
//import ActivityService from "@/services/ActivityService.js";
export default {
  name: "activity-info",
  data() {
    return {};
  },
  created() {
    // let activityPromise = ActivityService.getActivities(
    //   this.$store.state.currentFamily.familyId
    // );
    // console.log(this.$store.state.currentFamily.familyId);
    // activityPromise.then((response) => {
    //   this.activities = response.data;
    // });
  },
  computed: {
    activities() {
      return this.$store.state.currentActivity;
    },
    books() {
      return this.$store.state.currentBook;
    },
  },
  methods: {
    findBook(bookId) {
      let foundBook = this.$store.state.currentBooks.find(
        (book) => bookId == book.bookId
      );
      if (foundBook == undefined) {
        return "Book not registered to family or not found.";
      }
      return foundBook.title;
    },
  },
};
</script>

<style>
.activity{
border: 1px solid silver;
  padding:20px;
}
</style>