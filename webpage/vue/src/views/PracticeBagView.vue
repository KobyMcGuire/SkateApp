<template>
    <v-sheet class="d-flex-column">
        <h1>Generate a Practice Bag for your next Session</h1>

        <!-- TODO: FILL OUT RULES??? -->
        <v-form @submit.prevent ref="form">
            <v-select v-model="newBagMix" :items="mixChoices" :rules="practiceBagMixRules"
                label="Practice Bag Composition"></v-select>

            <v-slider min="1" :max="newBagMaxLength" v-model="sliderNumber" label="How many tricks would you like in this bag?" :step="1" thumb-label="always"></v-slider>

            <v-sheet>
                <trick-card v-for="trick in newPracticeBag" :key="trick.id" :trick="trick"></trick-card>
            </v-sheet>

            <v-sheet class="d-flex justify-center">
                <v-btn type="submit" class="bg-green-lighten-1" width="300" text="Create" @click="validateForm()"></v-btn>
            </v-sheet>
        </v-form>

        <v-divider class="my-10"></v-divider>

        <!-- Previously created practice bags -->
        <h1 class="text-center mb-5">Previous Practice Sessions</h1>
        <v-sheet class="d-flex ga-10">
            <practice-bag-card v-for="practiceBag in practiceBags" :key="practiceBag.id"
                :practiceBag="practiceBag"></practice-bag-card>
        </v-sheet>


    </v-sheet>
</template>

<script>
import PracticeBagService from '../services/PracticeBagService';
import PracticeBagCard from '../components/PracticeBagCard.vue';
import TrickCard from '../components/TrickCard.vue';
import TrickService from '../services/TrickService';

export default {

    components: { PracticeBagCard, TrickCard },

    data() {
        return {
            newBagLength: "",
            mixChoices: [
                "In-Bag Tricks",
                "In-Progress Tricks",
                "A mix of In-Bag and In-Progress Tricks"
            ],
            newBagMix: "",
            sliderNumber: 1,
            newPracticeBag: [],

            practiceBagMixRules: [
                (value) => {
                    if (value?.length > 0) return true;

                    return "Mix selection must not be left empty";
                },
            ],
        }
    },

    created() {

        // Grab all practice bags from DB
        PracticeBagService.getPracticeBags()
            .then((response) => {
                this.practiceBags = response.data;
                this.addPraticeBagsToStore(response.data);
            })
            .catch((error) => {
                this.errorHandler(error, 'fetching');
            })

        // Set maximum length of new Practice bag (Grab all tricks from DB and take length)
        TrickService.getKnownTricks()
            .then((response) => {
                this.$store.state.knownTricks = response.data;
            })

        TrickService.getUnknownTricks()
            .then((response) => {
                this.$store.state.unknownTricks = response.data;
            })

    },

    computed: {
        practiceBags() {
            return this.$store.state.practiceBags;
        },

        newBagMaxLength() {
            if (this.newBagMix === "In-Bag Tricks") {
                return this.$store.state.knownTricks.length;
            }
            else if (this.newBagMix === "In-Progress Tricks") {
                return this.$store.state.unknownTricks.length;
            }
            else {
                return this.$store.state.knownTricks.length + this.$store.state.unknownTricks.length;
            }
        }
    },

    methods: {
        // Helper Methods
        errorHandler(error, verb) {
            console.log(`There was an error ${verb}, the error was: ${error}`);
        },

        sliderChange(e) {
            this.sliderNumber = e;
            console.log(e);
            return this.sliderNumber
        },

        // Store methods
        addPraticeBagsToStore(practiceBags) {
            this.$store.state.practiceBags = practiceBags;
        },


        generatePracticeBag() {
            if (this.newBagMix === "In-Bag Tricks") {
                this.grabRandomInBagTricks();
            }

            else if (this.newBagMix === "In-Progress Tricks") {
                this.grabRandomInProgessTricks();
            }

            else if (this.newBagMix === "A mix of In-Bag and In-Progress Tricks") {
                this.grabRandomMixTricks();
            } 
        },

        grabRandomInBagTricks() {
            let storeArray = this.$store.state.knownTricks;
            let indicesSelected = [];

            for (let i = 0; i < this.sliderNumber; i++) {
                let indexSelected = Math.floor(Math.random() * storeArray.length);
                if (indicesSelected.includes(indexSelected)) {
                    i--;
                    continue;
                }
                indicesSelected.push(indexSelected);
                this.newPracticeBag.push(storeArray[indexSelected]);
            }
        },

        grabRandomInProgessTricks() {
            let storeArray = this.$store.state.unknownTricks;
            let indicesSelected = [];

            for (let i = 0; i < this.sliderNumber; i++) {
                let indexSelected = Math.floor(Math.random() * storeArray.length);
                if (indicesSelected.includes(indexSelected)) {
                    i--;
                    continue;
                }
                indicesSelected.push(indexSelected);
                this.newPracticeBag.push(storeArray[indexSelected]);
            }
        },

        grabRandomMixTricks() {

        },


        // Form Validation Method
        async validateForm() {
            const { valid } = await this.$refs.form.validate()

            if (valid) {
                this.generatePracticeBag();
            }
            else {
                alert("Form is invalid");
            }
        },
    }

}
</script>

<style scoped></style>

