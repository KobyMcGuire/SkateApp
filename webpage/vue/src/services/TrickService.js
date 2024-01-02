import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getTricks() {
        return http.get("/tricks");
    },

    createTrick(trick) {
        return http.post("/tricks", trick);
    }


}