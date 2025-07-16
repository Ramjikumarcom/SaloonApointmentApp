import React from 'react'
import SaloonDrawerList from './Components/SaloonDrawerList'
import Navbar from '../Saloon Admin/Navbar'
import SaloonRoutes from '../RoutesFile/SaloonRoutes'


const SaloonDashboard = () => {
    return (
        <div className='min-h-screen'>
            <Navbar DrawerList={SaloonDrawerList}></Navbar>
            <section className='lg:flex lg:h-[90vh]'>
                <div className="hidden lg:block h-full">
                    <SaloonDrawerList></SaloonDrawerList>
                </div>
                <div className='p-10 w-full lg:w-[80%] overflow-y-auto'>
                    <SaloonRoutes></SaloonRoutes>

                </div>

            </section>

        </div>
    )
}

export default SaloonDashboard