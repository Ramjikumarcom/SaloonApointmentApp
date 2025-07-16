import React from 'react'
import { FiberManualRecord } from '@mui/icons-material'
import { Button } from '@mui/material'
const ServiceCard = () => {
    return (
        <div className='w-full'>

            <div className='flex items-center justify-between gap-5'>
                <div className='space-y1 w-[60%]'>
                    <h1 className='text-2xl font-semibold'>Man Beard</h1>
                    <p className='text-gray-500 text-sm'>Stylish man Beard</p>
                    <div className='flex items-center gap-3'>
                        <p>₹400</p>
                        <FiberManualRecord style={{ fontSize: "10px", color: 'gray' }} />
                        <p>45 mins</p>
                    </div>
                </div>

                <div className="space-y-3">
                    <img className='w-50  object-cover rounded-md' src="https://images.pexels.com/photos/15611684/pexels-photo-15611684.jpeg" alt="" />
                    <Button fullWidth variant='outlined' >Add</Button>

                </div>
            </div>
        </div>
    )
}

export default ServiceCard