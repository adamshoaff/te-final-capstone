import axios from 'axios';

export default {

    getActivities(username) {
        return axios.get(`/activities?username=${username}`);
    },
    addActivity(activity, username) {
        return axios.post(`/activities/add?username=${username}`, activity);
    }
}