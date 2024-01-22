<template>
  <v-sheet class="d-flex flex-column ga-10">
    <v-sheet class="d-flex ga-10">

      <tricks-container :isKnown="'Yes'" />

      <tricks-container :isKnown="'No'" />

    </v-sheet>

    <v-expand-transition>
      <v-form v-if="showNewTrickForm" @submit.prevent ref="form">
        <v-text-field v-model="newTrickName" label="Trick Name" :rules="trickNameRules"></v-text-field>

        <v-select v-model="newTrickStance" :items="stances" :rules="stanceNameRules" label="Stance"></v-select>

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
import TricksContainer from "../components/TricksContainer.vue";


export default {
  components: { TricksContainer },

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
      newTrick : {},
      newTrickName : "",
      newTrickStance : "",
      updateTrickId: "",
      fetchedTrick: {},
      changeKnown: "",
      trickNameRules: [
        (value) => {
          if (value?.length > 0) return true;

          return "Trick name must not be left empty";
        },
      ],
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
    // Helper Methods
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

    handleCreateTrick() {
      // Show new trick form
      this.showNewTrickForm = !this.showNewTrickForm;
    },




    // API calls
    handleSubmitNewTrick() {
        // Map the newTrick object
        this.newTrick.name = this.newTrickName;
        this.newTrick.flipOrShuv = this.flipShuvRadio;
        this.newTrick.stance = this.newTrickStance;
        this.newTrick.known = this.inBagRadio;


        TrickService.createTrick(this.newTrick)
          .then((response) => {
            // Push trick to store array to dynamically update page, reset the form, reset the data trick, and close the trick form
            this.newTrick.id = response.data.id;
            this.addTrickToStore();
            this.resetForm();
            this.newTrick = {};
            this.showNewTrickForm = !this.showNewTrickForm;
          })
          .catch((error) => {
            this.errorHandler(error, "creating");
          });    
    },




    // Store Methods
    addTrickToStore() {
      if (this.newTrick.known === "Yes") {
        this.$store.state.inBagTricks.push(this.newTrick);
      } else {
        this.$store.state.inProgressTricks.push(this.newTrick);
      }
    },



    // Form Methods
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
input[type="radio"] {
  margin-top: 3px;
}
</style>