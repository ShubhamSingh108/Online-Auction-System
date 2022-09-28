import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { toast } from 'react-toastify'
import Base from '../components/Base'
import userContext from '../context/userContext'
import { loadItem, updateItem as doUpdateItem} from '../services/item-post-service'
import { Card, CardBody, Input, Form, Label, option, Container, Button} from "reactstrap"
import { getCurrentUserDetail } from '../auth'

function UpdateItem() {

     // to retrieving values
     const { itemID } = useParams()
     const object=useContext(userContext)
     const navigate=useNavigate()

     //to save info of item using useState
    const[item,setItem]= useState(null)

//     const[user,setUser]=useState({})

//      useEffect(()=>{
//  console.log(getCurrentUserDetail())
//  setUser(getCurrentUserDetail())


//      },[])





     useEffect(()=>{
        //load the item from database
        loadItem(itemID).then(data=>{
            setItem({...data})
        }).catch(error=>{
            console.log(error);
            toast.error("error in loading the item")
        })
     },[])

     /*  useEffect(()=>{
        if (item.itemId != object.user.data.userId) {
            document.write("----------FAILURE-------")
           toast.error("This is not your item !!")
          // navigate("/")
       }
     },[item])  */


    //   useEffect(() => {
    //     console.log("first")
    //     if (item) {
    //         //post.user.id != object.user.data.id item.itemID !=user.itemID user.userId == 3
    //         if (user.userId == user.userId.itemID) {
    //             toast.error("This is not your item !!")
    //             navigate("/")
    //         }

    //      }

    //  }, [item]) 
    const handleChange=(event,fieldName)=>{

        setItem(
            {
                ...item,
                [fieldName]:event.target.value
            }
        )
    }

  
    const updateItem = (event) => {
        event.preventDefault()
        console.log(item)
        doUpdateItem({ ...item}, item.itemID)
            .then(res => {
                console.log(res)
                toast.success("item updated")
            })
            .catch(error => {
                console.log(error);
                toast.error("Error in upading item")
            })

    }

    const updateHtml=()=>{
        return(
            <div className="box" >

            <Card className="shadow p-3 mb-5 rounded border-0 mt-2 example mt-4" >
                <CardBody >
                 {/* {JSON.stringify(item)}  */}
                <h3>Update Item</h3>
                    <Form onSubmit={updateItem}>
                        <div classname="my-3">
                            <Label for="item">Item Name</Label>
                           <Input type="text" id="item" placeholder="Enter here" name="item"
                            onChange={(event)=>handleChange(event,'itemName')} 
                          value={item.itemName} 
                          />
                       </div> 
                       <div classname="my-3">
                            <Label for="desc">Description</Label>
                           <Input type="textarea" id="desc" name="desc" placeholder="Enter here"
                           onChange={(event)=>handleChange(event,'description')} 
                          value={item.description}
                           />
                       </div> 
                       <div classname="my-3">
                            <Label for="price">Price</Label>
                           <Input type="number" id="price" name="price" placeholder="Enter here"
                           onChange={(event)=>handleChange(event,'price')} 
                          value={item.price}
                          />
                       </div>
                       <div classname="my-3">
                            <Label for="purchase">Purchasing date</Label>
                           <Input type="date" id="purchase" name="purchase" placeholder="Enter here"
                            onChange={(event)=>handleChange(event,'purchaseDate')} 
                            value={item.purchaseDate}
                            />
                       </div>
                       <div classname="my-3">
                            <Label for="category">Category</Label>
                           <Input type="select" id="category" name="category"
                            onChange={(event)=>handleChange(event,'category')} 
                           value={item.category}
                            defaultValue={-1}>
   
                         <option value={-1} >--Select category--</option>
                               <option>COIN</option>
                               <option>PAINTING</option>
                               <option>WATCH</option>
                               <option>OTHERS</option> 
   
                            {/* dynamic category */}
                             {/* {
                                categories.map((category) => (
                                    <option value={category.itemID} key={category.itemID}>
                                        {category.category}
                                    </option>
                                ))
                            }  */} 
                           </Input>
                       </div>  
   
                       {/* file field */}
                        {/* <div classname="my-3">
                            <Label for="image">Select Image</Label>
                           <Input type="file" id="image" name="image" 
                            onChange={(e)=>fieldChanged(e,'imageURL')} value={item.imageURL}/>
                       </div>  */} 
   
                      {/*   <div classname="my-3">
                            <Label for="image">Select Image</Label>
                           <Input type="file" id="image" 
                           // onChange={handleFileChange} 
                            />
                       </div>  */}
   
   
                 
   
                       
                       <Container className="text-center mt-2" >
                           <Button type="submit" className="rounded-0" color="warning">Update Item</Button>
                           <Button type="reset" className="rounded-0 ms-2" color="danger">RESET ITEM</Button>
                       </Container>
                       

                    </Form>
                </CardBody>
            </Card>
   
           </div>
        )
    }

  return (
    <Base>
  <Container>
  { item && updateHtml()}
  </Container>

    </Base>
  )
}

export default UpdateItem