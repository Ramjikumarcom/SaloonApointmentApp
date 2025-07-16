import { Box, Grid, LinearProgress, Rating } from '@mui/material'
import React from 'react'

const RatingCard = () => {
    return (
        <div className='border p-10 rounded-md'>

            <div className='flex items-center space-x-3 pb-10'>
                <Rating
                    readOnly
                    value={4.5}
                    defaultValue={4.5}
                    precision={0.5}
                    name='half-rating'
                >

                </Rating>
                <p className='opacity-60'>23405</p>
            </div>

            <Box>
                <Grid container justifyContent={"center"} alignItems={"center"}>
                    <Grid size={2}>
                        <p>Excellent</p>
                    </Grid>

                    <Grid size={7}>
                        <LinearProgress sx={{ bg: "#d0d0d0", height: 7, borderRadius: 4 }} variant='determinate' value={40} color='success'
                        ></LinearProgress>
                    </Grid>
                    <Grid size={2} >
                        <p className='opacity-50 p-2'>12776</p>
                    </Grid>

                </Grid>

                <Grid container justifyContent={"center"} alignItems={"center"}>
                    <Grid size={2}>
                        <p>Very Good</p>
                    </Grid>

                    <Grid size={7}>
                        <LinearProgress sx={{ bg: "#d0d0d0", height: 7, borderRadius: 4 }}
                            variant='determinate' value={50} color='success'
                        ></LinearProgress>
                    </Grid>
                    <Grid size={2} >
                        <p className='opacity-50 p-2'>12776</p>
                    </Grid>

                </Grid>

                <Grid container justifyContent={"center"} alignItems={"center"}>
                    <Grid size={2}>
                        <p>Good</p>
                    </Grid>

                    <Grid size={7}>
                        <LinearProgress sx={{ bg: "#d0d0d0", height: 7, borderRadius: 4 }}
                            variant='determinate' value={30} color='success'
                        ></LinearProgress>
                    </Grid>
                    <Grid size={2} >
                        <p className='opacity-50 p-2'>12776</p>
                    </Grid>

                </Grid>


                <Grid container justifyContent={"center"} alignItems={"center"}>
                    <Grid size={2}>
                        <p>Average</p>
                    </Grid>

                    <Grid size={7}>
                        <LinearProgress sx={{ bg: "#d0d0d0", height: 7, borderRadius: 4 }}
                            variant='determinate' value={40} color='warning'
                        ></LinearProgress>
                    </Grid>
                    <Grid size={2} >
                        <p className='opacity-50 p-2'>12776</p>
                    </Grid>

                </Grid>



                <Grid container justifyContent={"center"} alignItems={"center"}>
                    <Grid size={2}>
                        <p>Poor</p>
                    </Grid>

                    <Grid size={7}>
                        <LinearProgress sx={{ bg: "#d0d0d0", height: 7, borderRadius: 4 }}
                            variant='determinate' value={40} color='error'
                        ></LinearProgress>
                    </Grid>
                    <Grid size={2} >
                        <p className='opacity-50 p-2'>12776</p>
                    </Grid>

                </Grid>
            </Box>


        </div>
    )
}

export default RatingCard