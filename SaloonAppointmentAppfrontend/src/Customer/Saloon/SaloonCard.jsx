import React from 'react'
import StarIcon from '@mui/icons-material/Star';
import { useNavigate } from 'react-router-dom';
const SaloonCard = () => {
    const navigation = useNavigate();
    return (
        <div onClick={() => navigation("/saloon/2")} className='w-56 cursor-pointer rounded-md md:w-80 bg-slate-100 '>
            <img className='w-full h-[15rem] object-cover rounded-t-md' src="https://images.pexels.com/photos/7518740/pexels-photo-7518740.jpeg" alt="" />
            <div className='p-8 space-y-2'>
                <h1>Elite Saloon</h1>
                <div className='
                text-white text-sm p-1 bg-green-700 rounded-full w-[4rem]
                flex items-center justify-center gap-1
                '>
                    <StarIcon />
                </div>
                <p>Profesional hair cut .....</p>
                <p>adani house ,mumbai</p>
            </div>
        </div>
    )
}

export default SaloonCard