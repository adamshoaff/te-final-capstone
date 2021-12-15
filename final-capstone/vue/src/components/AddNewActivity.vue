<template>
  <form v-on:submit.prevent>
    <div class="activity-form-element">
      <div class="member-dropdown">
        <label for="member-dropdown">Member Name: </label>
        <select id="dropdown" v-model="newActivity.memberId">
          <option
            v-for="member in $store.state.currentFamily.members"
            v-bind:key="member.memberId"
            :value="member.memberId"
          >
            {{ member.firstName }}
          </option>
        </select>
      </div>
      <!--
      <div class="memberName">
        <label for="memberName">Member Name: </label>
        <input
          type="text"
          name="memberName"
          id="memberName"
          v-model="newActivity.memberName"
        />
      </div> -->
      <div>
        <label for="book-title">Book Title:</label>
        <select id="dropdown" v-model="newActivity.bookId">
          <option
            v-for="book in $store.state.currentBooks"
            v-bind:key="book.title"
            :value="book.bookId"
          >
            {{ book.title }}
          </option>
        </select>
      </div>
      <!-- <div class="bookTitle">
        <label for="bookTitle">Book Title: </label>
        <input
          type="text"
          name="bookTitle"
          id="bookTitle"
          v-model="newActivity.bookTitle"
        /> 
      </div> -->
      <div class="readingFormat">
        <label for="readingFormat">Which format?</label>

        <select
          name="readingFormat"
          id="readingFormat"
          v-model="newActivity.readingFormat"
        >
          <option value="Digital">Digital</option>
          <option value="Paper">Paper</option>
          <option value="Audiobook">Audiobook</option>
          <option value="ReadAloud-Listener">Read Aloud (Listener)</option>
          <option value="ReadAloud-Reader">Read Aloud (Reader)</option>
          <option value="Other">Other</option>
        </select>
      </div>

      <!-- <div class="readingFormat">
        <label for="readingFormat"
          >Reading Format (Digital, Audiobook, etc.):
        </label>
        <input
          type="text"
          name="readingFormat"
          id="readingFormat"
          v-model="newActivity.readingFormat"
        />
      </div> -->

      <div class="readingMinutes">
        <label for="readingMinutes">How many minutes did you read?</label>
        <select
          name="readingMinutes"
          id="readingMinutes"
          v-model="newActivity.readingMinutes"
        >
          <option value="15">15</option>
          <option value="30">30</option>
          <option value="45">45</option>
          <option value="60">60</option>
          <option value="75">75</option>
          <option value="90">90</option>
          <option value="120">120</option>
        </select>
      </div>

      <!-- <div class="readingMinutes">
        <label for="readingMinutes">Minutes Read: </label>
        <input
          type="text"
          name="readingMinutes"
          id="readingMinutes"
          v-model="newActivity.readingMinutes"
        />
      </div> -->

    
      <div class="readingNotes">
        <label for="readingNotes">Any Notes? </label>
        <textarea
          name="readingNotes"
          id="readingNotes"
          v-model="newActivity.readerNotes"
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
        memberId: Number,
        bookId: Number,
        readingFormat: "",
        readingMinutes: "",
        readerNotes: "",
        familyId: this.$store.state.currentFamily.familyId,
      },
    };
  },
  methods: {
    addActivity() {

      ActivityService.addActivity(this.newActivity, this.newActivity.memberId)
        .then((response) => {
          if (response.status === 200 || response.status === 201) {
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
    //create a method that takes in the member name and returns the id of the member
  },
};
</script>

<style>
</style>