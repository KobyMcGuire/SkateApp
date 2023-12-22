<template>
  <div>
    <ul>
      <li v-for="trick in tricks" :key="trick.id"> {{ trick.name }} </li>
    </ul>
  </div>
</template>

<script>
import TrickService from '../services/TrickService';

export default {
  data() {
    return {
      tricks : [],
    }
  },

  created() {
    TrickService.getTricks()
    .then((response) => {
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

<style>

</style>