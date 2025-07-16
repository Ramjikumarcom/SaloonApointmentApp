import { Card, Divider } from '@mui/material'
import React from 'react'

const Payment = () => {
    return (
        <div>
            <div className=''>
                <Card className='rounded-md space-y-4 p-7'>
                    <h1 className='font-semibold'>Total Earning</h1>
                    <h1 className='text-gray-600 font-medium pb-2'>₹8976</h1>
                    <Divider></Divider>
                    <p className='font-bold text-xl pb-2 pt-3'>Last Payment: <strong>$0</strong> </p>

                </Card>
            </div>
        </div>
    )
}

export default Payment