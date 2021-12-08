<template>
  <div>
    <a
      id="show-form-button"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >
      Create a new Member</a
    >
    <form v-on:submit.prevent="addMember" v-if="showForm === true">
      <div class="form-element">
        <label for="firstName">First Name</label>
        <input
          type="text"
          name="firstName"
          id="firstName"
          v-model="newMember.firstName"
        />
      </div>
      <div class="form-element">
        <label for="lastName">Last Name</label>
        <input
          type="text"
          name="lastName"
          id="lastName"
          v-model="newMember.lastName"
        />
      </div>
      <div class="form-element">
        <label for="memberType">Member Type:</label>
        <select id="memberType" v-model="newMember.memberType">
          <option value="parent">Parent</option>
          <option value="child">Child</option>
        </select>
      </div>
      <input type="submit" value="Save" />
      <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
    </form>
  </div>
</template>

<script>
//members aren't getting added to the table via the form
import familyService from "@/services/FamilyService.js";
export default {
  name: "add-new-member",
  data() {
    return {
      showForm: false,
      newMember: {
        firstName: "",
        lastName: "",
        memberType: "",
      },
    };
  },
  methods: {
    addNewMember() {
      this.$store.commit("ADD_NEW_MEMBER", this.newMember);
      this.resetForm();
    },

    addMember() {
      familyService
        .addMember(this.newMember)
        .then((response) => {
          if (response.status === 201) {
            this.addNewMember();
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },

    resetForm() {
      this.showForm = false;
      this.NewMember = {};
    },
  },
};
</script>

<style>
</style>