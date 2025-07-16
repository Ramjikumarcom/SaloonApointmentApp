import { ThemeProvider } from '@mui/material';
import './App.css'
import greenTheme from './theme/greenTheme';
import Home from './Customer/Home/Home';
import SaloonDetails from './Customer/Saloon/SaloonDetails/SaloonDetails';
import Booking from './Customer/Booking/Booking';
import Notifications from './Customer/Notifications/Notifications';
import Navbar from './Customer/Navbar/Navbar';
import { Route, Routes } from 'react-router-dom';
import SaloonDashboard from './Saloon/SaloonDashboard';
import CustomerRoutes from './RoutesFile/CustomerRoutes';
function App() {

  return (
    <>
      <ThemeProvider theme={greenTheme}>
        {/* <Navbar></Navbar> */}
        {/* <Home /> */}
        {/* <SaloonDetails /> */}
        {/* <Booking></Booking> */}
        {/* <Notifications></Notifications> */}
        <Routes>
          <Route path='/saloon-dashboard/*' element={<SaloonDashboard></SaloonDashboard>} />
          <Route path='*' element={<CustomerRoutes></CustomerRoutes>} />


        </Routes>
      </ThemeProvider>


    </>
  )
}

export default App
