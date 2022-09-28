import CustomNavbar from "./CustomNavbar";

const Base = ({ title = "Welcome to our website", children }) => {
  
  return( <div classname = "container-fluid"> 
   <CustomNavbar />

   {children}

  </div>
   );  
};
 
export default Base;