import axios from 'axios';

export default {

    getActivities(familyId) {
        return axios.get(`/activities?familyId=${familyId}`);
    },
    addActivity(activity, memberId) {
        return axios.post(`/activities/add?memberId=${memberId}`, activity);
    }
}