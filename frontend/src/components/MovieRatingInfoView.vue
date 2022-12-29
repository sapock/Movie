<template>

    <v-data-table
        :headers="headers"
        :items="movieRatingInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MovieRatingInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "customerId", value: "customerId" },
                { text: "score", value: "score" },
                { text: "review", value: "review" },
                { text: "movieId", value: "movieId" },
            ],
            movieRatingInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/movieRatingInfos'))

            temp.data._embedded.movieRatingInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.movieRatingInfo = temp.data._embedded.movieRatingInfos;
        },
        methods: {
        }
    }
</script>

