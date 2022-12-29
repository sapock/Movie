<template>

    <v-data-table
        :headers="headers"
        :items="reservationInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReservationInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "reservId", value: "reservId" },
                { text: "movieSubject", value: "movieSubject" },
                { text: "screenId", value: "screenId" },
                { text: "screenDate", value: "screenDate" },
                { text: "seatingQty", value: "seatingQty" },
            ],
            reservationInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reservationInfos'))

            temp.data._embedded.reservationInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.reservationInfo = temp.data._embedded.reservationInfos;
        },
        methods: {
        }
    }
</script>

