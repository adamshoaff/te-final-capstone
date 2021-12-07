<template>
  <form>
    <!--NO longer a form! Reformat this to look like Family Card mockup-->
    <label for="familyName">Family Name</label>
    <input type="text" name="familyName" id="familyName" />
    <!--new member form-->
    <router-link v-bind:to="{ name: 'new-member-form' }"
      >Add a Member</router-link
    >
    <button v-on:submit.prevent type="submit" class="submit-button">
      Submit
    </button>
  </form>
</template>

<script>
import NewMemberForm from "@/components/NewMemberForm.vue";
export default {
  name: "new-member-form",
  components: NewMemberForm,
};
</script>

<style>
</style>