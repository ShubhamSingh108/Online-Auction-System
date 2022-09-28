import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormFeedback, FormGroup, Input, Label, Row } from "reactstrap";
import Base from "../components/Base";
import {signUp} from "../services/user-service";
import {toast} from 'react-toastify';

const Signup = () => {

    const [data,setData]=useState({
        firstName:'',
        lastName:'',
        dob:'',
        email:'',
        password:'',
        address:'',
        city:'',
        state:'',
        country:'',
        pincode:'',


    })

    // useEffect(()=>{
    //     console.log(data)
    // },[data])
    
    const [error,setError]=useState({
        errors:{},
        isError:false
    })

    //handle change
    const handleChange=(event,property)=>{
      
        //dynamic setting the values
        setData({...data,[property]:event.target.value})
    }

    //reseting the form
    const resetData=()=>{
        setData({
        firstName:'',
        lastName:'',
        dob:'',
        email:'',
        password:'',
        address:'',
        city:'',
        state:'',
        country:'',
        pincode:'',
       
        })
    }

    //submitting the form
    const submitForm=(event)=>{
        event.preventDefault()

        //  if(error.isError){
        //       toast.error("Form data is invalid, correct all details then submit")

        //       setError({...error,isError:false})

        //      return;
        //    }

        console.log(data)


       //call server api for sending data
       signUp(data).then((resp) => {
           console.log(resp);
           console.log("success log");
           toast.success("User is registered successfully !!")
           setData({
            firstName:'',
            lastName:'',
            dob:'',
            email:'',
            password:'',
            address:'',
            city:'',
            state:'',
            country:'',
            pincode:'',
           })
       }).catch((error) => {
        console.log(error);
        toast.error("All Fields are mandatory");

        //handle errors in proper way
        setError({
            errors:error,
            isError:true
        })
       })



    }

    return (
        <Base>
       <Container>

           <Row className="mt-4">
          {/* {JSON.stringify(data)}  */}

               <Col sm={{size:6,offset:3}}>
               <Card color="dark"  inverse>
    <CardHeader>
           <h3>New User Registration</h3>
    </CardHeader>
     
    <CardBody>
        <Form onSubmit={submitForm}>
           
            {/* name field*/}
            <FormGroup>
                <Label for="firstName">First Name</Label>
                <Input type="text" name="firstName" id="firstName" placeholder="Enter here" onChange={(e)=>handleChange(e,'firstName')} 
                value={data.firstName}
                invalid={ error.errors?.response?.data?.firstName ? true : false } />

                <FormFeedback>
                { error.errors?.response?.data?.firstName }
                </FormFeedback>
            </FormGroup>

             <FormGroup>
                <Label for="lastName">Last Name</Label>
                <Input type="text" name="lastName" id="lastName" placeholder="Enter here" onChange={(e)=>handleChange(e,'lastName')} value={data.lastName} 
                invalid={ error.errors?.response?.data?.lastName ? true:false } />

               <FormFeedback>
                { error.errors?.response?.data?.lastName }
               </FormFeedback>
            </FormGroup>

             {/* dob field*/}
             <FormGroup>
                <Label for="dob">Date of Birth</Label>
                <Input type="Date"  name="dob" id="dob" placeholder="Enter here" onChange={(e)=>handleChange(e,'dob')} value={data.dob} 
                invalid={ error.errors?.response?.data?.dob ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.dob }
                </FormFeedback>
            </FormGroup>

            <FormGroup>
                <Label for="address">Address</Label>
                <Input type="textarea"  name="address" id="address" placeholder="Enter here" onChange={(e)=>handleChange(e,'address')} value={data.address} 
                invalid={ error.errors?.response?.data?.address ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.address }
                </FormFeedback>
            </FormGroup>

            <FormGroup>
                <Label for="city">City</Label>
                <Input type="text"  name="city" id="city" placeholder="Enter here" onChange={(e)=>handleChange(e,'city')} value={data.city} 
                invalid={ error.errors?.response?.data?.city ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.city }
                </FormFeedback>
            </FormGroup>

            <FormGroup>
                <Label for="state">State</Label>
                <Input type="text"  name="state" id="state" placeholder="Enter here" onChange={(e)=>handleChange(e,'state')} value={data.state} 
                invalid={ error.errors?.response?.data?.dob ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.state }
                </FormFeedback>
            </FormGroup>

            <FormGroup>
                <Label for="country">Country</Label>
                <Input type="text"  name="country" id="country" placeholder="Enter here" onChange={(e)=>handleChange(e,'country')} value={data.country} 
                invalid={ error.errors?.response?.data?.country ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.country }
                </FormFeedback>
            </FormGroup>

            <FormGroup>
                <Label for="pincode">Pincode</Label>
                <Input type="text"  name="pincode" id="pincode" placeholder="Enter here" onChange={(e)=>handleChange(e,'pincode')} value={data.pincode} 
                invalid={ error.errors?.response?.data?.pincode ? true:false }  />

               <FormFeedback>
                { error.errors?.response?.data?.pincode }
                </FormFeedback>
            </FormGroup>

             {/* Email field*/}
             <FormGroup>
                <Label for="email">Email</Label>
                <Input type="email" name= "email"  id= "email" placeholder="Enter here" onChange={(e)=>handleChange(e,'email')} value={data.email} 
                invalid={ error.errors?.response?.data?.email ? true:false } />

               <FormFeedback>
                { error.errors?.response?.data?.email  }
                </FormFeedback>
            </FormGroup>

            {/* password*/}
             <FormGroup>
                <Label for="password">Password</Label>
                <Input type="password" name="password" id= "password" onChange={(e)=>handleChange(e,'password')} value={data.password}
                invalid={ error.errors?.response?.data?.password ? true:false } />

               <FormFeedback>
                { error.errors?.response?.data?.password }
                </FormFeedback>
            </FormGroup>
            <Container className="text-center">
                <Button color="light" outline>Register</Button>
                <Button type="reset" color="secondary" className="ms-2" onClick={resetData}>Reset</Button>
            </Container>
        </Form>
    </CardBody>

</Card>
               </Col>
           </Row>

       </Container>
        </Base>
    );
};

export default Signup;