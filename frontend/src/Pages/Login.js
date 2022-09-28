import { useContext, useState } from "react";
import { toast } from "react-toastify";
import { Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row, Button } from "reactstrap";
import Base from "../components/Base";
import { loginUser } from "../services/user-service";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";
import userContext from "../context/userContext";


const Login = () => {

  // const userContextData =useContext(userContext)
  //redirect to userdashboard
  const navigate = useNavigate()


  const [loginDetail, setLoginDetail] = useState({
    username: '',
    password: '',
  });


 const handleChange=(event,field)=>{
  let actualValue = event.target.value;
  setLoginDetail({
    ...loginDetail,
    [field]: actualValue
  });
 }

 //after login reseting the field
 const handleReset = () => {
  setLoginDetail({
    username: '',
    password: '',
  });
};

 const handleFormSubmit=(event)=>{
   event.preventDefault();
   console.log(loginDetail);

   //validation
   if (
     loginDetail.username.trim() == "" || 
     loginDetail.password.trim() == ""
     ) {
    toast.error("Username or Password  is required !!");
    return;
  }

  //submit the data to server to generate token
  loginUser(loginDetail).then((data)=>{
   // console.log("user login: ")
    console.log(data)

     //save the data to localStorage
     doLogin(data,()=>{
       console.log("login detail is saved to local storage")

      //  userContextData.setUser({
      //   data: data.user,
      //   login: true,
      // });
      // if(user.roles.includes("USER"))
       //redirect to user dashboard page
     navigate("/user/dashboard")
     // else
     // navigate("/Admin/dashboard")
     })


    toast.success("Login Successfully")
  }).catch(error=>{

    console.log(error)
    //if data is incorrect
    if(error.response.status==400 || error.response.status==404){
      toast.error(error.response.data.message)
    }else{
      toast.error("Something went wrong  on server !!")
    }
  })

 };


    return (
        <Base>
        <Container >
           <Row className="mt-4">
               <Col sm={{size:6, offset:3}}>
               <Card color="dark" inverse>

                <CardHeader>
                  <h3>User Login </h3>
                </CardHeader>

                <CardBody>
                  <Form onSubmit={handleFormSubmit}>
                      {/*email field */}
                      <FormGroup>
                          <Label for="email">E-mail</Label>
                          <Input type="text" name="email" id="email" placeholder="Enter here" 
                          value={loginDetail.username}
                          onChange={(e)=> handleChange(e,'username')}/>
                          
                          {/* binding variable to the field-->  value={loginDetail.username} */}
                      </FormGroup>
                       
                       {/*password field */}
                      <FormGroup>
                          <Label for="pwd">Password</Label>
                          <Input type="password" name="pwd" id="pwd" placeholder="Enter here" 
                          value={loginDetail.password}
                          onChange={(e)=> handleChange(e,'password')} />
                      </FormGroup>
                      <Container className="text-center">
                    <Button color="light" outline>
                      Login
                    </Button>
                    <Button onClick={handleReset} className="ms-2" outline color="secondary">
                      Reset
                    </Button>
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

export default Login;