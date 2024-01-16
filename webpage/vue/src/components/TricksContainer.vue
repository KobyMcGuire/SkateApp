<template>
    <v-sheet rounded class="pa-3 elevation-10 bg-blue-grey-lighten-4" width="500">
        <h2 class="tricks-header">Your Bag</h2>
        <!-- Search Bar component here  -->
        <div v-if="isKnown === 'Yes'" class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToKnown($event)">
            <trick-card v-for="trick in $store.state.inBagTricks" :key="trick.id" :trick="trick" class="trick-card"
                :id="trick.id" draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>

        <div v-else class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToInProgress($event)">
            <trick-card v-for="trick in $store.state.inProgressTricks" :key="trick.id" :trick="trick" class="trick-card"
                :id="trick.id" draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>
    </v-sheet>
</template>


<script>
import TrickCard from './TrickCard.vue';
import TrickService from '../services/TrickService';

export default {
    components: { TrickCard },

    props: ['isKnown'],

    computed : {

    },

    data() {
        return {
            changeKnown: "",
        }
    },

    methods: {
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

    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    justify-content: center;
    gap: 10px;
}

.tricks-header {
    text-align: center;
}
</style>