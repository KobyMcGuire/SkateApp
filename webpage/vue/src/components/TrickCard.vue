<template>
  <v-card class="trick-card">
    <v-card-item>
      <v-card-title>{{ trick.name }}</v-card-title>
      <v-card-subtitle>{{ trick.stance }}</v-card-subtitle>
    </v-card-item>
  </v-card>
</template>

<script>
export default {
  props: ["trick"],
};
</script>

<style scoped>
.trick-card {
  min-width: 100px;
}
</style>