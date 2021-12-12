<template>
  <form>
    <div class="activity-form-element">
      <div class="memberName">
        <label for="memberName">Member Name: </label>
        <input
          type="text"
          name="memberName"
          id="memberName"
          v-model="newActivity.memberName"
        />
      </div>
      <div class="bookTitle">
        <label for="bookTitle">Book Title: </label>
        <input
          type="text"
          name="bookTitle"
          id="bookTitle"
          v-model="newActivity.bookTitle"
        />
      </div>
      <div class="readingFormat">
        <label for="readingFormat"
          >Reading Format (Digital, Audiobook, etc.):
        </label>
        <input
          type="text"
          name="readingFormat"
          id="readingFormat"
          v-model="newActivity.readingFormat"
        />
      </div>
      <div class="readingMinutes">
        <label for="readingMinutes">Minutes Read: </label>
        <input
          type="text"
          name="readingMinutes"
          id="readingMinutes"
          v-model="newActivity.readingMinutes"
        />
      </div>
      <div class="readingNotes">
        <label for="readingNotes">Any Notes? </label>
        <textarea
          name="readingNotes"
          id="readingNotes"
          v-model="newActivity.readingNotes"
        ></textarea>
      </div>
      <div class="actions">
        <button type="submit" v-on:click="addActivity()">Save</button>
        <button type="button" v-on:click="cancel()">Cancel</button>
      </div>
    </div>
  </form>
</template>

<script>
import ActivityService from "@/services/ActivityService.js";
export default {
  name: "add-new-activity",
  data() {
    return {
      newActivity: {
        memberName: "",
        bookTitle: "",
        readingFormat: "",
        readingMinutes: "",
        readingNotes: "",
      },
    };
  },
  methods: {
    addActivity() {
      ActivityService.addActivity(
        this.newActivity,
        this.$store.state.user.username
      )
        .then((response) => {
          if (response.status === 200) {
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    cancel() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
</style>