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

.error-message {
  background-color: #fca5a5;

  padding: 5px;

  border-radius: 5px;

  margin-bottom: 5px;
}

button {
  border: none;
  border-radius: 5px;

  transition: all 0.1s ease-in-out;
}

button:hover {
  border: 2px solid black;
  transform: scale(1.025);

  cursor: pointer;
}

input[type="submit"] {
  padding: 5px;

  border: none;
  border-radius: 5px;

  background-color: #86efac;

  transition: all 0.1s ease-in-out;
}

input[type="submit"]:hover {
  border: 2px solid black;
  transform: scale(1.025);

  cursor: pointer;
}

#skate-trainer {
  background-color: #f1f5f9;

  min-height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content:flex-start;
  align-items: center;
  gap: 100px  ;
}

</style>
