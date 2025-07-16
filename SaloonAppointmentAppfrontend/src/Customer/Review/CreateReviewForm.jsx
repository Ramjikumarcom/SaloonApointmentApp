import { Box, Button, InputLabel, Rating, TextField } from '@mui/material'
import { useFormik } from 'formik'
import React from 'react'

const CreateReviewForm = () => {
    const formik = useFormik({
        initialValues: {
            reviewText: "",
            reviewRating: 0
        },
        onSubmit: (values) => {
            console.log("sibmitting values", values);
        }

    })
    return (
        <Box
            onSubmit={formik.handleSubmit}
            sx={{ mt: 3 }}
            component={"form"}
            className='space-y-5 w-full lg:w-[0.5]'

        >
            <TextField
                // fullWidth
                id='reviewText'
                name='reviewText'
                label="Review"
                variant='outlined'
                multiline
                value={formik.values.reviewText}
                onChange={formik.handleChange}
                sx={{ width: 556 }}
                // role={4}
                rows={5}
            >

            </TextField>

            <div className='space-y-2'>
                <InputLabel>Rating</InputLabel>
                <Rating
                    id="reviewRating"
                    name='reviewRating'

                    value={formik.values.reviewRating}
                    onChange={(event, newValue) => formik.setFieldValue("reviewRating", newValue)}
                    precision={0.5}
                ></Rating>
            </div>
            <Button variant='contained' type='submit'>Submit Review</Button>
        </Box>
    )
}

export default CreateReviewForm