<template>
    <div class="cards-column-container">
      <h2>Known Tricks</h2>
      <div class="trick-cards-container">
        <trick-card
          v-for="trick in knownTricks"
          :key="trick.id"
          :trick="trick"
        ></trick-card>
      </div>

      <h2>Unknown Tricks</h2>
      <div class="trick-cards-container">
        <trick-card
          v-for="trick in unknownTricks"
          :key="trick.id"
          :trick="trick"
        ></trick-card>
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
  },
};
</script>

<style scoped>

.cards-column-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px ;

  min-width: 80%;
}

.trick-cards-container {
  border: 1px solid black;

  padding: 5px;

  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;

  min-width: 100%;
}
</style>