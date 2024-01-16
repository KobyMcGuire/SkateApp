<template>
  <v-app>
    <v-navigation-drawer expand-on-hover rail>
      <v-list>
        <v-list-item
          prepend-avatar=""
          title="Koby McGuire"
          subtitle="testEmail@gmail.com"
        ></v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list density="compact" nav>
        <v-list-item
          prepend-icon="mdi-home"
          title="Home"
          value="home"
          to="/"
          link
        ></v-list-item>
        <v-list-item
          prepend-icon="mdi-skateboard"
          title="My Bag of Tricks"
          value="myTricks"
          to="/tricks"
          link
        ></v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar title="Trick Bag" class="text-center"></v-app-bar>

    <v-main
      class="d-flex align-center justify-center"
      style="min-height: 300px"
    >
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import '../src/assets/global.css'
export default {};
</script>

<style>
/* * {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
} */

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
</style>
