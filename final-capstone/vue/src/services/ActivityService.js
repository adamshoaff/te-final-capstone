import axios from 'axios';

export default {

    getActivities(username) {
        return axios.get(`/activities?username=${username}`);
    },
    addActivity(activity, memberId) {
        return axios.post(`/activities/add?memberId=${memberId}`, activity);
    }
}