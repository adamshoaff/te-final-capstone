<template>
  <div>
    <!--v-models go here-->
    <router-link v-bind:to="{ name: 'add-new-member' }"
      ><button>Add New Member</button></router-link
    >
  </div>
</template>

<script>
import FamilyService from '@/services/FamilyService.js';
import AddNewMember from "@/components/AddNewMember.vue";
export default {
  components: { AddNewMember },
  name: "add-new-member",
  data() {
    return {
      //set up blank family object
    }
  },
  methods:
  retrieveFamily() {
      //use service here (look up)
  }
};
</script>

<style>
</style>