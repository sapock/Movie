<template>

    <v-card outlined>
        <v-card-title>
            MovieRatingInfo # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="CustomerId" v-model="item.customerId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Score" v-model="item.score" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Review" v-model="item.review" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="MovieId" v-model="item.movieId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MovieRatingInfoViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/movieRatingInfos/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

