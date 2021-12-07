import axios from 'axios';

export default {

    addFamily(family) {
        return axios.post('/family', family);
    }

    // //get family object here:
    // getFamilyByUsername(username){
    //     //path goes here: need endpoint from Controller
    // }


}