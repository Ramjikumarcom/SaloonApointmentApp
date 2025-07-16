import { AccountCircle, NotificationsActive } from '@mui/icons-material'
import { Badge, IconButton, Button, Icon, Avatar, Menu, MenuItem } from '@mui/material'
import React from 'react'
import { Fade } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {

    const [anchorEl, setAnchorEl] = React.useState(null); // ✅ valid in .jsx
    const navigations = useNavigate();
    const open = Boolean(anchorEl);
    const handleClick = (anchorEl) => {
        setAnchorEl(anchorEl.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return (
        <div className='z-50 px-6 flex items-center justify-between py-5'>
            <div className="flex items-center gap-10">
                <h1 onClick={() => navigations("/")} className='cursor-pointer font-bold text-2xl'>TANGO</h1>
                <div className='flex items-center'>
                    <h1 onClick={() => navigations("/")} className='cursor-pointer  text-2xl font-semibold'>Home</h1>

                </div>
            </div>

            <div className='flex items-center gap-3 md:gap6 '>
                <Button variant='contained' >
                    Add Saloon
                </Button>
                <IconButton>
                    <Badge onClick={() => navigations("/notifications")} badgeContent={5} color='error' >
                        <NotificationsActive color='success'></NotificationsActive>
                    </Badge>
                </IconButton>
                {true ? <div className='flex gap-1 items-center'>
                    <h1 className='text-lg font-semibold'>@Ram</h1>

                    <IconButton
                        id="fade-button"
                        aria-controls={open ? 'fade-menu' : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? 'true' : undefined}
                        onClick={handleClick}
                    >
                        <Avatar sx={{ bgcolor: "green" }}>A</Avatar>

                    </IconButton>
                    <Menu
                        id="fade-menu"
                        slotProps={{
                            list: {
                                'aria-labelledby': 'fade-button',
                            },
                        }}
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        TransitionComponent={Fade}
                    >
                        <MenuItem onClick={handleClose}>Profile</MenuItem>
                        <MenuItem onClick={() => {
                            navigations("/bookings")
                            handleClose()
                        }}>My Booking</MenuItem>
                        <MenuItem onClick={handleClose}>Logout</MenuItem>
                    </Menu>
                </div> :
                    <IconButton>
                        <AccountCircle sx={{ fontSize: '45px', color: 'green' }}></AccountCircle>
                    </IconButton>}


            </div>


        </div>
    )
}

export default Navbar