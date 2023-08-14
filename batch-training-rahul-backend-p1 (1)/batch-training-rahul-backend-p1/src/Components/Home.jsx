import React from "react";
import Login from "./Login";
import '../Styles/Login.css';

const Home = () => {
	return (
		<div className="home">
			<h1>Welcome to online banking</h1>
			<Login />
		</div>
	)
}

export default Home;