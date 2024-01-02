<template>
    <div class="trick-card">
        <p>{{ trick.name }}</p>
        <p>{{  trick.stance }}</p>
    </div>
</template>

<script>
export default {
    props : ['trick'],

}
</script>

<style scoped>

.trick-card {
    border: 1px solid black;

    padding: 5px;

    /* Change this to percent? */
    min-width: 150px;

    text-align: center;
}

</style>