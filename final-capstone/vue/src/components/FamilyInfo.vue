<template>
  <div>
    <h1>My Family Members</h1>
    <div class="members" v-for="member in members" v-bind:key="member.id">
      {{ member.name }}
    </div>
   
    <add-new-member />
  </div>
</template>

<script>
import FamilyService from "@/services/FamilyService.js";
import AddNewMember from "@/components/AddNewMember.vue";
export default {
  components: { AddNewMember },
  name: "family-info",
  data() {
    return {
      members: [],
    };
  },
  created() {
    let familyPromise = FamilyService.getFamily();
    familyPromise.then((response) => {
      this.members = response.data;
    });
  },
};
</script>

<style>
</style>