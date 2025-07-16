import React, { useState } from 'react'
import CategoryCard from './CategoryCard'
import ServiceCard from './ServiceCard';
import { Button, Divider } from '@mui/material';
import { RemoveShoppingCart, ShoppingCart } from '@mui/icons-material';
import { green } from '@mui/material/colors';
import SelectedServices from './SelectedServices';

const SaloonServiceDetails = () => {
    const [selectedCategoryClick, setSlecetedCategory] = useState(0);
    const handleCategoryClick = (category) => () => { setSlecetedCategory(category) }
    return (
        <div className='lg:flex gap-5 h-[90vh] mt-10'>

            <section className='space-y-5 border-r lg:w-[25%] pr-5'>
                {[1, 1, 1, 1, 1].map((item, index) => <CategoryCard selectedCategoryClick={selectedCategoryClick}
                    handleCategoryClick={handleCategoryClick(index)} item={index}
                />)}
            </section>
            <section className='space-y-2 lg:w-[50%] px-5 lg:px-20 overflow-y-auto'>
                {[1, 1, 1, 1, 1, 1].map((item) => <div className='space-y-4'>
                    <ServiceCard />
                    <Divider></Divider>
                </div>)}
            </section>

            <section className='lg:w-[25%]'>

                <div className='border rounded-md p-5'>

                    {true ? <div>
                        <div className='flex items-center gap-2'>
                            <ShoppingCart sx={{ fontSize: "30px", color: "green" }}></ShoppingCart>
                            <h1 className=' text-sm'>selected Services</h1>
                        </div>


                        <SelectedServices></SelectedServices>
                        <Button sx={{ py: "0.7rem" }} fullWidth variant='contained'>Book Now</Button>
                    </div> :
                        <div className='flex flex-col gap-3 items-center justify-center'>
                            <RemoveShoppingCart sx={{ fontSize: "50px", color: "green" }}></RemoveShoppingCart>
                            <h1>Not selected</h1>
                        </div>}
                </div>
            </section>
        </div>
    )
}

export default SaloonServiceDetails