<template>
  <v-sheet class="d-flex flex-column ga-10">
    <v-sheet class="d-flex ga-10">
      <v-sheet rounded class="pa-3 elevation-10 bg-blue-grey-lighten-4" width="500">
        <h2 class="tricks-header">Your Bag</h2>
        <div class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToKnown($event)">
          <trick-card v-for="trick in $store.state.inBagTricks" :key="trick.id" :trick="trick" class="trick-card"
            :id="trick.id" draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>
      </v-sheet>

      <v-sheet rounded class="pa-3 elevation-10 bg-blue-grey-lighten-4" width="500">
        <h2 class="tricks-header">In-Progress Tricks</h2>
        <div class="trick-cards-container" @dragover.prevent v-on:drop="handleDropToInProgress($event)">
          <trick-card v-for="trick in $store.state.inProgressTricks" :key="trick.id" :trick="trick" class="trick-card"
            :id="trick.id" draggable="true" v-on:dragstart="handleDragStart($event)"></trick-card>
        </div>
      </v-sheet>
    </v-sheet>

    <v-expand-transition>
      <v-form v-if="showNewTrickForm" @submit.prevent ref="form">
        <v-text-field v-model="newTrick.name" label="Trick Name" :rules="trickNameRules"></v-text-field>

        <!-- Add rules here -->
        <v-select v-model="newTrick.stance" :items="stances" :rules="stanceNameRules" label="Stance"></v-select>

        <p>Is this trick in your bag?</p>
        <v-radio-group v-model="inBagRadio" :rules="inBagRules" inline>
          <v-radio label="Yes" value="Yes"></v-radio>
          <v-radio label="No" value="No"></v-radio>
        </v-radio-group>

        <p>Is this trick composed of a Shuv, Flip, or both?</p>
        <v-radio-group v-model="flipShuvRadio" :rules="flipShuvRadioRules" inline>
          <v-radio label="Shuv" value="Shuv"></v-radio>
          <v-radio label="Flip" value="Flip"></v-radio>
          <v-radio label="Combination" value="Flip-Shuv"></v-radio>
        </v-radio-group>

        <div class="d-flex justify-center ga-3">
          <v-btn type="submit" class="bg-green-lighten-1" width="300" text="Submit" @click="

            (expandNewTrickForm = !expandNewTrickForm), validateForm()

            "></v-btn>

          <v-btn class="bg-red-lighten-1" width="300" text="Cancel"
            @click="(showNewTrickForm = !showNewTrickForm), resetForm()"></v-btn>
        </div>
      </v-form>
    </v-expand-transition>

    <v-btn v-on:click="handleCreateTrick" prepend-icon="mdi-plus" class="bg-blue-grey-lighten-4 mx-auto" width="300">
      Create a New Trick
    </v-btn>
  </v-sheet>
</template>

<script>
import TrickService from "../services/TrickService";
import TrickCard from "../components/TrickCard.vue";

