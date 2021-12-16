<template>
  <div class="new-member">
     <h1> Create New Member</h1>
    
     <add-new-member/>
      </div>
</template>

<script>
import AddNewMember from "../components/AddNewMember.vue";
export default {
  name: "create-new-member",
components:{
    AddNewMember
}
}
</script>

<style scoped>
.new-member {
  background-color:#31c3e6;
} 
</style>