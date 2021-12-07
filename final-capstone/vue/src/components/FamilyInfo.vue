<template>
  <div>
    <!--v-models go here-->
    <add-new-member />
  </div>
</template>

<script>
//import FamilyService from "@/services/FamilyService.js";
import AddNewMember from "@/components/AddNewMember.vue";
export default {
  components: { AddNewMember },
  name: "family-info",
  data() {
    return {
      //set up blank family object
    };
  },
  // methods:
  // retrieveFamily() {
  //     //use service here (look up)
  // }
};
</script>

<style>
</style>