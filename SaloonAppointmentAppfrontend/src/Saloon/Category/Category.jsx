import Button from '@mui/material/Button';
import React, { useState } from 'react'
import CategoryForm from './CategoryForm';
import CategoryTable from './CategoryTable';

const Category = () => {
    const [activeTab, setActiveTab] = useState(1)
    const handleClick = (tab) => () =>
        setActiveTab(tab);

    return (
        <div>
            <div className='flex items-center gap-5'>

                <Button onClick={handleClick(1)} variant={activeTab === 1 ? "contained" : "outlined"}>All Categories</Button>

                <Button onClick={handleClick(2)} variant={activeTab === 2 ? "contained" :
                    "outlined"}>Create Categories</Button>

            </div>
            <div className=''>
                {activeTab === 1 ? <CategoryTable /> : <CategoryForm></CategoryForm>}

            </div>
        </div>
    )
}

export default Category