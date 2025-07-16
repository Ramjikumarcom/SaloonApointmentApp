import React from 'react'
import SaloonCard from './SaloonCard'

const SaloonList = () => {
    return (
        <div className='flex  flex-wrap justify-between gap-7 p-4'>
            {
                [1, 1, 1, 1, 1].map((item) => <SaloonCard></SaloonCard>)
            }
        </div>
    )
}

export default SaloonList
