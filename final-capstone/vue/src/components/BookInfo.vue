<template>
  <div class="family-book">
    <h2>My Family's Books</h2>
    <div class="books" v-for="book in books" v-bind:key="book.bookId">
      <span class="bookTitle">{{ book.title }}</span>
      <span class="by"> by </span>
      <span class="bookAuthor">{{ book.author }}</span>
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService.js";
export default {
  name: "book-info",
  data() {
    return {
      books: [],
    };
  },
  created() {
    let bookPromise = BookService.getBooks(this.$store.state.user.username);
    bookPromise.then((response) => {
      this.books = response.data;
      this.$store.commit("SET_CURRENT_BOOKS", response.data);
    });
  },
};
</script>

<style>
.family-book {
  border-radius: 15px;
  border: 1px solid silver;
  padding:20px;
}
</style>