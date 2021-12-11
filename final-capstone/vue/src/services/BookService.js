import axios from 'axios';


export default {
    getBooks(username) {
        return axios.get(`/books?username=${username}`);
    },

    addBook(book, username) {
        return axios.post(`/books/add?username=${username}`, book);
    }
}