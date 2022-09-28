//authenticate --> isLoggedIn
export const isLoggedIn=()=>{
   let data = localStorage.getItem("data");
   if(data != null)
       return true;
    else
       return false;
   
}


//doLogin=> data=>set to localstorage
export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data));
    next();
}

//next-callback  -->after logout if it wants to redirect

//doLogout=> remove data from localstorage
export const doLogout=(next)=>{
    localStorage.removeItem("data")
      next()
    //redirect to user dashboard page
}



//get currentUser
export const getCurrentUserDetail=()=>{
    if(isLoggedIn()){
        //data-> user+token
        //.user -->after parsing  user return not token
        return JSON.parse(localStorage.getItem("data")).user;
    }else{
        return false;
    }
}


// making a  method for accessing token
export const getToken=()=>{
  if(isLoggedIn()){
      return JSON.parse(localStorage.getItem("data")).token
  }else{
      return null;
  }
}