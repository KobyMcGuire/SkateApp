<template>
    <v-toolbar
      dense
      floating
    >
      <v-text-field
        hide-details
        prepend-icon="mdi-magnify"
        single-line
      ></v-text-field>

    </v-toolbar>
</template>

<script>
export default {
    props: ["isKnown"],

}
</script>

<style scoped>

</style>