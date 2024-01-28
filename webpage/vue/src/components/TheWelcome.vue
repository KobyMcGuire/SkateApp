<template>
  <v-sheet class="d-flex justify-center align-center ga-5">

    <v-sheet width="45%">
      <h1>Welcome to your Bag</h1>
      <p class="text-center">
        A tool that will enhance and bolster your growth in skateboarding.
        Record what tricks you know, and what tricks you want to learn, and
        allow our tool to recommend you these tricks in lines, or to take with
        you to your next practice sesh.
      </p>
    </v-sheet>

    <img src="../assets/grabPic.PNG" alt="Man grabbing skateboard mid-air" />

  </v-sheet>
</template>

<script>
export default {};
</script>

<style scoped>
#welcome {
  min-width: 80vw;

  display: flex;
  justify-content: space-between;
}

h1 {
  font-size: 5rem;
  text-align: center;
}

img {
  border-radius: 3px;

  position: relative;
}
</style>