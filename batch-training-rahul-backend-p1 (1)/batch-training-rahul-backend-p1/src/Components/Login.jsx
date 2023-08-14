import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Link } from "react-router-dom";
import '../Styles/Login.css';

const Login = () => {
    const [userid, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(userid, password);
    }

    return (
        <div className="login">
            {/* <h1>Login Page</h1> */}
            <div className="login-form">
                <Form>
                    <Form.Group className="mb-3" controlId="formBasicUserId">
                        <Form.Label>User ID</Form.Label>
                        <Form.Control 
                            type="text" 
                            placeholder="Enter User Id" 
                            // value={userid}
                            onChange={(e) => setUserId(e.target.value)}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control 
                            type="password" 
                            placeholder="Password" 
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Form>
            </div>
            <div>
                <Link to="/forgotpw">Forgot password?</Link>
            </div>
        </div>
    )
}

export default Login;