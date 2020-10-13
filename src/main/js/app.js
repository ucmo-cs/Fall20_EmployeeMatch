import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListComponent from "./components/ListComponent";
import AddComponent from "./components/AddComponent";
import EditComponent from "./components/EditComponent";
import LoginComponent from "./components/LoginComponent";
import Header from "./components/Header";
const ReactDOM = require('react-dom');

function App() {
	return (

		<div>
			<Header/>
			<Router>
				<div className="col-md-6">
					<h1 className="text-center" style={style}>Employee Match</h1>
					<Switch>
						<Route path="/" exact component={ListComponent} />
						<Route path="/list" component={ListComponent} />
						<Route path="/add" component={AddComponent} />
						<Route path="/edit" component={EditComponent} />
						<Route path="/login" component={LoginComponent}/>
					</Switch>
				</div>
			</Router>
		</div>
	);
}

const style = {
	color: 'red',
	margin: '10px'
}

export default App;

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
