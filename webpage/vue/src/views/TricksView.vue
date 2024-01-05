<template>
  <v-sheet class="d-flex flex-column ga-10">
  
    <v-sheet class="d-flex ga-10">
      <v-sheet rounded class="pa-3 elevation-10 bg-grey-lighten-3">
        <h2 class="tricks-header">Known Tricks</h2>
        <div class="trick-cards-container">
          <trick-card
            v-for="trick in knownTricks"
            :key="trick.id"
            :trick="trick"
            class="trick-card"
          ></trick-card>
        </div>
      </v-sheet>

      <v-sheet rounded class="pa-3 elevation-10 bg-grey-lighten-3">
        <h2 class="tricks-header">Unknown Tricks</h2>
        <div class="trick-cards-container">
          <trick-card
            v-for="trick in unknownTricks"
            :key="trick.id"
            :trick="trick"
            class="trick-card"
          ></trick-card>
        </div>
      </v-sheet>
    </v-sheet>





    <v-form v-if="showNewTrickForm" @submit.prevent fast-fail>
      <div class="new-trick-error-message error-message" v-if="newTrickError">
        <p>You must complete all fields</p>
      </div>

      <v-text-field
        v-model="newTrick.name"
        label="Trick Name"
        :rules="trickNameRules"
      ></v-text-field>

      <v-text-field
        v-model="newTrick.stance"
        label="Stance"
        :rules="stanceNameRules"
      ></v-text-field>

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

      <input type="submit" @click="handleSubmitNewTrick" />
    </v-form>

    <v-btn v-on:click="handleCreateTrick" prepend-icon="mdi-plus" class="bg-blue-grey-lighten-4">
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
      knownTricks: [],
      unknownTricks: [],
      newTrick: {},
      newTrickName : "",
      trickNameRules : [
        value => {
          if (value?.length > 0) return true

          return 'Trick name must not be left empty'
        }
      ],
      newTrickStance : "",
      stanceNameRules : [
        value => {
          if (value?.length > 0) return true

          return 'Stance must not be left empty'
        }
      ],
      inBagRadio : "",
      inBagRules : [
        value => {
          if (value?.length > 0) return true

          return 'You must select an option'
        }
      ],
      flipShuvRadio : "",
      flipShuvRadioRules : [
        value => {
          if (value?.length > 0) return true

          return 'You must select an option'
        }
      ],
      showNewTrickForm: false,
      newTrickError: false,
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
    errorHandler(error, verb) {
      console.log(`There was an error ${verb}, the error was: ${error}`);
    },

    filterTricks(data) {
      data.forEach((trick) => {
        if (trick.known === "Yes") {
          this.knownTricks.push(trick);
        } else {
          this.unknownTricks.push(trick);
        }
      });
    },

    handleCreateTrick() {
      // Show new trick form
      this.showNewTrickForm = !this.showNewTrickForm;
    },

    handleSubmitNewTrick() {
      this.checkFormValid();

      if (!this.newTrickError) {
        // Map the isKnown and flipOrShuv
        this.newTrick.known = document.querySelector(
          "input[name=is-known]:checked"
        ).value;
        this.newTrick.flipOrShuv = document.querySelector(
          "input[name=flip-or-shuv]:checked"
        ).value;

        TrickService.createTrick(this.newTrick)
          .then((response) => {
            // Reset New Trick Object
            this.newTrick = {};
          })
          .catch((error) => {
            this.errorHandler(error, "creating");
          });
      } else {
        return;
      }
    },

    checkFormValid() {
      // Selecting elements
      let name = document.getElementById("name").value;
      let stance = document.getElementById("stance").value;
      let isKnown = document.querySelector("input[name=is-known]:checked");
      let flipOrShuv = document.querySelector(
        "input[name=flip-or-shuv]:checked"
      );

      if (
        name === "" ||
        stance === "" ||
        isKnown === null ||
        flipOrShuv === null
      ) {
        this.newTrickError = true;
      } else {
        this.newTrickError = false;
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