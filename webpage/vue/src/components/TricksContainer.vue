<template>
    <v-sheet rounded class="d-flex-column pa-3 elevation-10 bg-blue-grey-lighten-4" width="500">
        <!-- Header -->
        <h2 v-if="isKnown === 'Yes'" class="mb-6 text-center">Your Bag</h2>
        <h2 v-else class="mb-6 text-center">In Progress Tricks</h2>

        <!-- Search Bar -->
        <v-toolbar dense floating height="40" rounded class="pl-2 mb-3">
            <v-text-field hide-details prepend-icon="mdi-magnify" single-line v-model="searchTerm"></v-text-field>
        </v-toolbar>

        <!-- The inBag container and the inProgress container -->
        <div v-if="isKnown === 'Yes'" class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToKnown($event)">
            <trick-card v-for="trick in tricks" :key="trick.id" :trick="trick" class="trick-card" :id="trick.id"
                draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>
        <div v-else class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToInProgress($event)">
            <trick-card v-for="trick in tricks" :key="trick.id" :trick="trick" class="trick-card" :id="trick.id"
                draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>
    </v-sheet>
</template>


<script>
import TrickCard from './TrickCard.vue';
import TrickService from '../services/TrickService';

export default {
    components: { TrickCard },

    props: ['isKnown'],

    computed: {
        // Search Bar functionality
        tricks() {
            if (this.isKnown === 'Yes') {
                return this.$store.state.inBagTricks.filter((trick) => {
                    console.log(trick);
                    if (trick.name === null || trick.name.toLowerCase().includes(this.searchTerm.toLowerCase())) {
                        return true;
                    }
                });
            }
            else {
                return this.$store.state.inProgressTricks.filter((trick) => {
                    if (trick.name === null || trick.name.toLowerCase().includes(this.searchTerm.toLowerCase())) {
                        return true;
                    }
                });
            }
        }

    },

    data() {
        return {
            changeKnown: "",
            searchTerm: "",
        }
    },

    methods: {
        // Error handler
        errorHandler(error, verb) {
            console.log(`There was an error ${verb}, the error was: ${error}`);
        },
        // Updating the store arrays in order to have the drag and drop happen instantly ... was having issues with data taking too long coming back from back-end
        updateStoreAfterKnownChange() {
            // Changing to in-bag
            if (this.changeKnown === "Yes") {
                for (let i = 0; i < this.$store.state.inProgressTricks.length; i++) {
                    if (this.$store.state.inProgressTricks[i].id == this.updateTrickId) {
                        // Splice from current array and push to other array
                        let trickToPush = this.$store.state.inProgressTricks[i];
                        this.$store.state.inProgressTricks.splice(i, 1);
                        this.$store.state.inBagTricks.push(trickToPush);
                    }
                }
            }

            // Changing to in-progress
            else {
                for (let i = 0; i < this.$store.state.inBagTricks.length; i++) {
                    if (this.$store.state.inBagTricks[i].id == this.updateTrickId) {
                        let trickToPush = this.$store.state.inBagTricks[i];
                        this.$store.state.inBagTricks.splice(i, 1);
                        this.$store.state.inProgressTricks.push(trickToPush);
                    }
                }
            }
        },


        // API call to update the trick's 'known' attribute
        updateTrickKnown() {
            TrickService.getTrick(this.updateTrickId)
                .then((response) => {
                    TrickService.updateTrick(this.updateTrickId, { id: this.updateTrickId, name: response.data.name, flipOrShuv: response.data.flipOrShuv, stance: response.data.stance, known: this.changeKnown })
                        .then((response) => {
                            // Update store for dynamiac updating
                            this.updateStoreAfterKnownChange();

                            // Reset data variables
                            this.changeKnown = "";
                            this.updateTrickId = "";
                        })
                        .catch((error) => {
                            this.errorHandler(error, "updating known on a trick");
                        })
                })
                .catch((error) => {
                    this.errorHandler(error, "fetching a specific trick");
                })
        },


        // Drag and Drop methods
        handleDragStart(event) {
            console.log(event.target.id);
            event.dataTransfer.setData("trickId", event.target.id);
        },

        handleDropToKnown(event) {
            this.updateTrickId = event.dataTransfer.getData("trickId");
            this.changeKnown = "Yes";
            this.updateTrickKnown();

        },

        handleDropToInProgress(event) {
            this.updateTrickId = event.dataTransfer.getData("trickId");
            this.changeKnown = "No";
            this.updateTrickKnown();
        },
    }
}
</script>


<style scoped>
.trick-cards-container {
    padding: 5px;

    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 10px;
}

.tricks-header {
    text-align: center;
}
</style>