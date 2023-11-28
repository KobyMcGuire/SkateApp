<template>
  <div id="welcome">
    <h1>Welcome to your bag of tricks</h1>
    <div class="home-images">
      <img class="tre" src="../assets/treFlipPic.PNG" alt="">
      <img class="grab" src="../assets/grabPic.PNG" alt="">
      <img class="smith" src="../assets/smithGrind.PNG" alt="">
    </div>
  </div>
</template>

<script>
import { assert } from '@vue/compiler-core';

export default {

}
</script>

<style scoped>
#welcome {
  min-width: 80vw;

  display: flex;
  justify-content: space-between;
}

h1 {
  width: 500px;

  font-size: 5rem;

  text-align: center;
  }

  img {
    border-radius: 3px; 

    position: relative;
  }

.tre {
  width: 800px;
  height: auto;

  z-index: 1;
}

.grab {
  height: 500px;
  width: auto;

  z-index: 2;
  bottom: 5%;
  left: -10%;

}
.smith {
  height: 500px;
  width: auto;

  z-index: 3;
  left: 25%;
  bottom: 10%;

}
</style>