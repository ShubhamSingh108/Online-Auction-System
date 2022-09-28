import { useEffect,useState } from "react"
import { Card, CardBody, Input, Form, Label, option, Container, Button} from "reactstrap"
import { loadAllCategories } from "../services/item-service"
import '../form.css';
import { toast } from "react-toastify";
import { createItem as docreateItem } from "../services/item-post-service";
import { getCurrentUserDetail } from "../auth";
import {uploadItemImage} from "../services/item-post-service";

const AddProduct=()=>{

   // const [categories,setCategories] = useState([])
    const [user, setUser] = useState('')

    const [item,setItem] = useState({
   // itemID:'',
	itemName:'',
	description:'',
	purchaseDate:'',
	price:'',
    category:'',
    status:'',
    userId:''

})



//image
 const [image, setImage]=useState(null) 



    //no need of authentication , we are allowed all get api
      useEffect(
        ()=>{
            setUser(getCurrentUserDetail())
       loadAllCategories().then((data)=>{
            console.log(data)
            //set data in category so all category array will come
           // setCategories(data)
        }).catch(error=>{
            console.log(error)
        })
     },
         []
     ) 

    //field changed function
    const fieldChanged = (event,property) => {
  //console.log(event.target.value)

  setItem({...item,[property]:event.target.value})
    }




    //create item func
    const createItem=(event)=>{
//stop the default behaviour of form
        event.preventDefault();

        //console.log(item)
    
        if (item.itemName.trim() === '') {
            toast.error("Item Name is required !!")
            return;
        }

        // if (item.brandName.trim() === '') {
        //     toast.error("Brand Name is required !!")
        //     return;
        // }
        // if (item.purchaseDate.trim() === '') {
        //     toast.error("Purchase Date is required !!")
        //     return
        // }
     /*    if (item.expiryDate.trim() === '') {
            toast.error("Expiry Date is required !!")
            return;
        }
        if (item.minPrice.trim() === '') {
            toast.error("Price is required !!")
            return
        }
        */

        if (item.category === '') {
            toast.error("select some category !!")
            return;
        }



        
        //submit the form one server
        item['userId'] = user.userId
        docreateItem(item).then(data=>{

            toast.success("item created")
            console.log(item)
        
            uploadItemImage(image,data.itemID).then(data=>{
                console.log("step4"+data.itemID)
                toast.success("image uploaded !!")
            }).catch((error)=>{
                console.log("step"+data.itemID)
                toast.error("Error in uploading Image") 
                console.log(error)
            }) 


         
        }).catch((error)=>{
            toast.error("error")
            console.log(error)

        })
}

     //handing file change event
     const handleFileChange=(event)=>{
         console.log(event.target.files[0]);
         setImage(event.target.files[0])
         console.log("1step")
     }

    return (
        
        <div className="box" >

         <Card className="shadow p-3 mb-5 rounded border-0 mt-2 example mt-4" >
             <CardBody >
             {/* {JSON.stringify(item)} */}
             <h3>Add New Item</h3>
                 <Form onSubmit={createItem}>
                     <div classname="my-3">
                         <Label for="item">Item Name</Label>
                        <Input type="text" id="item" placeholder="Enter here" name="item"
                         onChange={(e)=>fieldChanged(e,'itemName')} value={item.itemName} />
                    </div> 
                    <div classname="my-3">
                         <Label for="desc">Description</Label>
                        <Input type="textarea" id="desc" name="desc" placeholder="Enter here"
                        onChange={(e)=>fieldChanged(e,'description')} value={item.description}/>
                    </div> 
                    <div classname="my-3">
                         <Label for="price">Price</Label>
                        <Input type="number" id="price" name="price" placeholder="Enter here"
                        onChange={(e)=>fieldChanged(e,'price')} value={item.price}/>
                    </div>
                    <div classname="my-3">
                         <Label for="purchase">Purchasing date</Label>
                        <Input type="date" id="purchase" name="purchase" placeholder="Enter here"
                         onChange={(e)=>fieldChanged(e,'purchaseDate')} value={item.purchaseDate}/>
                    </div>
                    

                    <div classname="my-3">
                         <Label for="category">Category</Label>
                        <Input type="select" id="category" name="category"
                         onChange={(e)=>fieldChanged(e,'category')} value={item.category}
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

                     <div classname="my-3">
                         <Label for="image">Select Image</Label>
                        <Input type="file" id="image" 
                         onChange={handleFileChange} />
                    </div> 


              

                    
                    <Container className="text-center mt-2" >
                        <Button type="submit" className="rounded-0" color="warning">ADD ITEM NOW</Button>
                        <Button type="reset" className="rounded-0 ms-2" color="danger">RESET ITEM</Button>
                    </Container>
                    





                   {/*  <section>
     <div class="add-product-form">
        <header class="main-header">Add new product</header>
        <div class="data">
            <label for="name">Name:</label>
            <input type="text" id="name" placeholder="Item name..."/>
            <label for="category">Category:</label>
            <input type="text" id="category" placeholder="Category..."/>
            <label for="price">Price:</label>
            <input type="text" id="price" placeholder="0.00"/>
        </div>
        <div class="panel">
            <button id="add-product-button" class="button">Add</button>
            <a href="#/products">
                <button class="button" id="cancel-button">Cancel</button>
            </a>
        </div>
    </div>
</section> */}
                 </Form>
             </CardBody>
         </Card>

        </div>
    )
}

export default AddProduct