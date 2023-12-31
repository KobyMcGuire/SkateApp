<template>
  <div>

    <div class="trick-cards-container">
      <trick-card v-for="trick in tricks" :key="trick.id" :trick="trick"></trick-card>
    </div>

  </div>
</template>

<script>
import TrickService from '../services/TrickService';
import TrickCard from '../components/TrickCard.vue';

export default {
  components : {TrickCard},

  data() {
    return {
      tricks : [],
    }
  },

  created() {
    TrickService.getTricks()
    .then((response) => {
      console.log(response.data);
      this.tricks = response.data;
    })
    .catch((error) => {
      this.errorHandler(error, "fetching tricks");
    })

  },

  methods : {

    errorHandler(error, verb){
      console.log(`There was an error ${verb}, the error was: ${error}`);
    },
  
  }
}
</script>

<style scoped>
.trick-cards-container {
  border: 1px solid black;
  
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px ;
}

</style>