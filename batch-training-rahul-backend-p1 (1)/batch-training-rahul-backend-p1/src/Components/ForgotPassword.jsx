import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Link } from "react-router-dom";
import '../Styles/Login.css';

const ForgotPassword = () => {
	const [email, setEmail] = useState("");
	const [buttonFlag, setButtonFlag] = useState(true);
	const [password, setPassword] = useState("");

	const handleSubmit = (e) => {
		e.preventDefault();
		setButtonFlag(false);
	}

	const handleVerify = (e) => {
		e.preventDefault();
		console.log(email, password);
	}

	return (
		<div className="login">
			<div className="login-form">
				<Form>
					<Form.Group className="mb-3" controlId="formBasicEmail">
						<Form.Label>Please enter your registered email id</Form.Label>
						<Form.Control
							type="email"
							placeholder="Enter email"
							onChange={(e) => setEmail(e.target.value)}
						/>
					</Form.Group>
					{
						buttonFlag ?
							<div>
								<Button variant="primary" type="submit" onClick={handleSubmit}>
									Submit
								</Button>
							</div>
							:
							<div>
								<Form.Group className="mb-3" controlId="formBasicPassword">
									<Form.Label>Password</Form.Label>
									<Form.Control
										type="password"
										placeholder="Password"
										onChange={(e) => setPassword(e.target.value)}
									/>
								</Form.Group>
								<Button variant="primary" type="submit" onClick={handleVerify}>
									Verify
								</Button>
							</div>
					}
				</Form>
			</div>
		</div>
	)
}

export default ForgotPassword;