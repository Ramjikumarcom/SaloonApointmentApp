import { NotificationsActive } from '@mui/icons-material'
import { Card } from '@mui/material'
import { green } from '@mui/material/colors'
import { space } from 'postcss/lib/list'
import React from 'react'

const NotificationsCard = () => {
    return (
        <Card
            sx={{ bgcolor: "#EAF0F1" }}
            className={`cursor-pointer p-5 flex items-center gap-5`}
        >
            <NotificationsActive sx={{ color: 'green' }}></NotificationsActive>
            <div>
                <p>your booking got confirmed</p>
                <h1 className='space-x-3'>
                    {[1, 1, 1, 1].map((item) => <span>hair cut</span>)}
                </h1>
            </div>
        </Card>
    )
}

export default NotificationsCard