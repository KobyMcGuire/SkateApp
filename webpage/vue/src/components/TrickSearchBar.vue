<template>
    <v-toolbar
      dense
      floating
      height="50"
      rounded
    >
      <v-text-field
        hide-details
        prepend-icon="mdi-magnify"
        single-line
        v-model="searchTerm"
        v-on:keyup="filterTricks()"
      ></v-text-field>

    </v-toolbar>
</template>

<script>
export default {
    props: ["isKnown"],

    data() {
      return {
        searchTerm : ""
      }
    },

    methods :  {
      filterTricks() {
        let tricks = ""
        if (this.isKnown === 'Yes') {
          this.$store.state.inBagTricks = this.$store.state.inBagTricks.filter((trick) => {
            if (trick.name.includes(this.searchTerm)) {
              return true;
            }
          })
        }
        else {
          tricks = "no";
        }
        console.log(this.searchTerm);
        return tricks;
      },
    }
}
</script>

<style scoped>

</style>