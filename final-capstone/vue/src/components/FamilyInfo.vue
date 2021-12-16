<template>
  <div class="my-family">
    <h2>My Family Members</h2>
    <div class="family" v-for="member in family.members" v-bind:key="member.id">
      <span class="firstName">{{ member.firstName }}</span>
    </div>
  </div>
</template>



<script>
import FamilyService from "@/services/FamilyService.js";

export default {
  name: "family-info",
  data() {
    return {
      family: {
        id: 1,
        name: "blackfamily",
        members: [
          {
            id: 1,
            familyId: 1,
            firstName: "tay",
            lastName: "black",
            userId: 1,
          },
          {
            id: 2,
            familyId: 1,
            firstName: "zhen",
            lastName: "chen",
            userId: 2,
          },
          {
            id: 3,
            familyId: 1,
            firstName: "annie",
            lastName: "c",
            userId: 3,
          },
        ],
      },
    };
  },
  created() {
    let familyPromise = FamilyService.getByUsername(
      this.$store.state.user.username
    );
    familyPromise.then((response) => {
      this.family = response.data;
      this.$store.commit("SET_CURRENT_FAMILY", response.data);
    });
  },
};
</script>

<style>
.my-family {
  background-color:white;
  /* display: flex; */
  border-radius: 15px;;
 border: 1px solid silver; 
  padding:20px;
}

</style>