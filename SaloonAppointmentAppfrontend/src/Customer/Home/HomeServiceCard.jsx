import React from 'react'

const HomeServiceCard = ({ item }) => {
    return (
        <div className='flex flex-col items-center justify-center w-50 h-55 gap-4 p-5 
        rounded-lg bg-slate-100'>
            <img className='w-28 h-28 rounded-full ' src={item.image} alt="" />
            <h1 className='text-center'>{item.name}</h1>
        </div>
    )
}

export default HomeServiceCard