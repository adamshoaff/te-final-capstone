import axios from 'axios';
const http = axios.create({
    baseURL: "http://localhost:8080"
  });
export default {


    getFamily() {
        return http.get('/family');
    }

    // //get family object here:
    // getFamilyByUsername(username){
    //     //path goes here: need endpoint from Controller
    // }


}