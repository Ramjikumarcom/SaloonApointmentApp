import React from 'react'

const SaloonDetail = () => {
    return (
        <div className='space-y-5 mb-20'>
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

            <section className='space-y-2 py-2  '>

                <h1 className='font-bold text-4xl '>Elite Saloon</h1>
                <p>Ambavadi choke Banalore</p>
                <p>
                    <strong>Timing:</strong> 10:00:00 To 18:30:00
                </p>
            </section>

        </div>
    )
}

export default SaloonDetail