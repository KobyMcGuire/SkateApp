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

    min-width: 15%;

    text-align: center;
}

</style>