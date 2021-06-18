import React from "react";
import ReactDOM from "react-dom";
import {Route, Link, HashRouter as Router, Switch} from "react-router-dom";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import NotFound from "./components/NotFound";
import Home from "./components/Home";
import Login from "./components/login/Login";
import {logout} from "./services/auth";
import Festival from "./components/festival/Festival";
import AddFestival from "./components/festival/AddFestival";
import Rezervacija from "./components/festival/Rezervacija";




class App extends React.Component {
    render(){
        return(
            <div style = {{
                backgroundImage: `url(${process.env.PUBLIC_URL + '/festival.jpg'})`,
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat',
                width: '100vw',
                height: '100vh'}}>
            <div>
            <Router>
                <Navbar bg="dark" variant="dark" expand>
                    <Navbar.Brand as ={Link} to="/">
                    <img   src="/festival-logo.jpg"
                            width="60"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"></img>
                    </Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link as={Link} to="/festivali">
                            Festivali
                        </Nav.Link>
                    </Nav>


                {window.localStorage['jwt'] ?
                <Button onClick = {()=>logout()}>Log out</Button> :
                <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                }
                </Navbar>
                <Container style={{marginTop:25}}>
                    <Switch>
                        <Route exact path = "/" component = {Home}/>
                        <Route exact path = "/festivali" component = {Festival}/>
                        <Route exact path="/festivali/add" component={AddFestival}/>
                        <Route exact path="/festivali/rezervisi/:id" component={Rezervacija}/>
                        <Route exact path = "/login" component = {Login}/>
                        <Route component = {NotFound} />
                    </Switch>
                </Container>
            </Router>
            </div>
            </div>
        );
    }
}

ReactDOM.render(<App/>, document.querySelector("#root"));