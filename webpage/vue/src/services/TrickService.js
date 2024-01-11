import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getTricks() {
        return http.get("/tricks");
    },

    getTrick(trickId) {
        return http.get(`/tricks/${trickId}`);
    },

    createTrick(trick) {
        return http.post("/tricks", trick);
    },

    updateTrick(trickId, trick) {
        return http.put(`/tricks/${trickId}`, trick);
    },


}