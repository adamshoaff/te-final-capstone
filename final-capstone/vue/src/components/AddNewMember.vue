<template>
  <form v-on:submit.prevent>
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
    <div class="actions">
      <button type="submit" v-on:click="addMember()">Save</button>
      <button type="button" v-on:click="cancel()">Cancel</button>
    </div>
  </form>
</template>

<script>
//members aren't getting added to the table via the form
import MemberService from "@/services/MemberService.js";
export default {
  name: "add-new-member",
  data() {
    return {
      newMember: {
        firstName: "",
        lastName: "",
        memberType: "",
      },
    };
  },
  methods: {
    // addNewMember() {
    //   this.$store.commit("ADD_NEW_MEMBER", this.newMember);
    //   this.resetForm();
    // },

    addMember() {
      MemberService.addMember(this.newMember, this.$store.state.user.username)
        .then((response) => {
          if (response.status === 200) {
            // this.addNewMember();
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

<style scoped>
div {
padding:0;
}
</style>