import React, { useContext, useEffect,useState } from 'react'
import { Link } from 'react-router-dom'
import {Button, Card, CardBody, CardText } from 'reactstrap'
import { getCurrentUserDetail, isLoggedIn } from '../auth'
import userContext from '../context/userContext'

function Item({item={userId:-1,itemName:"this is default item brandName",description:"this is default item desc"}, RemoveItem}) {
  
  // const userContextData = useContext(userContext)


  const[user,setUser]=useState(null)
  const[login,setLogin]=useState(null)
  useEffect(()=>{
    setUser(getCurrentUserDetail())
    setLogin(isLoggedIn())
  },[])


  return (
   <Card className='border-0 shadow-sm mt-3'>
       <CardBody>
           <h1>{item.itemName}</h1>
           <CardText>
             {item.description.substring(0,40)}... 
           </CardText>

           <div>
             <Link className='btn btn-secondary border-0' to={'/items/'+ item.itemID} >Read More</Link>

                
              {/* {isLoggedIn ?  user.userId==item.user.user?<Button color="danger"  className='ms-2'>Delete</Button>:'':''} */}
              {/* {isLoggedIn ?  item.userId==item.userId ?
              <Button color="danger"  className='ms-2'>Delete</Button>:'':''}  */}
                {isLoggedIn && (user && user.userId == user.userId ? 
              <Button onClick={()=>RemoveItem(item)} color="danger"  className='ms-2'>Delete</Button> : '')} 
              

           {/* {userContextData.user.login && (user && user.userId == user.userId ? 
              <Button onClick={()=>RemoveItem(item)} color="danger"  className='ms-2'>Delete</Button> : '')} 
              */}
             {isLoggedIn && (user && user.userId == user.userId ? 
              <Button tag={Link} color="primary" to={`/user/update-item/${item.itemID}`} className='ms-2'>Update</Button> : '')}

               {/* onClick={()=>RemoveItem(item)}  bid-item/:itemID    to={`/user/bid-item/${item.itemID}`}*/}
               {isLoggedIn && (user && user.userId == user.userId ? 
                    <Button tag={Link} color="primary" to={`/user/bid-item/${item.itemID}`}  className='ms-2'>Place Bid</Button> : '')}

           </div>
       </CardBody>
   </Card>
  )
}

export default Item;