import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const DrawerList = ({ menu, menu2, toggleDrawer }) => {
    const navigate = useNavigate();
    const location = useLocation();

    return (
        <div className='h-full pl-5'>
            <div className='flex flex-col justify-between h-full w-[260px] border-r py-6'>

                {/* Top Menu */}
                <div className='space-y-2 pr-3'>
                    {menu.map((item, index) => {
                        const isActive = location.pathname === item.path;
                        return (
                            <div
                                key={index}
                                onClick={() => {
                                    navigate(item.path)
                                    if (toggleDrawer) toggleDrawer(false)();
                                }
                                }
                                className={`cursor-pointer flex items-center gap-3 px-5 py-3 rounded-md transition-all duration-200
                                    ${isActive ? 'bg-green-500 text-white shadow' : 'bg-gray-100 text-gray-700 hover:bg-gray-200'}
                                `}
                            >
                                <div className='text-xl'>
                                    {isActive ? item.activeIcon : item.icon}
                                </div>
                                <ListItem disableGutters className='p-0 m-0'>
                                    <ListItemText
                                        primary={item.name}
                                        primaryTypographyProps={{ className: 'truncate text-sm' }}
                                    />
                                </ListItem>
                            </div>
                        );
                    })}
                </div>


                <div className='space-y-5'>
                    <Divider />
                    <div className='space-y-2 pr-3'>
                        {menu2.map((item, index) => {
                            const isActive = location.pathname === item.path;
                            return (
                                <div
                                    key={index}
                                    onClick={() => {
                                        navigate(item.path)
                                        if (toggleDrawer) toggleDrawer(false)();
                                    }
                                    }
                                    className={`cursor-pointer flex items-center gap-3 px-5 py-3 rounded-md transition-all duration-200
                                    ${isActive ? 'bg-green-500 text-white shadow' : 'bg-gray-100 text-gray-700 hover:bg-gray-200'}
                                `}
                                >
                                    <div className='text-xl'>
                                        {isActive ? item.activeIcon : item.icon}
                                    </div>
                                    <ListItem disableGutters className='p-0 m-0'>
                                        <ListItemText
                                            primary={item.name}
                                            primaryTypographyProps={{ className: 'truncate text-sm' }}
                                        />
                                    </ListItem>
                                </div>
                            );
                        })}
                    </div>
                </div>


                {/* Bottom Menu */}


            </div>
        </div>
    );
};

export default DrawerList;
