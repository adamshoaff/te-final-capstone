<template>
  <div>
    <h1>My Family's Books</h1>
    <div class="books" v-for="book in books" v-bind:key="book.id">
      {{ book.title }} - {{ book.author }}
      {{ book.isbn }}
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService.js";
export default {
  name: "book-info",
  data() {
    return {
      books: [
        {
          id: 1,
          title: "A Tale of Two Cities",
          author: "Person",
        },
        {
          id: 2,
          title: "Alice in Wonderland",
          author: "Walt Disney?",
        },
      ],
    };
  },
  create() {
    let bookPromise = BookService.getBooks(this.$store.state.user.username);
    bookPromise.then((response) => {
      this.books = response.data;
    });
  },
};
</script>

<style>
</style>