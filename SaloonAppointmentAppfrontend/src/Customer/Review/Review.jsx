import React from 'react'
import ReviewCard from './ReviewCard'
import { Divider } from '@mui/material'
import RatingCard from './RatingCard'

const Review = () => {
    return (
        <div className='pt-10 flex flex-col lg:flex-row gap-20'>
            <section className='w-full md:w-0.5 lg:w-[40%] space-y-2'>
                <h1 className='font-semibold text-lg pb-4'>
                    <RatingCard></RatingCard>
                </h1>

            </section>

            <section className='w-full md:w-0.5 lg:w-[60%]'>

                <div className='mt-10'>

                    <div className='space-y-5'>
                        {
                            [1, 1, 1, 1, 1].map(() => <div className='space-y-4'>
                                <ReviewCard></ReviewCard>
                                <Divider></Divider>
                            </div>)
                        }

                    </div>
                </div>
            </section>

        </div>
    )
}

export default Review