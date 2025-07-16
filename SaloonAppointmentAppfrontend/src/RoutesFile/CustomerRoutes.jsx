import React from 'react'
import { Route, Routes } from 'react-router-dom'
import SaloonDashboard from '../Saloon/SaloonDashboard'

import SaloonDetails from '../Customer/Saloon/SaloonDetails/SaloonDetails'
import Booking from '../Customer/Booking/Booking'
import Navbar from '../Customer/Navbar/Navbar'
import Home from '../Customer/Home/Home'
import Notifications from '../Customer/Notifications/Notifications'
import PageNotFound from '../PageNotFound/PageNotFound'

const CustomerRoutes = () => {
    return (
        <div>
            <Navbar></Navbar>
            <Routes>

                <Route path='/' element={<Home></Home>} />

                <Route path='/notifications' element={<Notifications></Notifications>} />

                <Route path='/saloon/:id' element={<SaloonDetails></SaloonDetails>} />

                <Route path='/bookings' element={<Booking></Booking>} />
                <Route path='*' element={<PageNotFound></PageNotFound>} />


            </Routes>
        </div>
    )
}

export default CustomerRoutes