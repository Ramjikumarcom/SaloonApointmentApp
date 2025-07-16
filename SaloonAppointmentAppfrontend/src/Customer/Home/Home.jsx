import React from 'react'
import Banner from './Banner'
import HomeServiceCard from './HomeServiceCard'
import { services } from '../../Data/Service'
import SaloonList from '../Saloon/SaloonList'
const Home = () => {
    return (
        <div className='space-y-20'>
            <section>
                <Banner />
            </section>
            <section className='items-center gap-5 px-20 space-y-10 lg:space-y-0 lg:flex'>
                <div className='w-full lg:w-[50%]'>
                    <h1 className='text-2xl font-semibold pb-9'>What are you looking for, Bestie?</h1>
                    <div className='flex flex-wrap justify-center items-center gap-4'>
                        {
                            services.map((item) => <HomeServiceCard key={item.id} item={item}></HomeServiceCard>)
                        }
                    </div>

                </div>
                <div className='w-full lg:w-1/2  grid gap-4 m-b-0  grid-cols-2 grid-rows-12
                 h-[40vh] md:h-[100vh]'>

                    <div className='row-span-6'>
                        <img className='h-full w-full rounded-md'
                            src="https://images.pexels.com/photos/32351045/pexels-photo-32351045.jpeg" alt="" />
                    </div>

                    <div className='row-span-4'>
                        <img className='h-full w-full rounded-md'
                            src="https://images.pexels.com/photos/3998402/pexels-photo-3998402.jpeg" alt="" />
                    </div>


                    <div className='row-span-6'>
                        <img className='h-full w-full rounded-md'
                            src="https://images.pexels.com/photos/32351045/pexels-photo-32351045.jpeg" alt="" />
                    </div>

                    <div className='row-span-4'>
                        <img className='h-full w-full rounded-md'
                            src="https://images.pexels.com/photos/4625642/pexels-photo-4625642.jpeg" alt="" />
                    </div>

                </div>


            </section>

            <section className='px-20'>
                <h1 className='text-3xl font-bold pb-10'>Book your Favourite Saloon</h1>
                <SaloonList></SaloonList>
            </section>
        </div>

    )
}

export default Home