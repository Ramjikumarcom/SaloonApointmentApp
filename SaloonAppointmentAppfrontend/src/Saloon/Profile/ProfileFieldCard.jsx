import { Divider } from '@mui/material'
import React from 'react'

const ProfileFieldCard = ({ value, keys }) => {
    return (
        <div className='p-5 flex space-x-5 items-center bg-slate-50'>
            <p className='w-20 lg:w-36 p-3'>{keys}</p>
            <Divider flexItem orientation='vertical'></Divider>
            <p className='pl-4 lg:pl-10 font-semibold lg:text-lg'>
                {value}
            </p>
        </div>
    )
}

export default ProfileFieldCard