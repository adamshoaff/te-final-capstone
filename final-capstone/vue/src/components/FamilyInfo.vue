<template>
  <div>
    <h1>My Family Members</h1>
    <div class="members" v-for="member in family.members" v-bind:key="member.id">
      {{ member.firstName }}
      {{member.lastName}}
    </div>
  </div>
</template>

<!--currently not working: the component is not displaying the members that are in the table-->

<script>
//import MemberService from "@/services/MemberService.js";

export default {
  name: "family-info",
  data() {
    return {
      family: {
        id:1,
        name:'blackfamily',
        members: [ {
          id:1,
          familyId:1,
          firstName: "tay",
          lastName: "black",
          userId: 1,   
        },
        {
          id:2,
          familyId:1,
          firstName: 'zhen',
          lastName: 'chen',
          userId: 2
        },
        {
          id: 3,
          familyId:1,
          firstName: 'annie',
          lastName: 'c',
          userId: 3
        
        }]
      },
    };
  }
  // created() {
  //   let familyPromise = MemberService.getMembers(
  //     this.$store.state.user.username
  //   );
  //   familyPromise.then((response) => {
  //     this.members = response.data;
  //     //need to push this data into the store (look at book)
  //     //Vuex
  //   });
  //},
};
</script>

<style>
</style>