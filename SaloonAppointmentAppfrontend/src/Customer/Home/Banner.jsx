import React from 'react'
import bannerImage from '../../Assets/Video/bannerImage.mp4'

const Banner = () => {
    return (
        <div className='w-full relative h-[80vh]'>
            <video
                className='object-cover w-full h-full'
                muted
                autoPlay
                autoFocus
                src={bannerImage}
            >

            </video>
            <div
                className="absolute inset-0 z-20 flex flex-col items-center justify-center px-5 space-y-3 text-white"
            >
                <h1 className="text-5xl font-bold">Be your self</h1>
                <p className="text-2xl text-center text-slate-400">
                    Discover and Book Beauty, Wellness near you
                </p>

                <input className='
                border-none bg-white rounded-md p-5 py-4 w-[15rem] md:w-[33rem] outline-none text-black px-5
                ' type="text" placeholder='search saloon service ...' />
            </div>

        </div>
    )
}

export default Banner
