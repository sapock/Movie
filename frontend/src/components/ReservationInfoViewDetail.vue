<template>

    <v-card outlined>
        <v-card-title>
            ReservationInfo # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="ReservId" v-model="item.reservId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MovieSubject" v-model="item.movieSubject" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ScreenId" v-model="item.screenId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="ScreenDate" v-model="item.screenDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="SeatingQty" v-model="item.seatingQty" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReservationInfoViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reservationInfos/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

