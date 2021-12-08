<template>
  <div>
    <h1>My Family Members</h1>
    <div class="members" v-for="member in members" v-bind:key="member.id">
      {{ member.name }}
    </div>

   
  </div>
</template>

<!--currently not working: the component is not displaying the members that are in the table-->

<script>
import FamilyService from "@/services/FamilyService.js";

export default {
  
  name: "family-info",
  data() {
    return {
      members: [],
    };
  },
  created() {
    let familyPromise = FamilyService.getFamily(
      this.state.$store.currentUser.username
    );
    familyPromise.then((response) => {
      this.members = response.data;
      //need to push this data into the store (look at book)
      //Vuex
    });
  },
};
</script>

<style>
</style>