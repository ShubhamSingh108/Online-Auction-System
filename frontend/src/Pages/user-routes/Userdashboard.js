import React, { useEffect, useState } from "react"
import { toast } from "react-toastify";
import { Container } from "reactstrap";
import { getCurrentUserDetail } from "../../auth";
import AddItem from "../../components/AddItem";
import Base from "../../components/Base";
import Item from "../../components/Item";
import { deleteItemService, loadItemUserWise } from "../../services/item-post-service";

const Userdashboard=()=>{


    const [user,setUser] = useState({})
    const [items,setItems] = useState([])


    useEffect(()=>{
        console.log(getCurrentUserDetail());
        setUser(getCurrentUserDetail())

        loadItemData();
},[])

function loadItemData(){
    loadItemUserWise(getCurrentUserDetail().userId).then(data=>{
        console.log(data)
        setItems([...data])
    }).catch(error=>{
        console.log(error)
        toast.error("error in loading user items")
    })
}

//function to delete item
const RemoveItem=(item)=>{
    //going to delete item
    console.log(item)
    deleteItemService(item.itemID).then(res=>{
        console.log(res)
        toast.success("post is deleted")
    }).catch(error=>{
        console.log(error)
        toast.error("Error in deleting item")
    })
}

    return(
       <Base>
       <Container>
       <AddItem />
       <h1 className="my-3">MyItems TotalCount : ({items.length})</h1>

       {items.map((item,index)=>{
           return (
            <Item item={item} key={index} RemoveItem={RemoveItem}/>
           )
      
       })

       }
       </Container>
       </Base>
    )
}

export default Userdashboard;