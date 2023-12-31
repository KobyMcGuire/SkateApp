<template>
  <div>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <p>|</p>
      <router-link v-bind:to="{ name : 'tricks' }">My Tricks</router-link>
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

    font-size: 3rem;

    text-decoration: none;
}

nav a::onhover {
    cursor: pointer;
}

p {
    display: inline;

    font-size: 3rem;
}

</style>