export default {
  components: { TrickCard },

  data() {
    return {
      tricks: [],
      stances: [
        "Normal",
        "Switch",
        "Fakie",
        "Nollie"
      ],
      expandNewTrickForm: false,
      newTrick: {},
      updateTrickId : "",
      fetchedTrick: {},
      changeKnown : "",
      newTrickName: "",
      trickNameRules: [
        (value) => {
          if (value?.length > 0) return true;

          return "Trick name must not be left empty";
        },
      ],
      newTrickStance: "",
      stanceNameRules: [
        (value) => {
          if (value?.length > 0) return true;

          return "Stance must not be left empty";
        },
      ],
      inBagRadio: "",
      inBagRules: [
        (value) => {
          if (value?.length > 0) return true;

          return "You must select an option";
        },
      ],
      flipShuvRadio: "",
      flipShuvRadioRules: [
        (value) => {
          if (value?.length > 0) return true;

          return "You must select an option";
        },
      ],
      showNewTrickForm: false,
    };
  },

  created() {
    TrickService.getTricks()
      .then((response) => {
        this.filterTricks(response.data);
        this.tricks = response.data;
      })
      .catch((error) => {
        this.errorHandler(error, "fetching tricks");
      });
  },

  methods: {
    // HELPER METHODS
    errorHandler(error, verb) {
      console.log(`There was an error ${verb}, the error was: ${error}`);
    },

    filterTricks(data) {
      // Reset store tricks
      this.$store.state.inBagTricks = [];
      this.$store.state.inProgressTricks = [];

      // Filter tricks
      data.forEach((trick) => {
        if (trick.known === "Yes") {
          this.$store.state.inBagTricks.push(trick);
        } else {
          this.$store.state.inProgressTricks.push(trick);
        } 
      });
    },

    updateStoreAfterKnownChange() {
      // Changing to in-bag
      if (this.changeKnown === "Yes") {
        for (let i = 0; i < this.$store.state.inProgressTricks.length; i++) {
          if (this.$store.state.inProgressTricks[i].id == this.updateTrickId) {
            // Splice from current array and push to other array
            let trickToPush = this.$store.state.inProgressTricks[i];
            this.$store.state.inProgressTricks.splice(i , 1);
            this.$store.state.inBagTricks.push(trickToPush);
          }
        }
      }

      // Changing to in-progress
      else {
        for (let i = 0; i < this.$store.state.inBagTricks.length; i++) {
          if (this.$store.state.inBagTricks[i].id == this.updateTrickId) {
            let trickToPush = this.$store.state.inBagTricks[i];
            this.$store.state.inBagTricks.splice(i , 1);
            this.$store.state.inProgressTricks.push(trickToPush);
          }
        }
      }
    },






    // API CALLS
    handleCreateTrick() {
      // Show new trick form
      this.showNewTrickForm = !this.showNewTrickForm;
    },

    handleSubmitNewTrick() {
      if (!this.newTrickError) {
        // Map the isKnown and flipOrShuv
        this.newTrick.known = this.inBagRadio;
        this.newTrick.flipOrShuv = this.flipShuvRadio;

        TrickService.createTrick(this.newTrick)
          .then((response) => {
            // Push trick to store array to dynamically update page, reset the form, reset the data trick, and close the trick form
            this.addTrickToStore();
            this.resetForm();
            this.newTrick = {};
            this.showNewTrickForm = !this.showNewTrickForm;
          })
          .catch((error) => {
            this.errorHandler(error, "creating");
          });
      } else {
        return;
      }
    },

    updateTrickKnown() {
      TrickService.getTrick(this.updateTrickId)
        .then((response) => {      
          TrickService.updateTrick(this.updateTrickId,  {id : this.updateTrickId , name : response.data.name, flipOrShuv : response.data.flipOrShuv, stance : response.data.stance , known : this.changeKnown })
           .then ((response) => {
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




    // STORE METHODS
    addTrickToStore() {
      if (this.newTrick.known === "Yes") {
        this.$store.state.inBagTricks.push(this.newTrick);

      } else {
        this.$store.state.inProgressTricks.push(this.newTrick);
      }
    },





    // DRAG AND DROP METHODS
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



    // FORM METHODS
    resetForm() {
      this.$refs.form.reset();
    },

    async validateForm() {
      const { valid } = await this.$refs.form.validate()

      if (valid) {
        this.handleSubmitNewTrick();
      }
      else {
        alert("Form is invalid");
      }
    },
  },
};
</script>

<style scoped>
.cards-column-container {
  display: flex;
  justify-content: space-around;
  align-items: center;

  min-width: 80%;
}

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

.new-trick-form {
  border: 1px solid black;

  padding: 10px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  min-width: 10%;
  min-height: 100px;
}

.new-trick-form-radio-buttons {
  display: flex;
  gap: 5px;
}

.new-trick-form input {
  margin-bottom: 10px;
}

input[type="radio"] {
  margin-top: 3px;
}
</style>