import axios from 'axios';


export default {

    getByUsername(username) {
        return axios.get(`/family?username=${username}`);
    },

    // getFamily(username) {
    //     return axios.get('/family', username);
    // }

}
