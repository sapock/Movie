
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RatingInfoManager from "./components/listers/RatingInfoCards"
import RatingInfoDetail from "./components/listers/RatingInfoDetail"

import ScreenManager from "./components/listers/ScreenCards"
import ScreenDetail from "./components/listers/ScreenDetail"


import ReservationInfoView from "./components/ReservationInfoView"
import ReservationInfoViewDetail from "./components/ReservationInfoViewDetail"
import MovieRatingInfoView from "./components/MovieRatingInfoView"
import MovieRatingInfoViewDetail from "./components/MovieRatingInfoViewDetail"
import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"

import ReservationManager from "./components/listers/ReservationCards"
import ReservationDetail from "./components/listers/ReservationDetail"

import MovieManager from "./components/listers/MovieCards"
import MovieDetail from "./components/listers/MovieDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/ratingInfos',
                name: 'RatingInfoManager',
                component: RatingInfoManager
            },
            {
                path: '/ratingInfos/:id',
                name: 'RatingInfoDetail',
                component: RatingInfoDetail
            },

            {
                path: '/screens',
                name: 'ScreenManager',
                component: ScreenManager
            },
            {
                path: '/screens/:id',
                name: 'ScreenDetail',
                component: ScreenDetail
            },


            {
                path: '/reservationInfos',
                name: 'ReservationInfoView',
                component: ReservationInfoView
            },
            {
                path: '/reservationInfos/:id',
                name: 'ReservationInfoViewDetail',
                component: ReservationInfoViewDetail
            },
            {
                path: '/movieRatingInfos',
                name: 'MovieRatingInfoView',
                component: MovieRatingInfoView
            },
            {
                path: '/movieRatingInfos/:id',
                name: 'MovieRatingInfoViewDetail',
                component: MovieRatingInfoViewDetail
            },
            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },

            {
                path: '/reservations',
                name: 'ReservationManager',
                component: ReservationManager
            },
            {
                path: '/reservations/:id',
                name: 'ReservationDetail',
                component: ReservationDetail
            },

            {
                path: '/movies',
                name: 'MovieManager',
                component: MovieManager
            },
            {
                path: '/movies/:id',
                name: 'MovieDetail',
                component: MovieDetail
            },



    ]
})
