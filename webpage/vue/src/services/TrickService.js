import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getTricks() {
        return http.get("/tricks");
    },

    getKnownTricks() {
        return http.get("/tricks/known");
    },
    
    getUnknownTricks() {
        return http.get("/tricks/unknown");
    },

    getTrick(trickId) {
        return http.get(`/tricks/${trickId}`);
    },

    getPracticeBagTricks(practiceBagId) {
        return http.get(`/tricks/practicebags/${practiceBagId}`);
    },

    createTrick(trick) {
        return http.post("/tricks", trick);
    },

    updateTrick(trickId, trick) {
        return http.put(`/tricks/${trickId}`, trick);
    },


}