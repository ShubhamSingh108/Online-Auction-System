import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { toast } from "react-toastify"
import { Card, CardBody, Col, Container, Row,CardText, Button } from "reactstrap"
import Base from "../components/Base"
import { loadItem } from "../services/item-post-service"
import { BASE_URL } from "../services/helper"
import { getCurrentUserDetail, isLoggedIn } from "../auth"
const ItemPage=()=>{

    const {itemID}=useParams()
    const [item,setItem]=useState(null)
    useEffect(()=>{
        //load item of itemID
      loadItem(itemID).then(data=>{
          console.log(data);
          setItem(data)
      }).catch(error=>{
          console.log(error);
          toast.error("Error in loading part")
      })
    },[])

    const[user,setUser]=useState(null)
    const[login,setLogin]=useState(null)
    useEffect(()=>{
      setUser(getCurrentUserDetail())
      setLogin(isLoggedIn())
    },[])

    //date function
    const printDate=(numbers)=>{

        return new Date(numbers).toLocaleDateString()
    }

    return(
      <Base>
      <Container className="mt-4">
          <Link to="/">Home</Link>

          <Row>
              <Col md={{
                  size:12
              }}>



                  <Card className="mt-3 ps-4">
                     {
                         (item) && (
                            <CardBody>
                            <CardText>
                                   {/* Posted by <b>{item.user.id}</b>on <b>{new Date}</b> */}
                                   Purchasing date: <b>{ printDate(item.purchaseDate)}</b>
                            </CardText> 
                            <CardText>
                               <h4> <span className="text-muted">{item.category}</span></h4>
                            </CardText>
                            <CardText className="mt-3">
                                <h1>{item.itemName}</h1>
                                <h5>Rs.{item.price}</h5>
                            </CardText>


                            <div className="divder" style={{
                                            width: '100%',
                                            height: '1px',
                                            background: '#e2e2e2'

                                        }}></div>


                           <div className="image-container mt-3 shadow " style={{ maxWidth: '50%' }}>
                           <img className="img-fluid" src={BASE_URL+'/api/items/image/'+item.imageURL} alt="" />
                          
                           </div>

                           <CardText className="mt-2" dangerouslySetInnerHTML={{ __html: item.description }}>
                     
                            </CardText>
                        </CardBody>
                         )
                     }
                  </Card>
                  {/* <h1>load the item from database</h1> */}
              </Col>
          </Row>
          <Row className="mt-4">
              <Col  md={
                {
                    size:9,
                    offset:1
                }
              }>
                
              
              </Col>
          </Row>
      </Container>
      </Base>
    )
}

export default ItemPage