<template>
  <v-dialog width="500">
    <template v-slot:activator="{ props }">
      <v-btn v-bind="props" class="bg-blue-grey-lighten-4">{{ practiceBag.date }}</v-btn>
    </template>

    <template v-slot:default="{ isActive }">
      <v-card class="pa-5 bg-blue-grey-lighten-4">

        <v-card-item>
          <v-card-title class="text-center">{{
            practiceBag.date
          }}</v-card-title>
        </v-card-item>

        <v-divider class="mb-2"></v-divider>

        <v-sheet class="d-flex justify-center ga-5 bg-blue-grey-lighten-4">
          <trick-card
            v-for="trick in tricks"
            v-bind:key="trick.id"
            v-bind:trick="trick"
            width="40%"
          ></trick-card>
        </v-sheet>
        
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text="Close" @click="isActive.value = false"></v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import TrickCard from "./TrickCard.vue";
import TrickService from "../services/TrickService";

export default {
  components: { TrickCard },
  props: ["practiceBag"],

  data() {
    return {
      tricks: [],
    };
  },

  created() {
    TrickService.getPracticeBagTricks(this.practiceBag.id).then((response) => {
      this.tricks = response.data;
    });
  },
};
</script>

<style scoped>
</style>