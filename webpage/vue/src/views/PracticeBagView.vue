<template>
    <v-sheet class="d-flex-column">
        <h1>Create a Practice Bag for your next Session</h1>

        <v-divider class="my-10"></v-divider>

        <h1 class="text-center mb-5">Previous Practice Sessions</h1>
        <v-sheet class="d-flex ga-10">
            <practice-bag-card v-for="practiceBag in practiceBags" :key="practiceBag.id" :practiceBag="practiceBag"></practice-bag-card>
        </v-sheet>

        <!-- Previously created practice bags -->
    </v-sheet>
</template>

<script>
import PracticeBagService from '../services/PracticeBagService';
import PracticeBagCard from '../components/PracticeBagCard.vue';

export default {

    components : { PracticeBagCard },

    data() {
        return {
        }
    },

    created() {
        
        PracticeBagService.getPracticeBags()
            .then((response) => {
                this.practiceBags = response.data;
                this.addPraticeBagsToStore(response.data);
            })
            .catch((error) => {
                this.errorHandler(error, 'fetching');
            })
    },

    computed : {
        practiceBags() {
            return this.$store.state.practiceBags;
        }
    },

    methods: {
        // Helper Methods
        errorHandler(error, verb) {
            console.log(`There was an error ${verb}, the error was: ${error}`);
        },


        // Store methods
        addPraticeBagsToStore(practiceBags) {
            this.$store.state.practiceBags = practiceBags;
        }
    }

}
</script>

<style scoped></style>

