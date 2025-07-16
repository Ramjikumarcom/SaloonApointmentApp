import React from 'react'
import { useNavigate } from 'react-router-dom';
// import { ArrowLeftCircle } from 'lucide-react'; // optional: install lucide-react or use any icon set

const PageNotFound = () => {
    const navigate = useNavigate();
    return (
        <div className="min-h-screen bg-gradient-to-br from-white to-gray-100 flex flex-col items-center justify-center px-6 text-center">
            <div className="max-w-md">
                <h1 className="text-[10rem] font-extrabold text-purple-600 leading-none drop-shadow-lg">
                    404
                </h1>
                <h2 className="text-3xl md:text-4xl font-bold text-gray-800 mt-4">Oops! Page not found</h2>
                <p className="text-gray-600 mt-4 mb-6 text-base md:text-lg">
                    The page you're looking for doesn’t exist or has been moved. Please check the URL or go back home.
                </p>
                <button
                    onClick={() => navigate('/')}
                    className="inline-flex items-center cursor-pointer gap-2 px-6 py-3 bg-green-400 text-white text-sm md:text-base rounded-xl hover:bg-purple-700 transition-all duration-300 shadow-md"
                >
                    {/* <ArrowLeftCircle className="w-5 h-5" /> */}
                    Back to Home
                </button>
            </div>
        </div>
    )
}

export default PageNotFound