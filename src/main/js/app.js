import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import ListComponent from "./components/ListComponent";
import AddComponent from "./components/AddComponent";
import EditComponent from "./components/EditComponent";
import Header from "./components/Header";
const ReactDOM = require('react-dom');
import LoginView from './Views/LoginView'
import HomeView from './Views/HomeView'
import AboutView from './Views/AboutView'

function App() {

	return (

		<div>

			<Router>
				<Header/>
				<div >
					<h1 className="text-center" style={'{position: absolute;}'} style={style}>Employee Match</h1>
					<Switch>
						<Route path="/" exact><HomeView/></Route>
						<Route path="/list" component={ListComponent} />
						<Route path="/add" component={AddComponent} />
						<Route path="/edit" component={EditComponent} />
						<Route path="/about" component={EditComponent}><AboutView/></Route>
						<Route path="/login">
							<LoginView/>
						</Route>

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
