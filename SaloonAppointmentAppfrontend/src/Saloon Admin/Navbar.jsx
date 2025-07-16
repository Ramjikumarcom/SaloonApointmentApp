import { Menu } from '@mui/icons-material'
import NotificationsActiveIcon from '@mui/icons-material/NotificationsActive';
import Drawer from '@mui/material/Drawer'
import IconButton from '@mui/material/IconButton'
import Badge from '@mui/material/Badge'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const Navbar = ({ DrawerList }) => {
    const [open, setOpen] = useState(false)
    const navigation = useNavigate()
    const toggleDrawer = (newOpen) => () => {
        setOpen(newOpen);
    }

    // Example: You can replace this with props or state later
    const notificationCount = 3; // set to 0 if you don't want a number

    return (
        <div className='h-[10vh] flex items-center justify-between px-5 border-b'>
            <div className='flex items-center gap-3'>
                <IconButton onClick={toggleDrawer(true)}>
                    <Menu color='primary' />
                </IconButton>
                <h1 className='text-xl cursor-pointer font-bold'>Saloon Booking</h1>
            </div>

            {/* Notifications */}
            <IconButton>
                <Badge
                    onClick={
                        () => navigation('/notifications')
                    }
                    badgeContent={notificationCount}
                    color='error'
                    overlap="circular"
                    max={9} // you can customize this
                >
                    <NotificationsActiveIcon sx={{ fontSize: "38px" }} color='success' />
                </Badge>
            </IconButton>

            {/* Drawer */}
            <Drawer open={open} onClose={toggleDrawer(false)}>
                <DrawerList toggleDrawer={toggleDrawer} />
            </Drawer>
        </div>
    )
}

export default Navbar
