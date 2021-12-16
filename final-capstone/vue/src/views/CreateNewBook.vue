<template>
  <div class="new-book">
    <h1>Add a new Book here:</h1>

    <add-new-book />
  </div>
</template>

<script>
import AddNewBook from "../components/AddNewBook.vue";
export default {
  components: { AddNewBook },
};
</script>

<style scoped>
.new-book {
  background-color:#f06a56;
}
</style>