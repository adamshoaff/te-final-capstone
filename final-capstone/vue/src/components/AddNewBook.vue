<template>
  <form v-on:submit.prevent>
    <div class="book-form-element">
      <label for="title">Title</label>
      <input type="text" name="title" id="title" v-model="newBook.title" required />

      <label for="author">Author</label>
      <input type="text" name="author" id="author" v-model="newBook.author" required />

      <label for="isbn">Isbn</label>
      <input type="text" name="isbn" id="isbn" v-model="newBook.isbn" required />
      <div class="actions">
        <button type="submit" v-on:click="addBook()">Save</button>
        <button type="button" v-on:click="cancel()">Cancel</button>
      </div>
    </div>
  </form>
</template>

<script>
import BookService from "@/services/BookService.js";
export default {
  name: "add-new-book",
  data() {
    return {
      newBook: {
        title: "",
        author: "",
        isbn: "",
      },
    };
  },
  methods: {
    addBook() {
      BookService.addBook(this.newBook, this.$store.state.user.username)
        .then((response) => {
          if (response.status === 200) {
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

<style>
</style>