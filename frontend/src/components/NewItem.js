import React, { useState } from 'react'
import { useEffect } from 'react'
import { Row, Col,Pagination, PaginationItem, PaginationLink,Container } from 'reactstrap'
import { loadAllItems, loadItembyUserId } from '../services/item-post-service'
import Item from './Item'
function NewItem() {

    const[itemContent,setItemContent] = useState(null)




    useEffect(()=>{
        //load all the posts from the server
        loadAllItems().then((data)=>{
            console.log(data);
            setItemContent(data)
        }).catch(error=>{
            console.log(error)
        })
       },[])

  return (
   <div className="container-fluid">
       <Row >
           <Col md={
               {
                   size:10,
                   offset:1
               }
           }>
               {/* <h1>Items Count {itemContent?.totalElements}</h1> */}
               <h1>Antique Items</h1>
               {
                //    itemContent.brandName.map((item)=>(
                //     <Item item={item} />
                //    ))
                itemContent?.map((item)=>(
                    <Item item={item} key={item.itemID}
                    />
                ))
               }
              <Container className='mt-3'>
              <Pagination>
                   <PaginationItem>
                       <PaginationLink previous>

                       </PaginationLink>
                       
                   </PaginationItem>
                   
                   <PaginationItem>
                       <PaginationLink >
                         1
                       </PaginationLink>
                       
                   </PaginationItem>

                   <PaginationItem>
                       <PaginationLink next>

                       </PaginationLink>
                   </PaginationItem>
               </Pagination>
              </Container>

               {/* <Item /> */}
           </Col>
       </Row>
   </div>
   )
}

export default NewItem