import axios from 'axios';
// const http = axios.create({
//     baseURL: "http://localhost:8080"
// });
export default {

    getMembers(username) {
        return axios.get(`/members?username=${username}`);
    },

    addMember(member, username) {
        return axios.post(`/members?username=${username}`, member);
    },





    // //get family object here:
    // getFamilyByUsername(username){
    //     //path goes here: need endpoint from Controller
    // }


}