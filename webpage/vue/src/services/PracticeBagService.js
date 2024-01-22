import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    // Get all practice bags that the user has created
    getPracticeBags() {
        return http.get("/practicebags");
    },


}