import React, { useState } from 'react'
import SaloonDetail from './SaloonDetail'
import { Button, Divider } from '@mui/material'
import SaloonServiceDetails from './SaloonServiceDetails'
import Review from '../../Review/Review'
import CreateReviewForm from '../../Review/CreateReviewForm'

const tabs = [{ name: "All Services" }, { name: "Reviews" }, { name: "Create Reviews" }]

const SaloonDetails = () => {
    const [activetab, setActivetab] = useState(tabs[0]);
    return (
        <div className='pd-5 lg:px-20'>
            <SaloonDetail />
            <div className='space-y-4'>
                <div className='flex gap-2'>
                    {
                        tabs.map((item) => <Button key={item.name} onClick={() => setActivetab(item)}
                            variant={item.name == activetab.name ? 'contained' : 'outlined'} >{item.name}</Button>)
                    }
                </div>
                <Divider></Divider>
            </div>
            <div>
                {
                    activetab.name === "All Services" ? <div><SaloonServiceDetails /></div> : activetab.name ===
                        "Reviews" ? <div>

                        <Review></Review>


                    </div> : <div className='flex justify-center mt-2' ><CreateReviewForm></CreateReviewForm></div>
                }
            </div>

        </div>
    )
}

export default SaloonDetails