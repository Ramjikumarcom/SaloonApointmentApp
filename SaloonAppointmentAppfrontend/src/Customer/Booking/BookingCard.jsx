import { ArrowRightAlt } from '@mui/icons-material'
import { Button } from '@mui/material'
import React from 'react'

const BookingCard = () => {
    return (
        <div className='p-5 rounded-md bg-slate-100 md:flex items-center justify-between'>

            <div className='space-y-2'>
                <h1 className='text-2xl font-bold'>Monika Saloon</h1>

                <div>
                    <li>hair cut</li>
                    <li>message therapy</li>
                    <li>hair color</li>
                </div>
                <div>
                    <p>Time & Date <ArrowRightAlt />2025-01-16</p>
                    <p>12:00:00 To 12:45:00</p>
                </div>
            </div>

            <div className='space-y-2'>
                <img className='h-28 w-28' src="https://images.pexels.com/photos/15611688/pexels-photo-15611688.jpeg" alt="" />
                <p className='text-center'>₹249</p>
                <Button color='error' variant='outlined'>Cancel</Button>

            </div>
        </div>
    )
}

export default BookingCard