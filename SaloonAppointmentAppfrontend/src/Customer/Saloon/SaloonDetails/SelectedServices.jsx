import { Close } from '@mui/icons-material'
import { IconButton } from '@mui/material'
import React from 'react'

const SelectedServices = () => {
    return (
        <div className='my-5 space-y-3'>

            {[1, 1, 1, 1].map((item, index) => <div className='p-3 space-x-8 rounded-md bg-slate-100 flex justify-between items-center'>
                <h1 className='font-thin'>Man Beard</h1>
                <p>₹399</p>
                <IconButton>
                    <Close></Close>
                </IconButton>
            </div>)}
        </div>
    )
}

export default SelectedServices