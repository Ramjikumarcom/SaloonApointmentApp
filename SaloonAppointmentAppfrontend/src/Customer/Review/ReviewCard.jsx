import React from 'react'
import { Avatar, Grid, Box, Rating, IconButton } from '@mui/material'
import { Delete } from '@mui/icons-material'
import { red } from '@mui/material/colors'

const ReviewCard = () => {
    return (
        <div className='p-10 pl-15 flex justify-between'>

            <div className='w-[80%]'>
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs={2}>
                        <Avatar sx={{ width: 56, height: 56, bgcolor: "#9155FD" }}>
                            A
                        </Avatar>
                    </Grid>

                    <Grid item xs={10}>
                        <div className='space-y-2'>
                            <p className='font-semibold text-lg'>Ramji Kumar</p>
                            <p className='opacity-70'>2024-12-01T09:51:18.321553</p>
                        </div>

                        <Rating
                            readOnly
                            value={4.5}
                            name='half-rating'
                            precision={0.5}
                        />
                        <p>This saloon is provided great services</p>
                    </Grid>
                </Grid>
            </div>

            <div className='w-[20%] flex items-start justify-end'>
                <IconButton>
                    <Delete sx={{ color: red[500] }} />
                </IconButton>
            </div>
        </div>
    )
}

export default ReviewCard
