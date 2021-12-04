<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <new-family-form /> 
    
  </div>
</template>

<script>
import NewFamilyForm from "@/components/NewFamilyForm.vue";

export default {
  components: { NewFamilyForm },
  name: "home",
};
</script>
