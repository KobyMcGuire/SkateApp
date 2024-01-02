<template>
  
  <button class="create-trick-button" v-on:click="handleCreateTrick">
    Create a New Trick
  </button>

  <div class="new-trick-form" :class="{'is-showing' : showNewTrickForm}" v-if="showNewTrickForm">
    <p>This form is showing</p>
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
      showNewTrickForm: false,
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


  min-width: 10%;
  min-height: 100px;
}

</style>