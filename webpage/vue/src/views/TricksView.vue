<template>
  <button class="create-trick-button" v-on:click="handleCreateTrick">
    Create a New Trick
  </button>

  <div
    class="new-trick-form"
    :class="{ 'is-showing': showNewTrickForm }"
    v-if="showNewTrickForm"
  >
    <div class="new-trick-error-message error-message" v-if="newTrickError">
      <p>You must complete all fields</p>
    </div>

    <label for="name">Name</label>
    <input type="text" id="name" name="name" v-model="newTrick.name" required/>

    <label for="stance">Stance</label>
    <input type="text" id="stance" name="stance" v-model="newTrick.stance" required/>

    <p>Is this trick in your bag?</p>
    <div class="new-trick-form-radio-buttons">
      <label for="Yes">Yes</label>
      <input
        type="radio"
        name="is-known"
        id="Yes"
        value="Yes"
        required
      />

      <label for="No">No</label>
      <input type="radio" name="is-known" id="No" value="No"/>
    </div>

    <p>Is this trick composed of a Shuv, Flip, or both?</p>
    <div class="new-trick-form-radio-buttons">
      <label for="Flip">Flip Trick</label>
      <input type="radio" name="flip-or-shuv" id="Flip" value="Flip" required/>

      <label for="Shuv">Shuv Trick</label>
      <input type="radio" name="flip-or-shuv" id="Shuv" value="Shuv"/>

      <label for="Flip-Shuv">Both</label>
      <input type="radio" name="flip-or-shuv" id="Flip-Shuv" value="Flip-Shuv"/>
    </div>

    <input type="submit" @click="handleSubmitNewTrick" />
  </div>

  <div class="cards-column-container">
    <div>
      <h2 class="tricks-header">Known Tricks</h2>
      <div class="trick-cards-container">
        <trick-card
          v-for="trick in knownTricks"
          :key="trick.id"
          :trick="trick"
          class="trick-card"
        ></trick-card>
      </div>
    </div>

    <div>
      <h2 class="tricks-header">Unknown Tricks</h2>
      <div class="trick-cards-container">
        <trick-card
          v-for="trick in unknownTricks"
          :key="trick.id"
          :trick="trick"
          class="trick-card"
        ></trick-card>
      </div>
    </div>
  </div>
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
      showNewTrickForm: false,
      newTrickError: false,
    };
  },

  created() {
    TrickService.getTricks()
      .then((response) => {
        console.log(response.data);
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
         this.newTrick.known = document.querySelector('input[name=is-known]:checked').value;
         this.newTrick.flipOrShuv = document.querySelector('input[name=flip-or-shuv]:checked').value;

        TrickService.createTrick(this.newTrick)
        .then((response) => {
          console.log("Submit Successful");

          // Reset New Trick Object
          this.newTrick = {};
        })
        .catch((error) => {
          this.errorHandler(error, "creating");
        })
      }
      else {
        return;
      }
    },

    checkFormValid() {
      // Selecting elements
      let name = document.getElementById("name").value;
      let stance = document.getElementById("stance").value;
      let isKnown = document.querySelector('input[name=is-known]:checked');
      let flipOrShuv = document.querySelector('input[name=flip-or-shuv]:checked');

      if (name === "" || stance === "" || isKnown === null || flipOrShuv === null) {
        this.newTrickError = true;
      }
      else {
        this.newTrickError = false; 
      }
    },


  },
};
</script>

<style scoped>
.create-trick-button {
  display: block;

  background-color: grey;

  padding: 5px;
}
.cards-column-container {
  display: flex;
  justify-content: space-around;
  align-items: center;

  min-width: 80%;
}

.trick-cards-container {
  border: 1px solid black;

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
  margin-top : 3px;
}
</style>