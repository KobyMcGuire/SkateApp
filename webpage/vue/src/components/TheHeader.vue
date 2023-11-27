<template>
  <div>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <p>|</p>
      <router-link v-bind:to="{ name : 'tricks' }">Tricks</router-link>
    </nav>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
a {

    border: px solid black;
    border-radius: 5px;

    margin: 5px;
    padding: 3px;

    text-decoration: none;
}

nav a::onhover {
    cursor: pointer;
}

p {
    display: inline;
}

</style>