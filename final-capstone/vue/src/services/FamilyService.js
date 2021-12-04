import axios from 'axios';

export default {

    addFamily(family) {
        return axios.post('/family', family);
    }


}