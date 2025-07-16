import React from 'react'
import { AccountBalanceWallet, AccountBox, Add, Category, Dashboard, Inventory, Logout, NotificationAddOutlined, Receipt, ShoppingBag } from '@mui/icons-material'
import DrawerList from '../../Saloon Admin/DrawerList'
const menu = [
    {
        name: "Dashboard",
        path: "/saloon-dashboard",
        icon: <Dashboard className='text-green-600'></Dashboard>,
        activeIcon: <Dashboard className='text-white'></Dashboard>
    },
    {
        name: "Bookings",
        path: "/saloon-dashboard/bookings",
        icon: <ShoppingBag className='text-green-600'></ShoppingBag>,
        activeIcon: <ShoppingBag className='text-white'></ShoppingBag>
    },
    {
        name: "Services",
        path: "/saloon-dashboard/services",
        icon: <Inventory className='text-green-600'></Inventory>,
        activeIcon: <Inventory className='text-white'></Inventory>
    },
    {
        name: "Add Services",
        path: "/saloon-dashboard/add-services",
        icon: <Add className='text-green-600'></Add>,
        activeIcon: <Add className='text-white'></Add>
    },
    {
        name: "Payment",
        path: "/saloon-dashboard/payment",
        icon: <AccountBalanceWallet className='text-green-600'></AccountBalanceWallet>,
        activeIcon: <AccountBalanceWallet className='text-white'></AccountBalanceWallet>
    },
    {
        name: "Transaction",
        path: "/saloon-dashboard/transaction",
        icon: <Receipt className='text-green-600'></Receipt>,
        activeIcon: <Receipt className='text-white'></Receipt>
    },
    {
        name: "Category",
        path: "/saloon-dashboard/category",
        icon: <Category className='text-green-600'></Category>,
        activeIcon: <Category className='text-white'></Category>
    },
    {
        name: "Notifications",
        path: "/saloon-dashboard/notifications",
        icon: <NotificationAddOutlined className='text-green-600'></NotificationAddOutlined>,
        activeIcon: <NotificationAddOutlined className='text-white'></NotificationAddOutlined>
    }

]

const menu2 = [
    {
        name: "Account",
        path: "/saloon-dashboard/account",
        icon: <AccountBox className='text-green-600'></AccountBox>,
        activeIcon: <AccountBox className='text-white'></AccountBox>
    },
    {
        name: "Logout",
        path: "/",
        icon: <Logout className='text-green-600'></Logout>,
        activeIcon: <Logout className='text-white'></Logout>
    }
]
const SaloonDrawerList = ({ toggleDrawer }) => {

    return (
        <div>
            <DrawerList menu={menu} menu2={menu2} toggleDrawer={toggleDrawer}></DrawerList>
        </div>
    )
}

export default SaloonDrawerList