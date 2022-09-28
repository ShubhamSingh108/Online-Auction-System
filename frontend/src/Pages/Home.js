import { useEffect } from "react";
import { Container } from "reactstrap";
import Base from "../components/Base";
import NewItem from "../components/NewItem";
const Home = () => {

    
    return (
        
        <Base>
      {/*   <div>
            <h1>This is home page</h1>
            <h4>welcome to home page</h4>
        </div> */}
        <Container>
        <NewItem />
        </Container>
        
        </Base>
    );
};

export default Home;