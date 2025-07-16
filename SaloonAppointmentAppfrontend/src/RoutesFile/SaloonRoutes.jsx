import React from 'react'
import { Route, Routes } from 'react-router-dom'
import SaloonHome from '../Saloon/SaloonHome/SaloonHome'
import Services from '../Saloon/Services/Services'
import CreateServiceform from '../Saloon/Services/CreateServiceform'
import Booking from '../Saloon/Booking/Booking'

import Transaction from '../Saloon/Transaction/Transaction'
import Payment from '../Saloon/Payment/Payment'
import Category from '../Saloon/Category/Category'
import Notifications from '../Customer/Notifications/Notifications'
import Profile from '../Saloon/Profile/Profile'

const SaloonRoutes = () => {
    return (
        <Routes>
            <Route path='/' element={<SaloonHome></SaloonHome>}></Route>
            <Route path='/services' element={<Services></Services>}></Route>
            <Route path='/add-services' element={<CreateServiceform></CreateServiceform>}></Route>
            <Route path='/bookings' element={<Booking></Booking>}></Route>
            <Route path='/category' element={<Category></Category>}></Route>
            <Route path='/transaction' element={<Transaction></Transaction>}></Route>
            <Route path='/notifications' element={<Notifications></Notifications>}></Route>
            <Route path='/payment' element={<Payment></Payment>}></Route>
            <Route path='/account' element={<Profile></Profile>}></Route>

        </Routes>
    )
}

export default SaloonRoutes