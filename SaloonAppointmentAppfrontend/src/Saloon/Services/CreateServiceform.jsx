import { AddPhotoAlternate, Close } from '@mui/icons-material'
import { Button, CircularProgress, FormControl, Grid, IconButton, InputLabel, MenuItem, Select, TextField } from '@mui/material'
import { useFormik } from 'formik'
import React from 'react'

const CreateServiceform = () => {
    const formik = useFormik({
        initialValues: {
            name: "",
            image: "",
            category: "",
            description: "",
            price: "",
            duration: ""
        },
        onSubmit: () => {
            console.log("formik data", formik.values);
        }
    })

    return (
        <div className='flex justify-center items-center'>
            <form onSubmit={formik.handleSubmit} action="" className='space-y-4 p-4 w-full lg:w:1/2'>
                <Grid container spacing={2}>
                    <Grid item xs={12} className='w-24 h-24'>
                        {false ? (
                            <div className='relative border w-[50vh]'>
                                <img
                                    className='w-30 h-30 object-cover'
                                    src="https://images.pexels.com/photos/32835382/pexels-photo-32835382.jpeg"
                                    alt=""
                                />
                                <IconButton
                                    color='error'
                                    size='small'
                                    sx={{
                                        position: "absolute",
                                        top: 0,
                                        right: 0
                                    }}
                                >
                                    <Close sx={{ fontSize: "1rem" }} />
                                </IconButton>
                            </div>
                        ) : (
                            <>
                                <input
                                    type='file'
                                    accept='image/*'
                                    id='fileInput'
                                    style={{ display: "none" }}
                                />
                                <label htmlFor="fileInput" className='cursor-pointer'>
                                    <span className='w-24 h-24 flex items-center justify-center p-3 border rounded-md border-gray-400'>
                                        <AddPhotoAlternate className='text-gray-700' />
                                    </span>
                                </label>

                                {false && (
                                    <div className='absolute left-0 right-0 bottom-0 w-24 h-24 flex justify-center items-center'>
                                        <CircularProgress />
                                    </div>
                                )}
                            </>
                        )}
                    </Grid>


                    <Grid size={12}>
                        <TextField fullWidth id='name' name='name' label='name'
                            value={formik.values.name}
                            onChange={formik.handleChange}
                            required />
                    </Grid>
                    <Grid size={12}>
                        <TextField fullWidth id='description' name='description' label='description'
                            value={formik.values.description}
                            multiline
                            rows={4}
                            onChange={formik.handleChange}
                            required />
                    </Grid>
                    <Grid size={{ xs: 12, sm: 6 }}>
                        <TextField fullWidth id='price' name='price' label='price'
                            value={formik.values.price}
                            onChange={formik.handleChange}
                            required />
                    </Grid>
                    <Grid size={{ xs: 12, sm: 6 }}>
                        <TextField fullWidth id='duration' name='duration' label='duration'
                            value={formik.values.duration}
                            onChange={formik.handleChange}
                            required />
                    </Grid>
                    <Grid size={12}>
                        {/* <TextField fullWidth id='category' name='category' label='category'
                          
                            
                            required /> */}


                        <FormControl fullWidth>
                            <InputLabel id="category">Category</InputLabel>
                            <Select
                                labelId="category"
                                id="category"
                                value={formik.values.category}
                                label="category"
                                name='category'
                                onChange={formik.handleChange}
                            >
                                {[1, 1, 1, 1, 1].map(() => <MenuItem value={10}>HairCut</MenuItem>)}

                            </Select>
                        </FormControl>
                    </Grid>




                    <Grid size={12}>
                        <Button type='submit' variant='contained'>Create Services</Button>
                    </Grid>
                </Grid>
            </form>
        </div>
    )
}

export default CreateServiceform
