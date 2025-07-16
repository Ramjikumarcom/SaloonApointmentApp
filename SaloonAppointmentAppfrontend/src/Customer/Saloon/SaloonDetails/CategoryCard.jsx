import React from 'react'

const CategoryCard = ({ handleCategoryClick, selectedCategoryClick, item }) => {
    return (
        <div onClick={handleCategoryClick} className={`px-3 py-2 cursor-pointer flex gap-2 pt-2 items-center
      ${selectedCategoryClick === item ? " bg-green-500 text-white rounded-md" : ""}`}>

            <img className='w-15 h-14 object-cover rounded-full' src="https://images.pexels.com/photos/458766/pexels-photo-458766.jpeg" alt="" />
            <h1 >Bridal Makeup</h1>
        </div>
    )
}

export default CategoryCard