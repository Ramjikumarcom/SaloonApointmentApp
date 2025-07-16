import React from 'react'
import ProfileFieldCard from './ProfileFieldCard'
import { Divider } from '@mui/material'

const Profile = () => {
    return (
        <div className='lg:px-20 lg:bottom-20 space-y-20'>

            <div className='w-full lg:w-[70%]'>
                <h1 className='text-5xl font-bold pb-5'>Elite Saloon</h1>
                <section className='grid grid-cols-2 gap-3 '>
                    <div className="col-span-2">
                        <img className='w-full rounded-md h-[22rem] object-cover' src="https://images.pexels.com/photos/1813272/pexels-photo-1813272.jpeg" alt="" />
                    </div>
                    <div className="col-span-1">
                        <img className='w-full rounded-md h-[22rem] object-cover' src="https://images.pexels.com/photos/3992863/pexels-photo-3992863.jpeg" alt="" />
                    </div>
                    <div className="col-span-1">
                        <img className='w-full rounded-md h-[22rem] object-cover' src="https://images.pexels.com/photos/3998414/pexels-photo-3998414.jpeg" alt="" />
                    </div>
                </section>


            </div>
            <div className='mt-10 lg:w-[70%]'>

                <div className='flex items-center pb-3 justify-between'>
                    <h1 className='text-2xl font-bold text-gray-600'>Saloon Details</h1>
                </div>
                <div>
                    <ProfileFieldCard keys={"saloon name"} value={"Elite saloon"}></ProfileFieldCard>
                    <Divider>

                    </Divider>

                    <ProfileFieldCard keys={"saloon address"} value={"ambavadi choke ,bangalore"}></ProfileFieldCard>

                    <Divider></Divider>

                    <ProfileFieldCard keys={"open Time"} value={"10:00 AM"}></ProfileFieldCard>

                    <Divider></Divider>

                    <ProfileFieldCard keys={"Close Time"} value={"10:00 PM"}></ProfileFieldCard>

                </div>
            </div>

            <div className='mt-10 lg:w-[70%]'>

                <div className='flex items-center pb-3 justify-between'>
                    <h1 className='text-2xl font-bold text-gray-600'>Owner Details</h1>
                </div>
                <div>
                    <ProfileFieldCard keys={"Owner name"} value={"Ramji Gupta"}></ProfileFieldCard>
                    <Divider>

                    </Divider>

                    <ProfileFieldCard keys={"email"} value={"ramjik708@gmail.com"}></ProfileFieldCard>

                    <Divider></Divider>

                    <ProfileFieldCard keys={"Role"} value={"Saloon Owner"}></ProfileFieldCard>


                </div>
            </div>


        </div>
    )
}

export default Profile