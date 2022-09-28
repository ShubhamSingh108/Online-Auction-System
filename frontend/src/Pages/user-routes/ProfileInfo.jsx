import React, { useEffect, useState } from 'react'
import { getCurrentUserDetail } from '../../auth'
import Base from '../../components/Base'
import userContext from '../../context/userContext'

const ProfileInfo=()=> {
  
const[user,setUser]=useState({})

     useEffect(()=>{
 console.log(getCurrentUserDetail())
 setUser(getCurrentUserDetail())


      },[])
  return (
    <Base>
      <h1 >Welcome {user.firstName} {user.lastName}</h1>
    </Base>
  )
}

export default ProfileInfo