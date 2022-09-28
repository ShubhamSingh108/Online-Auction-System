import React, { useState, useEffect} from 'react'
import { Card,CardBody, Form, Input, Label,Container,Button } from 'reactstrap'
import { getCurrentUserDetail } from '../auth'
import { CreateEventPost } from '../services/Bid-service'
import { toast } from "react-toastify";

const PlaceBid=()=> {


  const [user,setUser]=useState(undefined)
  const [eventitem,setEventItem]=useState({
    bidAmount:''
  })

  useEffect(()=>{
    console.log(getCurrentUserDetail())
    setUser(getCurrentUserDetail())
   
   },[])
  

//field changed function 
const fieldChanged=(event)=>{
 // console.log(event.target.value)
  setEventItem({...eventitem,[event.target.name]:event.target.value})
}



 const createItemEvent=(event)=>{
   
  event.preventDefault();
  //console.log("payment successfully Submitted")
  console.log(eventitem)
  if(eventitem.bidAmount.trim()=== ''){
    alert("BidAmount is required !!!")
    return;
  }

  //submit
  eventitem['userId']=user.userId
  CreateEventPost(eventitem).then(data=>{
    alert("post created")
    console.log(eventitem)
    toast.success("Congrats Bid Successfull")
    
  }).catch((error)=>{
    //alert("error in event")
    console.log(error )
    
    
  })
 }


  return (
    <div className="wrapper">
    <Card>
      <CardBody>
      {/* {JSON.stringify(eventitem)} */}
        <h1>Bidding</h1>
        <Form  onSubmit={createItemEvent}>
          <div className='my-3'>
            <Label for="bidAmount">Bid Amount</Label>
            <Input type="number" id="bidAmount"  name="bidAmount" placeholder='Enter Bid Amount' className="rounded-0"
            onChange={fieldChanged}/>
          </div>

          
          <Container className="text-center">
            <Button type="submit" className="rounded-0" color="primary">Submit Payment</Button>
          </Container>
        </Form>
      </CardBody>
    </Card>
      </div>
  )
}

export default PlaceBid