<template>
    <v-card class="pa-3 elevation-10 bg-blue-grey-lighten-4">
        <v-card-item>
            <v-card-title>{{ practiceBag.date }}</v-card-title>
        </v-card-item>
    </v-card>
</template>

<script>

export default {
    props : ['practiceBag'],
}
</script>

<style scoped>
</style>