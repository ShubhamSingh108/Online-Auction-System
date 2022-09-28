import logo from './logo.svg';
import './App.css';
import Base from './components/Base';
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Home from './Pages/Home';
import Login from './Pages/Login';
import Signup from './Pages/Signup';
import About from './Pages/About';
import Services from './Pages/Services';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Userdashboard from './Pages/user-routes/Userdashboard';
import Admindashboard from './Pages/user-routes/Userdashboard';
import Privateroute from './components/Privateroute';
import ProfileInfo from './Pages/user-routes/ProfileInfo';
import ItemPage from './Pages/ItemPage';
import UpdateItem from './Pages/UpdateItem';
import PlaceBid from './Pages/PlaceBid';


function App() {
  return (
 <BrowserRouter>
 <ToastContainer />
 <Routes>

<Route  path="/" element={<Home />}/>
<Route  path="/login" element={<Login />}/>
<Route  path="/signup" element={<Signup />}/>
<Route  path="/about" element={<About />}/>
<Route  path="/services" element={<Services />}/>
<Route  path="/items/:itemID" element={<ItemPage />}/>


{/* private */}
<Route  path="/user" element={<Privateroute />}>
<Route  path="dashboard" element={<Userdashboard />}/>
<Route  path="profile-info" element={<ProfileInfo />}/>
<Route  path="update-item/:itemID" element={<UpdateItem />}/>
<Route  path="bid-item/:itemID" element={<PlaceBid />}/>
</Route>

<Route  path="/admin" element={<Privateroute />}>
<Route  path="dashboard" element={<Admindashboard />}/>
<Route  path="profile-info" element={<ProfileInfo />}/>

</Route>

 </Routes>
 </BrowserRouter>
  )
}

export default App;
