import axios from 'axios';
const http = axios.create({
    baseURL: "http://localhost:8080"
});
export default {

    getFamily(username) {
        return http.get(`/family?username=${username}`);
    },

    addMember(member) {
        return http.post(`/family/`, member);
    }



    // //get family object here:
    // getFamilyByUsername(username){
    //     //path goes here: need endpoint from Controller
    // }


}