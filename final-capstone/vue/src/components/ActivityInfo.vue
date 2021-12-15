<template>
  <div class="activity">
    <h2>Our Reading Activities</h2>
    <div
      class="activities"
      v-for="activity in activities"
      v-bind:key="activity.activityId"
      v-bind:class="{'completed': activity.completed, 'incomplete': !activity.completed}"
    >
      <span class="memberName">{{ findMember(activity.memberId) }} </span>

      <span class="bookTitle"> {{ findBook(activity.bookId) }}</span>
      <span class="readingMinutes">
        | {{ activity.readingMinutes }} minutes
      </span>
      <span class="readingFormat"> | {{ activity.readingFormat }}</span>
      <button class="markCompleted" @click="toggleCompleted(activity.activityId, activity.completed)">{{ activity.completed ? "Mark Incomplete" : "Mark Completed" }}</button>
    </div>
  </div>
</template>

<script>
import ActivityService from "@/services/ActivityService.js";
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
    findMember(memberId) {
      let foundMember = this.$store.state.currentFamily.members.find(
        (member) => memberId == member.memberId
      );
      if (foundMember == undefined) {
        return "Unknown member";
      }
      return foundMember.firstName;
    },
    toggleCompleted(activityId, completed){
      if(completed == false){
      ActivityService.markComplete(activityId);
      } else{
        ActivityService.markIncomplete(activityId);
      }
      this.$store.commit('TOGGLE_COMPLETED', activityId);
    }

  },
};
</script>

<style>
.completed{
  text-decoration: line-through;
}
.activity {
  border-radius: 15px;
  border: 1px solid silver;
  padding: 20px;
}
</style>