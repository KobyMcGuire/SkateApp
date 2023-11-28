<template>
  <div id="skate-trainer">
    <the-header/>
    <router-view />
    <the-footer/>
  </div>
</template>

<script>
import TheHeader from './components/TheHeader.vue';
import TheFooter from './components/TheFooter.vue';

export default {

  components : {
    TheHeader,
    TheFooter
  },

}
</script>

<style>
* {
  font-family:'Gill Sans', 'Gill Sans MT', 'Calibri', 'Trebuchet MS', 'sans-serif';
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#skate-trainer {
  background-color: #f1f5f9;

  min-height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

</style>
