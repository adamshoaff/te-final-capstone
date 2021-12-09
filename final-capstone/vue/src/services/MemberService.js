import axios from 'axios';
const http = axios.create({
    baseURL: "http://localhost:8080"
});
export default {

    getMembers(username) {
        return http.get(`/member?username=${username}`);
    },

    addMember(member) {
        return http.post(`/member/create`, member);
    }



    // //get family object here:
    // getFamilyByUsername(username){
    //     //path goes here: need endpoint from Controller
    // }


}